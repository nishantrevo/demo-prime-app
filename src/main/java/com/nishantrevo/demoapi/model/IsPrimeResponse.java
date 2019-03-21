package com.nishantrevo.demoapi.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.nishantrevo.demoapi.util.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class IsPrimeResponse {

    @Getter @Setter
    @JsonProperty(Constants.JSON_PROPERTY_NAME_NUMBER)
    private int number;

    @JsonProperty(Constants.JSON_PROPERTY_NAME_IS_PRIME)
    private boolean isPrime;

    @JsonSetter(Constants.JSON_PROPERTY_NAME_IS_PRIME)
    public void setPrime(boolean prime) {
        isPrime = prime;
    }

    @JsonGetter(Constants.JSON_PROPERTY_NAME_IS_PRIME)
    public boolean isPrime() {
        return isPrime;
    }
}
