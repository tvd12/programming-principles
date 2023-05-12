package org.youngmonkeys.ls.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.youngmonkeys.ls.entity.User;
import org.youngmonkeys.ls.model.UserModel;
import org.youngmonkeys.ls.repo.UserRepository;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserWalletService userWalletService;

    public UserModel getUserById(long userId) {
        User user = userRepository.findById(userId);
        long money = userWalletService.getUserMoney(userId);
        return UserModel
            .builder()
            .id(userId)
            .username(user.getUsername())
            .displayName(user.getDisplayName())
            .build();
    }
}
