package runners;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import io.cucumber.core.cli.Main;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



public class TestRunner {
    public static void main(String[] args) throws Exception {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/runner.properties"));


            String testingType = properties.getProperty("AutomationTestType");
            String featureFile = properties.getProperty("featureFile");
            String scenarioTag = properties.getProperty("testCaseID");
//            System.out.println(testingType);

            if (Objects.equals(featureFile, "") || Objects.equals(scenarioTag, "") || Objects.equals(testingType, "")) {
                System.out.println("Please specify type of testing, feature file, and scenario tag in runner.properties.");
                System.exit(1);
            }
            String featureFilePath;
            String stepDefFiles;

            switch (testingType.toLowerCase()) {
                case "mobileapp":
                    featureFilePath = "src/test/resources/features/mobileApp/";
                    stepDefFiles = "stepDefinitions.mobileAppSteps";
                    break;

                case "webapp":
                    featureFilePath = "src/test/resources/features/webApp/";
                    stepDefFiles = "stepDefinitions.webAppSteps";
                    break;

                case "restapi":
                    featureFilePath = "src/test/resources/features/restAPI/";
                    stepDefFiles = "stepDefinitions.restAPISteps";
                    break;
                default:
                    throw new IllegalArgumentException("Enter a valid Test Type in runner.properties");
            }

            // Pass feature, glue, and tags dynamically to Cucumber CLI
            String[] cucumberOptions = new String[]{
                    "--plugin", "pretty",
                    "--plugin", "html:target/cucumber-reports",
                    "--plugin", "json:target/cucumber.json",
                    "--plugin", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                    "--glue", stepDefFiles,
                    featureFilePath + featureFile + ".feature",
                    "--tags", "@" + scenarioTag
            };
            Main.main(cucumberOptions);

        }
        catch (IOException e) {
            throw new RuntimeException("Failed to load runner.properties");
        }

    }
}

