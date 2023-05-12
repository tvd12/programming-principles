package org.youngmonkeys.ls.service.nio;

import org.youngmonkeys.ls.entity.User;
import org.youngmonkeys.ls.model.UserModel;
import org.youngmonkeys.ls.repo.UserRepository;
import org.youngmonkeys.ls.service.UserWalletService;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NioUserService {

    private final UserRepository userRepository;
    private final NioUserWalletService userWalletService;

    public Single<UserModel> getUserById(long userId) {
        Single<User> singleUser = Single.fromCallable(
            () -> userRepository.findById(userId)
        )
            .subscribeOn(Schedulers.io());
        Single<Long> singleUserMoney = userWalletService
            .getUserMoney(userId);
        return Single.zip(
            singleUser,
            singleUserMoney,
            (user, money) ->
                UserModel
                    .builder()
                    .id(userId)
                    .username(user.getUsername())
                    .displayName(user.getDisplayName())
                    .build()
        )
            .subscribeOn(Schedulers.io());
    }
}
