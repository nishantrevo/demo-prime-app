package com.nishantrevo.demoapi.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.*;

/**
 * Logger for Test events
 * @author nishantthakur
 *
 */
@Slf4j
public class TestLoggerListener implements ISuiteListener, ITestListener {
    
    public void onStart(ISuite suite) {
        beforeSuiteLog(suite);
    }
    
    public void beforeSuiteLog(ISuite suite) {
        log(String.format("Starting execution of Suite : %s", suite.getName()));
    }
    
    public void onFinish(ISuite suite) {
        afterSuiteLog(suite);
    }
    
    public void afterSuiteLog(ISuite suite) {
        log(String.format("Finished execution of Suite : %s", suite.getName()));
    }
    
    
    //ITestListener Methods
    
    public void onStart(ITestContext context) {
    }
    
    public void beforeClassLog(ITestContext context) {
    }
    
    public void onFinish(ITestContext context) {
        afterClassLog(context);
    }
    
    public void afterClassLog(ITestContext context) {
        
        ITestNGMethod[] testMethods = context.getAllTestMethods();
        String className = testMethods.length>0
                ? testMethods[0].getTestClass().getName()
                : "**Cant Infer Test ClassName**";
        String suiteName = context.getSuite().getName();
        String testSetName = context.getCurrentXmlTest().getName();
        
        log(String.format("Finished Test Class: [%s], for test set [%s] and suite [%s]", className, testSetName, suiteName));
    }
    
    public void onTestStart(ITestResult result) {
    }
    
    public void beforeTestLog(ITestResult result) {
    }
    
    public void onTestSuccess(ITestResult result) {
        afterTestLog(result, "Passed");
    }
    
    public void onTestFailure(ITestResult result) {
        afterTestLog(result, "Failed");
    }
    
    public void onTestSkipped(ITestResult result) {
        afterTestLog(result, "Skipped");
    }
    
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        afterTestLog(result, "Failed within success percentage");
    }
    
    public void afterTestLog(ITestResult result, String msg, Object ... params) {
        //Log about test method to be executed
        String suiteName = result.getTestContext().getSuite().getName();
        String testSetName = result.getTestContext().getName();
        
        String className = result.getTestClass().getName();
        String testMethodName = result.getMethod().getMethodName();
        
        String resultMsg = result.isSuccess()?"PASS":"FAILED";
        String exceptionMsg = resultMsg=="PASS"?"NO-ERROR":result.getThrowable().getMessage();
        log(String.format("Test %s: %s of class [%s], test set [%s] and suite [%s]\nDataProvider Param Values [%s]\nResult [%s], \nError Message [%s]\n\n\n\n", msg, testMethodName, className, testSetName, suiteName, params, resultMsg, exceptionMsg));
    }
    
    private void log(String msg){
        log.info(msg);
        Reporter.log(msg);
    }
    
}
