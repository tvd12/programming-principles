package org.youngmonkeys.ut;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VirtualMachine {

    private final Calculator calculator;

    public String evaluate(String expression) {
        String[] strings = expression.split(" ");
        if ("+".equals(strings[1])) {
            return String.valueOf(
                calculator.sum(
                    Integer.parseInt(strings[0]),
                    Integer.parseInt(strings[2])
                )
            );
        }
        throw new IllegalArgumentException(
            "only support plus operation"
        );
    }
}
