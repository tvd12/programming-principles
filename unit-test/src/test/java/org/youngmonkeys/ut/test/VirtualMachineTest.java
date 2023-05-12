package org.youngmonkeys.ut.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;
import org.youngmonkeys.ut.Calculator;
import org.youngmonkeys.ut.VirtualMachine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tvd12.test.assertion.Asserts;
import com.tvd12.test.util.RandomUtil;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VirtualMachineTest {

    @Test
    public void evaluateBySumTest() {
        // given
        int a = RandomUtil.randomInt();
        int b = RandomUtil.randomInt();
        int sum = a + b;

        Calculator calculator = mock(Calculator.class);
        when(calculator.sum(a, b)).thenReturn(sum);

        VirtualMachine instance = new VirtualMachine(
            calculator
        );

        // when
        String actual = instance.evaluate(
            a + " + " + b
        );

        // then
        Asserts.assertEquals(actual, String.valueOf(sum));
        verify(calculator, times(1)).sum(a, b);
        verifyNoMoreInteractions(calculator);
    }

    @Test
    public void evaluateByMinusTest() {
        // given
        int a = RandomUtil.randomInt();
        int b = RandomUtil.randomInt();

        Calculator calculator = mock(Calculator.class);

        VirtualMachine instance = new VirtualMachine(
            calculator
        );

        // when
        Throwable throwable = Asserts.assertThrows(
            () ->
                instance.evaluate(
                    a + " - " + b
                )
        );

        // then
        Asserts.assertEqualsType(
            throwable,
            IllegalArgumentException.class
        );
        Asserts.assertEquals(
            throwable.getMessage(),
            "only support plus operation"
        );
        verifyZeroInteractions(calculator);
    }
}
