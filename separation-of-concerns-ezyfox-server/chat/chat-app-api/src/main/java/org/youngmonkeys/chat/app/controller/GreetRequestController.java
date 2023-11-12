package org.youngmonkeys.chat.app.controller;

import org.youngmonkeys.chat.app.constant.Commands;
import org.youngmonkeys.chat.app.constant.Errors;
import org.youngmonkeys.chat.app.request.GoRequest;
import org.youngmonkeys.chat.app.request.HelloRequest;
import org.youngmonkeys.chat.app.response.HelloResponse;
import org.youngmonkeys.chat.app.service.GreetingService;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.core.annotation.EzyDoHandle;
import com.tvd12.ezyfox.core.annotation.EzyRequestController;
import com.tvd12.ezyfox.core.exception.EzyBadRequestException;
import com.tvd12.ezyfox.io.EzyStrings;
import com.tvd12.ezyfoxserver.entity.EzyUser;
import com.tvd12.ezyfoxserver.support.factory.EzyResponseFactory;

@EzyRequestController
public class GreetRequestController {

    @EzyAutoBind
    private EzyResponseFactory responseFactory;

    @EzyAutoBind
    private GreetingService greetingService;

    @EzyDoHandle(Commands.HELLO)
    public void sayHello(EzyUser user, HelloRequest request) {
        if (EzyStrings.isNoContent(request.getNickName())) {
            throw new EzyBadRequestException(Errors.UNKNOWN, "invalid nick name");
        }
        responseFactory.newObjectResponse()
            .command(Commands.HELLO)
            .data(new HelloResponse(greetingService.hello(request.getNickName())))
            .user(user)
            .execute();
    }

    @EzyDoHandle(Commands.GO)
    public void sayGo(EzyUser user, GoRequest request) {
        if (EzyStrings.isNoContent(request.getNickName())) {
            throw new EzyBadRequestException(Errors.UNKNOWN, "invalid nick name");
        }
        responseFactory.newObjectResponse()
            .command(Commands.GO)
            .data(new HelloResponse(greetingService.go(request.getNickName())))
            .user(user)
            .execute();
    }
}
