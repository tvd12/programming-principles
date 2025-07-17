// HelloPayload.java
public class HelloPayload {
    static {
        System.out.println(">>> HelloPayload static block executed!");
        try {
            Runtime.getRuntime().exec("gnome-calculator"); // ví dụ, có thể thay bằng `echo`
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HelloPayload() {
        System.out.println(">>> Constructor called!");
    }

    public void run() {
        System.out.println("Attack you now!");
    }
}

