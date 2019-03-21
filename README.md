# IsPrime web app Usage Guide

### Description ###
A simple web app. Homepage is simple form to take a number. On form submit takes user to result page.
Result page displays:
- **Optimus Prime** If prime number
- **Confused Guy** If not prime number
- **Error** If any error occurs. Eg blank form, number more than java Integer.MAX


#### Important Files/Folders:
- **src/main/resources/application.properties** : application properties like log folder, port, gifs and messages.
- **src/main/resources/static** dir for home page
- **src/main/resources/templates** dir for thymeleaf templates
- **src/test/resources/testng.xml** : Defines the test suite.
- **src/test/resources/testconfig.properties** : Defines default values for test execution. Like browser and grid url. testng.xml parameters are given precedence.

#### testconfig.properties
| ParamName | Value |
| -------- | ----------- |
| browser | default browser name when **browser** env variable is not defined |
| gridurl | grid url |

#### Build app
##### Via Commandline
- Open terminal and go to project root folder.
- run **./mvnw clean package**
- all tests in testng.xml will be executed
- If all tests are success, jar will be created **target/demo-prime-app-0.0.1-SNAPSHOT.jar**

##### Run tests Via TestNG plugin in Eclipse/IntelliJ
- Make sure TestNG plugin is installed
- Direclty run Test class or Test method as reuired in run or debug mode by right clicking on class or method name and choose run
- Similarly, testng.xml file can also be executed.

#### Run app
- Build app
- Run command
```sh
java -jar target/demo-prime-app-0.0.1-SNAPSHOT.jar
```

## Alter test suite
- Test set to be inlcuded in execution can be altered by adding/removing from testng.xml.
- To add another suite xml to execute during test phase of maven build, add in pom.xml -> build -> plugins -> plugin -> maven-surefire-plugin add :
```sh
<suiteXmlFile>/path/to/newTestNg.xml</suiteXmlFile>
```

## Test suite Parameteters:
| ParamName | Value | Defaul Value |
| -------- | ----------- |---------|
| testconfigfiles | comma separated property file or dir names to load during test | **/src/test/resources/testconfig.properties**
| browser | browser name | value of **browser** property in config file |
| grid | url of selenium grid | **null** |

Eg, to set test different property file other than default **configuration.properties** in suite xml set param:
```sh
<parameter name="testconfigfiles" value="/path/to/other/config.properties"/>
```

## ReportNG test report
After test execution test report is available at **test-output/html/index.html**




