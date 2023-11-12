package org.youngmonkeys.game;

import org.youngmonkeys.game.plugin.PluginEntry;
import org.youngmonkeys.game.plugin.PluginEntryLoader;
import org.youngmonkeys.game.app.AppEntry;
import org.youngmonkeys.game.app.AppEntryLoader;

import com.tvd12.ezyfoxserver.constant.EzyEventType;
import com.tvd12.ezyfoxserver.embedded.EzyEmbeddedServer;
import com.tvd12.ezyfoxserver.ext.EzyAppEntry;
import com.tvd12.ezyfoxserver.ext.EzyPluginEntry;
import com.tvd12.ezyfoxserver.setting.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ApplicationStartup {

    public static final String ZONE_APP_NAME = "game";

    public static void main(String[] args) throws Exception {

        EzyPluginSettingBuilder pluginSettingBuilder = new EzyPluginSettingBuilder()
            .name(ZONE_APP_NAME)
            .addListenEvent(EzyEventType.USER_LOGIN)
            .entryLoader(DecoratedPluginEntryLoader.class);

        EzyAppSettingBuilder appSettingBuilder = new EzyAppSettingBuilder()
            .name(ZONE_APP_NAME)
            .entryLoader(DecoratedAppEntryLoader.class);

        EzyZoneSettingBuilder zoneSettingBuilder = new EzyZoneSettingBuilder()
            .name(ZONE_APP_NAME)
            .application(appSettingBuilder.build())
            .plugin(pluginSettingBuilder.build());

        EzySimpleSettings settings = new EzySettingsBuilder()
            .zone(zoneSettingBuilder.build())
            .build();

        EzyEmbeddedServer server = EzyEmbeddedServer.builder()
            .settings(settings)
            .build();
        server.start();

    }

    public static class DecoratedPluginEntryLoader extends PluginEntryLoader {

        @Override
        public EzyPluginEntry load() throws Exception {
            return new PluginEntry() {

                @Override
                protected String getConfigFile(EzyPluginSetting setting) {
                    return Paths.get(getPluginPath(setting), "config", "config.properties")
                        .toString();
                }

                private String getPluginPath(EzyPluginSetting setting) {
                    Path pluginPath = Paths.get("game-plugin");
                    if (!Files.exists(pluginPath)) {
                        pluginPath = Paths.get("../game-plugin");
                    }
                    return pluginPath.toString();
                }
            };
        }
    }

    public static class DecoratedAppEntryLoader extends AppEntryLoader {

        @Override
        public EzyAppEntry load() throws Exception {
            return new AppEntry() {

                @Override
                protected String getConfigFile(EzyAppSetting setting) {
                    return Paths.get(getAppPath(), "config", "config.properties")
                        .toString();
                }

                private String getAppPath() {
                    Path pluginPath = Paths.get("game-app-entry");
                    if (!Files.exists(pluginPath)) {
                        pluginPath = Paths.get("../game-app-entry");
                    }
                    return pluginPath.toString();
                }
            };
        }
    }
}
