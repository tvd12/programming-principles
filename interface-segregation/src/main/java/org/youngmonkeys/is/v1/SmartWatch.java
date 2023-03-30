package org.youngmonkeys.is.v1;

public class SmartWatch implements Watch {

    @Override
    public void watch() {}

    @Override
    public void automaticWindingUpTheThread() {
        throw new UnsupportedOperationException(
            "smart watch can not " +
            "automatic winding up the thread"
        );
    }

    @Override
    public void charge() {}

    @Override
    public void installApp() {}

    @Override
    public void openMediaPlayer() {}

    @Override
    public void openPhoneCall() {}
}
