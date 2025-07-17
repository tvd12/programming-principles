package org.youngmonkeys.dfs.logger;

public class Main {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getInstance()
            .getLogger("test");
        logger.info(
            "xin chao",
            UserData.builder()
                .id(1)
                .username("abc")
                .password("def")
                .build()
        );
    }
}
