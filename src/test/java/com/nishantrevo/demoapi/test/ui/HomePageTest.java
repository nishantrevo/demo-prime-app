package com.nishantrevo.demoapi.test.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.nishantrevo.demoapi.test.ui.locators.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.*;

public class HomePageTest extends UiTestBase {

    SelenideElement messageElement;
    SelenideElement numberInputElement;
    SelenideElement submitButtonElement;

    @BeforeClass
    public void openHomePage(){
        open("/");
        messageElement = $(HomePage.MESSAGE);
        numberInputElement = $(HomePage.NUMBER_INPUT);
        submitButtonElement = $(HomePage.SUBMIT_BUTTON);
    }

    @Test
    public void titleIsCorrect(){
        assertThat(title())
                .isNotNull()
                .isNotBlank()
                .isNotEmpty()
                .isEqualTo("Get Optimus Prime");
    }

    @Test
    public void messageIsCorrect(){
        messageElement.shouldHave(Condition.text("Enter a prime number to call Optimus"));
    }

    @Test
    public void numberInputElementTagName(){
        assertThat(numberInputElement.getTagName())
                .isEqualTo("input");
    }

    @Test
    public void numberInputType(){
        numberInputElement.shouldHave(Condition.attribute("type","number"));
    }
    
    @Test
    public void numberInputDoesNotAcceptsNumber(){
        numberInputElement.sendKeys("abc");
        numberInputElement.shouldHave(Condition.value(""));
    }
    
    @Test
    public void numberInputAcceptsNumber(){
        numberInputElement.clear();
        numberInputElement.sendKeys("123");
        numberInputElement.shouldHave(Condition.value("123"));
    }

    @Test
    public void submitButtonIsVisible(){
        submitButtonElement.shouldBe(Condition.visible);
    }
}
