package com.nishantrevo.demoapi.test.unit;


import com.nishantrevo.demoapi.util.MathUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class MathUtiTests {

    @Test(dataProvider = "numberAndIsPrimeFlagDataProvider")
    public void isPrime(int number, boolean isPrime){
        assertThat(MathUtil.isPrime(number))
                .as(number + " isPrime check")
                .isEqualTo(isPrime);
    }

    @DataProvider
    public static Object[][] numberAndIsPrimeFlagDataProvider(){

        Object[][] data = new Object[][]{
                {Integer.MIN_VALUE, false},
                {-10, false},
                {-7, false},
                {-5, false},
                {-3, false},
                {-2, false},
                {-1, false},
                {0, false},
                {1, false},
                {2, false},
                {3, true},
                {4, false},
                {5, true},
                {6, false},
                {7, true},
                {8, false},
                {9, false},
                {10, false},
                {Integer.MAX_VALUE, true},
        };
        return data;
    }

}
