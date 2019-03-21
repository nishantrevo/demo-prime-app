package com.nishantrevo.demoapi.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MathUtil {

    public static boolean isPrime(int number){
        log.info("Calculating {} is Prime", number);
        boolean isPrime = true;

        if(number <= 2){
            isPrime = false;
        }
        else{
            int half = number/2;
            for(int i=2; i<=half; i++){
                if(number%i == 0){
                    isPrime = false;
                    break;
                }
            }
        }
        log.info(
                "Calculated {} is {}"
                , number
                , isPrime?"prime":"not prime"
        );
        return isPrime;
    }

}
