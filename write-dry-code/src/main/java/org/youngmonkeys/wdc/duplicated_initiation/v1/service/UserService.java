package org.youngmonkeys.wdc.duplicated_initiation.v1.service;

import java.util.ArrayList;
import java.util.List;

import org.youngmonkeys.wdc.duplicated_initiation.v1.model.UserModel;

import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class UserService {

    public UserModel getUserByName(String username) {
        return new UserModel();
    }

    public List<UserModel> getUsersByKeyword(String keyword) {
        return new ArrayList<>();
    }
}
