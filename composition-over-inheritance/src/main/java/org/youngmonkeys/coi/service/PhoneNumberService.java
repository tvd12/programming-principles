package org.youngmonkeys.coi.service;

import java.util.Map;

import com.tvd12.ezyfox.util.EzyMapBuilder;
import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class PhoneNumberService {

    private final Map<Long, String> phoneNumberByUserId =
        EzyMapBuilder
            .mapBuilder()
            .put(1L, "0123456789")
            .toMap();

    public String getPhoneNumberByUserId(long userId) {
        return phoneNumberByUserId.get(userId);
    }
}
