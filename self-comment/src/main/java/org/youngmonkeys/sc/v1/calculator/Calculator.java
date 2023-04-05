package org.youngmonkeys.sc.v1.calculator;

public class Calculator {

    public int sum(int a, int b) {
        return a + b;
    }

    public int sum(int a, int b, int c) {
        return a + b - c;
    }

    public int sumAndMinus(
        int numberToAdd1,
        int numberToAdd2,
        int numberToMinus
    ) {
        return numberToAdd1 + numberToAdd2 - numberToMinus;
    }
}
