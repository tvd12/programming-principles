package org.youngmonkeys.refactoring.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RefactoringStartup {

    public static void main(String[] args) {
        try {
            SpringApplication.run(RefactoringStartup.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
