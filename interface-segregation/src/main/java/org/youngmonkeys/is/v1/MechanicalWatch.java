package org.youngmonkeys.is.v1;

public class MechanicalWatch implements Watch {

    @Override
    public void watch() {}

    @Override
    public void automaticWindingUpTheThread() {}

    @Override
    public void charge() {
        throw new UnsupportedOperationException(
            "mechanical watch can not charge"
        );
    }

    @Override
    public void installApp() {
        throw new UnsupportedOperationException(
            "mechanical watch can not install app"
        );
    }

    @Override
    public void openMediaPlayer() {
        throw new UnsupportedOperationException(
            "mechanical watch can not play media"
        );
    }

    @Override
    public void openPhoneCall() {
        throw new UnsupportedOperationException(
            "mechanical watch can not call phone"
        );
    }
}
