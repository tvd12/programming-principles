package org.youngmonkeys.cc.v1.event;

import org.youngmonkeys.cc.v1.model.User;

public class ApplicationEvent {

    private User user;

    public ApplicationEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
