package org.youngmonkeys.ut.test;

import org.testng.annotations.Test;
import org.youngmonkeys.ut.Calculator;

import com.tvd12.test.assertion.Asserts;
import com.tvd12.test.util.RandomUtil;

public class CalculatorTest {

    @Test
    public void sumTest() {
        // given
        int a = RandomUtil.randomInt();
        int b = RandomUtil.randomInt();

        Calculator instance = new Calculator();

        // when
        int actual = instance.sum(a, b);

        // then
        int expectation = a + b;
        Asserts.assertEquals(actual, expectation);
    }
}
