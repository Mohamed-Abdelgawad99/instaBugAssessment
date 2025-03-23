package runners;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import io.cucumber.core.cli.Main;




public class TestRunner {
    public static void main(String[] args) throws Exception {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/runner.properties"));


            String testingType = properties.getProperty("AutomationTestType");
            String featureFile = properties.getProperty("featureFile");
            String scenarioTag = properties.getProperty("testCaseID");
            List<String> addOns = null;


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
                    addOns = new ArrayList<>();
                    addOns.add("--glue");
                    addOns.add("hooks");
                    break;

                case "restapi":
                    featureFilePath = "src/test/resources/features/restAPI/";
                    stepDefFiles = "stepDefinitions.restAPISteps";
                    break;
                default:
                    throw new IllegalArgumentException("Enter a valid Test Type in runner.properties");
            }

            // Pass feature, glue, and tags dynamically to Cucumber CLI
            List<String> cucumberOptions = new ArrayList<>(Arrays.asList(
                    "--plugin", "pretty",
                    "--plugin", "html:target/cucumber-reports.html",
                    "--plugin", "json:target/cucumber.json",
                    "--plugin", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                    "--glue", stepDefFiles,
                    featureFilePath + featureFile + ".feature",
                    "--tags", "@" + scenarioTag
            ));
            if (addOns != null){
                cucumberOptions.addAll(addOns);
            }
            String[] newCucumberOptions = cucumberOptions.toArray(new String[0]);
            Main.run(newCucumberOptions);

            try {
                String allurePath = "bin/allure-2.33.0/bin/allure.bat";
                AllureHistoryHelper.preserveHistory();
                Process generateReport = new ProcessBuilder(allurePath, "generate", "allure-results", "--clean", "-o", "allure-report")
                        .inheritIO()
                        .start();
                generateReport.waitFor();
                AllureHistoryHelper.restoreHistory();

                // Open the report
                Process openReport = new ProcessBuilder(allurePath, "serve", "allure-results")
                        .inheritIO()
                        .start();
                System.out.println("\n✅ Allure report is running.");
                System.out.println("📂 Opened in your browser from local server.");
                System.out.println("🛑 Press ENTER to stop the Allure server and exit...");

                System.in.read(); // Wait for user input
                new ProcessBuilder("taskkill", "/F", "/IM", "java.exe", "/T").start();
                System.out.println("🚪 Allure server stopped. Goodbye!");
            } catch (IOException | InterruptedException e) {
                throw e;
            }
        }
        catch (IOException e) {
            throw e;
        }
    }
}

