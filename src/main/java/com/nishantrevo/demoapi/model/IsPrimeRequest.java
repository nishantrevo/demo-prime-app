package com.nishantrevo.demoapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nishantrevo.demoapi.util.Constants;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Data @ToString
public class IsPrimeRequest {

    @JsonProperty(Constants.JSON_PROPERTY_NAME_NUMBER)
    @Range(min = 0, max = Integer.MAX_VALUE)
    private int number;

}
