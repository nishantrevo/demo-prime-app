package com.nishantrevo.demoapi.controller;

import com.nishantrevo.demoapi.model.IsPrimeRequest;
import com.nishantrevo.demoapi.model.IsPrimeResponse;
import com.nishantrevo.demoapi.service.CalculatorService;
import com.nishantrevo.demoapi.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Slf4j @Controller
public class UiController {

    @Value("${"+Constants.PROPERTY_FILE_KEY_NAME_IS_PRIME_GIF+"}")
    private String isPrimeGif;
    @Value("${"+Constants.PROPERTY_FILE_KEY_NAME_IS_PRIME_MESSAGE+"}")
    private String isPrimeMsg;
    
    @Value("${"+Constants.PROPERTY_FILE_KEY_NAME_IS_NOT_PRIME_GIF+"}")
    private String isNotPrimeGif;
    @Value("${"+Constants.PROPERTY_FILE_KEY_NAME_IS_NOT_PRIME_MESSAGE+"}")
    private String isNotPrimeMsg;
    
    @Value("${"+Constants.PROPERTY_FILE_KEY_NAME_ERROR_GIF+"}")
    private String errorGif;
    @Value("${"+Constants.PROPERTY_FILE_KEY_NAME_ERROR_MESSAGE+"}")
    private String errorMsg;

    @Autowired private CalculatorService calculatorService;

    @RequestMapping(method = RequestMethod.POST, value = Constants.PRIME_URI)
    public String prime(@ModelAttribute(value = "isPrimeRequest") @Valid IsPrimeRequest isPrimeRequest, Model model){
        log.info("{}", isPrimeRequest);
        IsPrimeResponse isPrimeResponse = calculatorService.isPrime(isPrimeRequest);
        log.info("{}", isPrimeResponse);

        String msg, gif;
        log.info("Creating Model for {}", isPrimeResponse);
        if(isPrimeResponse.isPrime()) {
            msg = isPrimeMsg;
            gif = isPrimeGif;
        }else {
            msg = isNotPrimeMsg;
            gif = isNotPrimeGif;
        }
        model.addAttribute(Constants.MODEL_PROPERTY_NAME_MESSAGE, msg);
        model.addAttribute(Constants.MODEL_PROPERTY_NAME_RESULT_GIF, gif);
        log.info("Msg {}", msg);
        log.info("Gif {}", gif);
        log.info("Returning Model {}", model);
        return Constants.RESULT_URI;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e){

        log.error("Request {} failed with exception:\n{}", request, e.toString());
        String msg = errorMsg;
        String gif = errorGif;

        ModelAndView errorModelAndView = new ModelAndView(Constants.RESULT_URI);
        Map<String, Object> model = errorModelAndView.getModel();
        model.put(Constants.MODEL_PROPERTY_NAME_MESSAGE, msg);
        model.put(Constants.MODEL_PROPERTY_NAME_RESULT_GIF, gif);

        log.info("Msg {}", msg);
        log.info("Gif {}", gif);
        log.info("Returning ModelAndView {}", errorModelAndView);

        return errorModelAndView;
    }
}
