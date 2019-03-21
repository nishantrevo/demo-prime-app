package com.nishantrevo.demoapi.service;

import com.nishantrevo.demoapi.model.IsPrimeRequest;
import com.nishantrevo.demoapi.model.IsPrimeResponse;

public interface CalculatorService {

    IsPrimeResponse isPrime(IsPrimeRequest isPrimeRequest);

}
