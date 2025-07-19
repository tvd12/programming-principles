package org.youngmonkeys.dfs.limit_request_rate;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.server.core.request.RequestArguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@EzySingleton
public class EzyDefenceWatcher {

    private final ScheduledExecutorService scheduler;
    private final WebEzyDefenceSettingService settingService;
    private final WebWhiteUriIpService whiteUriIpService;
    private final List<String> ipBuffer;
    private final List<String> staleIps;
    private final List<String> unblockedIps;
    private final Map<String, BlockingPeriod> blockingPeriodByIp;
    private final Map<String, EzyActionFrame> requestPerSecondFrameByIp;

    public EzyDefenceWatcher(
        WebEzyDefenceSettingService settingService,
        WebWhiteUriIpService whiteUriIpService
    ) {
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.settingService = settingService;
        this.whiteUriIpService = whiteUriIpService;
        this.ipBuffer = new ArrayList<>();
        this.staleIps = new ArrayList<>();
        this.unblockedIps = new ArrayList<>();
        this.blockingPeriodByIp = new ConcurrentHashMap<>();
        this.requestPerSecondFrameByIp = new ConcurrentHashMap<>();
        this.startCleanUpIpsSchedule();
    }

    public boolean acceptRequest(
        RequestArguments arguments,
        String ip
    ) {
        if (blockingPeriodByIp.containsKey(ip)) {
            return false;
        }
        List<String> uris = Arrays.asList(
            arguments.getRequest().getRequestURI(),
            arguments.getUriTemplate()
        );
        if (whiteUriIpService.isInWhileList(uris, ip)) {
            return true;
        }
        boolean maxRequest = addRequest(ip);
        if (maxRequest) {
            long duration = settingService.getIpBlockingDuration();
            BlockingPeriod blockingPeriod = new BlockingPeriod(duration);
            blockingPeriodByIp.put(ip, blockingPeriod);
        }
        return !maxRequest;
    }

    /**
     * Add request to requestPerSecondFrameByIp.
     *
     * @param ip client's IP address
     * @return <b>True</b> if successful, <b>False</b> if failed
     */
    private boolean addRequest(String ip) {
        EzyActionFrame frame = requestPerSecondFrameByIp
            .compute(ip, (k, v) -> {
                if (v == null || v.isExpired()) {
                    return new EzyActionFrameSecond(
                        settingService.getMaxRequestPerSecondPerIp()
                    );
                }
                return v;
            });

        return frame.addActions(1);
    }

    private void startCleanUpIpsSchedule() {
        scheduler.scheduleAtFixedRate(
            this::cleanUpIps,
            1000,
            1000,
            TimeUnit.SECONDS
        );
    }

    private void cleanUpIps() {
        unblockIps();
        removeStaleIps();
    }

    private void unblockIps() {
        unblockedIps.clear();
        ipBuffer.clear();
        ipBuffer.addAll(blockingPeriodByIp.keySet());
        long now = System.currentTimeMillis();
        for (String ip : ipBuffer) {
            BlockingPeriod blockingPeriod = blockingPeriodByIp.get(ip);
            if (blockingPeriod == null) {
                continue;
            }
            if (blockingPeriod.isEnded(now)) {
                unblockedIps.add(ip);
            }
        }

        for (String ip : unblockedIps) {
            blockingPeriodByIp.remove(ip);
        }
    }

    private void removeStaleIps() {
        staleIps.clear();
        ipBuffer.clear();
        ipBuffer.addAll(requestPerSecondFrameByIp.keySet());
        for (String ip : ipBuffer) {
            EzyActionFrame frame = requestPerSecondFrameByIp.get(ip);
            if (frame == null || frame.isExpired()) {
                staleIps.add(ip);
            }
        }

        for (String ip : staleIps) {
            requestPerSecondFrameByIp.remove(ip);
        }
    }
}

