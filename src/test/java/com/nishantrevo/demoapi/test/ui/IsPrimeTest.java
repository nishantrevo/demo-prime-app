package com.nishantrevo.demoapi.test.ui;

import com.codeborne.selenide.Condition;
import com.nishantrevo.demoapi.test.ui.locators.HomePage;
import com.nishantrevo.demoapi.test.ui.locators.ResultPage;
import com.nishantrevo.demoapi.util.Constants;
import com.nishantrevo.demoapi.util.PropertyReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class IsPrimeTest extends UiTestBase{
    
    @BeforeMethod
    public void openHomePage(){
        open("/");
    }
    
    @Test
    public void submittingPrimeNumberShowsOptimusPrime(){
        $(HomePage.NUMBER_INPUT).sendKeys("7");
        $(HomePage.SUBMIT_BUTTON).click();
        
        $(ResultPage.MESSAGE)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text(PropertyReader.get(Constants.PROPERTY_FILE_KEY_NAME_IS_PRIME_MESSAGE)));
        $(ResultPage.GIF_IFRAME)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.attribute("src", PropertyReader.get(Constants.PROPERTY_FILE_KEY_NAME_IS_PRIME_GIF)));
    }
    
    
    @Test
    public void submittingNonPrimeNumberShowsConfusedPerson(){
        $(HomePage.NUMBER_INPUT).sendKeys("9");
        $(HomePage.SUBMIT_BUTTON).click();
        
        $(ResultPage.MESSAGE)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text(PropertyReader.get(Constants.PROPERTY_FILE_KEY_NAME_IS_NOT_PRIME_MESSAGE)));
        $(ResultPage.GIF_IFRAME)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.attribute("src", PropertyReader.get(Constants.PROPERTY_FILE_KEY_NAME_IS_NOT_PRIME_GIF)));
    }
    
    @Test
    public void submittingBlankFormShowsError(){
        $(HomePage.NUMBER_INPUT).clear();
        $(HomePage.SUBMIT_BUTTON).click();
        
        $(ResultPage.MESSAGE)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text(PropertyReader.get(Constants.PROPERTY_FILE_KEY_NAME_ERROR_MESSAGE)));
        $(ResultPage.GIF_IFRAME)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.attribute("src", PropertyReader.get(Constants.PROPERTY_FILE_KEY_NAME_ERROR_GIF)));
    }
    
    @Test
    public void submittingNumberGreaterThanMaxIntegerShowsError(){
        long number = Integer.MAX_VALUE + 1;
        $(HomePage.NUMBER_INPUT).sendKeys(number+"");
        $(HomePage.SUBMIT_BUTTON).click();
        
        $(ResultPage.MESSAGE)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text(PropertyReader.get(Constants.PROPERTY_FILE_KEY_NAME_ERROR_MESSAGE)));
        $(ResultPage.GIF_IFRAME)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.attribute("src", PropertyReader.get(Constants.PROPERTY_FILE_KEY_NAME_ERROR_GIF)));
    }
    
    @Test
    public void maxIntegerIsPrime(){
        $(HomePage.NUMBER_INPUT).sendKeys(Integer.MAX_VALUE+"");
        $(HomePage.NUMBER_INPUT).shouldHave(Condition.value(Integer.MAX_VALUE+""));
        $(HomePage.SUBMIT_BUTTON).click();
        
        $(ResultPage.MESSAGE)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.text(PropertyReader.get(Constants.PROPERTY_FILE_KEY_NAME_IS_PRIME_MESSAGE)));
        $(ResultPage.GIF_IFRAME)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.attribute("src", PropertyReader.get(Constants.PROPERTY_FILE_KEY_NAME_IS_PRIME_GIF)));
    }
    
}
