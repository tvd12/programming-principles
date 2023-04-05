package org.youngmonkeys.cc.v1.event;

import java.util.List;

import com.tvd12.ezyfox.bean.EzySingletonFactory;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class ApplicationEventPublisher {

    private final EzySingletonFactory singletonFactory;

    public ApplicationEventPublisher(EzySingletonFactory singletonFactory) {
        this.singletonFactory = singletonFactory;
    }

    public void publishEvent(OnUserRegistrationCompleteEvent event) {
        List<ApplicationEventHandler> eventHandlers =
            singletonFactory.getSingletonsOf(ApplicationEventHandler.class);
        for (ApplicationEventHandler handler : eventHandlers) {
            if (handler.getEventType() == OnUserRegistrationCompleteEvent.class) {
                handler.handle(event);
            }
        }
    }
}
