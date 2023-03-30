package org.youngmonkeys.di.v1.dependencies_injection;

public class ApplicationStartup {

    public static void main(String[] args) {
        MyRepository repository = new MyRepository();
        MyService service = new MyService(repository);
        MyController controller = new MyController(service);
        controller.doSomething();
    }
}
