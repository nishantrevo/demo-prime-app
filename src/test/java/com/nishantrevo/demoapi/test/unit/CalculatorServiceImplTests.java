package com.nishantrevo.demoapi.test.unit;

import com.nishantrevo.demoapi.model.IsPrimeRequest;
import com.nishantrevo.demoapi.model.IsPrimeResponse;
import com.nishantrevo.demoapi.service.CalculatorServiceImpl;
import com.nishantrevo.demoapi.service.InMemoryCache;
import lombok.extern.slf4j.Slf4j;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CalculatorServiceImplTests {

    private static final String CACHE_SERVICE_FIELD_NAME_IN_CALCULATOR_IMPL = "primeCacheService";

    CalculatorServiceImpl calculatorServiceImpl = new CalculatorServiceImpl();
    Field calculatorServiceField;
    @Mock InMemoryCache<Integer,Boolean> mockCache;
    @Captor ArgumentCaptor<Integer> integerArgumentCaptor;
    @Captor ArgumentCaptor<Boolean> booleanArgumentCaptor;

    @BeforeMethod
    public void before(){
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(calculatorServiceImpl, CACHE_SERVICE_FIELD_NAME_IN_CALCULATOR_IMPL, mockCache);
    }


    @Test(description = "If number is in cache, dont calculate, return value.")
    public void returns_from_cache_if_result_is_in_cache() throws Exception{

        //Set 7 is not prime in cache, even though it is
        Boolean sevenIsNotPrime = new Boolean("false");
        Mockito.when(mockCache.isCached(7)).thenReturn(true);
        Mockito.when(mockCache.getCachedValue(7)).thenReturn(sevenIsNotPrime);

        //Call isPrime method
        IsPrimeRequest isPrimeRequest = new IsPrimeRequest();
        isPrimeRequest.setNumber(7);
        IsPrimeResponse isPrimeResponse = calculatorServiceImpl.isPrime(isPrimeRequest);

        //Check wrong value from cache is returned
        assertThat(isPrimeResponse.isPrime())
                .isEqualTo(sevenIsNotPrime)
                .isFalse();

    }


    @Test(description = "If number is not in cache, calculate, return value.")
    public void returns_calculated_result_if_result_is_not_in_cache() throws Exception{

        Boolean sevenIsNotPrime = new Boolean("false");
        //Make isCached(7) return false
        Mockito.when(mockCache.isCached(7)).thenReturn(false);
        //But set 7 is not prime in cache, even though it is a prime
        Mockito.when(mockCache.getCachedValue(7)).thenReturn(sevenIsNotPrime);


        //Call isPrime method
        IsPrimeRequest isPrimeRequest = new IsPrimeRequest();
        isPrimeRequest.setNumber(7);
        IsPrimeResponse isPrimeResponse = calculatorServiceImpl.isPrime(isPrimeRequest);

        //Check it calculated correct value instead of returning wrong value
        assertThat(isPrimeResponse.isPrime())
                .isNotEqualTo(sevenIsNotPrime)
                .isTrue();

    }

    @Test(description = "After calculation, result is added to cache")
    public void result_is_added_to_cache_after_calculation(){

        //Make isCached(7) return false
        Mockito.when(mockCache.isCached(7)).thenReturn(false);

        //Call isPrime method
        IsPrimeRequest isPrimeRequest = new IsPrimeRequest();
        isPrimeRequest.setNumber(7);
        IsPrimeResponse isPrimeResponse = calculatorServiceImpl.isPrime(isPrimeRequest);

        //Check it calculated correct value
        assertThat(isPrimeResponse.isPrime())
                .isTrue();

        //Check addToCache was called
        Mockito.verify(mockCache, Mockito.atLeastOnce()).addToCache(integerArgumentCaptor.capture(), booleanArgumentCaptor.capture());
        assertThat(integerArgumentCaptor.getAllValues())
                .hasSize(1)
                .containsOnly(7);
        assertThat(booleanArgumentCaptor.getAllValues())
                .hasSize(1)
                .containsOnly(true);

    }


}
