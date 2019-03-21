package com.nishantrevo.demoapi.test.ui;

import com.codeborne.selenide.Configuration;
import com.nishantrevo.demoapi.DemoApiApplication;
import com.nishantrevo.demoapi.util.PropertyReader;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class UiTestBase {

    private static final String MAIN_RESOURCES_FOLDER = "src/main/resources/";
    private static final String TEST_RESOURCES_FOLDER = "src/test/resources/";
    
    @BeforeSuite
    public void startApplication() throws IOException {
        DemoApiApplication.main(new String[]{});
        PropertyReader.init(MAIN_RESOURCES_FOLDER);
        PropertyReader.init(TEST_RESOURCES_FOLDER);
    }
    
    @AfterSuite
    public void stopApplication(){
        DemoApiApplication.close();
    }
    
    @BeforeClass
    @Parameters({"testconfigfiles", "gridurl", "browser"})
    public void configureBrowser(
            @Optional String testConfigFiles,
            @Optional String gridUrl,
            @Optional String browser
    ) throws IOException {
        
        //Add provided config files to runtime
        if(testConfigFiles != null) {
            for (String fileName : testConfigFiles.split(","))
                PropertyReader.init(fileName);
        }
        
        //Set grid url if grid test
        if(gridUrl == null || gridUrl.isEmpty()) {
            Configuration.remote = PropertyReader.get("gridurl");
        }else{
            Configuration.remote = gridUrl;
        }
        
        //Set browser
        if(browser == null || browser.isEmpty()){
            Configuration.browser = PropertyReader.get("browser");
        }
        else {
            Configuration.browser = browser;
        }
    }

}
