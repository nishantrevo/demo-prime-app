package com.nishantrevo.demoapi.service;

import com.nishantrevo.demoapi.model.IsPrimeRequest;
import com.nishantrevo.demoapi.model.IsPrimeResponse;
import com.nishantrevo.demoapi.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Autowired
    private CacheService<Integer,Boolean> primeCacheService;

    public IsPrimeResponse isPrime(IsPrimeRequest isPrimeRequest){
        int number = isPrimeRequest.getNumber();
        boolean isPrime;

        if(primeCacheService.isCached(number)) {
            isPrime = primeCacheService.getCachedValue(number);
        }
        else {
            isPrime = MathUtil.isPrime(number);
            primeCacheService.addToCache(number, isPrime);
        }

        IsPrimeResponse isPrimeResponse = new IsPrimeResponse();
        isPrimeResponse.setNumber(number);
        isPrimeResponse.setPrime(isPrime);

        return isPrimeResponse;
    }
}
