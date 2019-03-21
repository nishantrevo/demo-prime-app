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
    @Parameters({"testconfigfiles", "gridrun", "gridurl", "browser"})
    public void configureBrowser(
            @Optional String testConfigFiles,
            @Optional String gridRun,
            @Optional String gridUrl,
            @Optional String browser
    ) throws IOException {
        
        //Add provided config files to runtime
        if(testConfigFiles != null) {
            for (String fileName : testConfigFiles.split(","))
                PropertyReader.init(fileName);
        }
        
        //Set grid url if grid test
        boolean isGridRun = false;
        try {
            if(gridRun != null)
                isGridRun = Boolean.getBoolean(gridRun);
            else
                isGridRun = Boolean.getBoolean(PropertyReader.get("gridrun"));
        }catch (Exception e){
        }
        if(isGridRun && (gridUrl == null || gridUrl.isEmpty())) {
            Configuration.remote = PropertyReader.get("gridurl");
        }else if(isGridRun && gridUrl != null && gridUrl.length()>0) {
            Configuration.remote = gridUrl;
        }
        
        //Set browser
        if(browser == null || browser.isEmpty()){
            Configuration.browser = PropertyReader.get("browser");
        }
        if(browser != null && !browser.isEmpty()){
            Configuration.browser = browser;
        }
    }

}
