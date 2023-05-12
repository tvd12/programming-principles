package org.youngmonkeys.ls.service;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import io.reactivex.rxjava3.core.Single;

@Service
public class UserWalletService {

    public Single<Long> getUserMoney(long userId) {
        return Single.just(0L);
    }
}
