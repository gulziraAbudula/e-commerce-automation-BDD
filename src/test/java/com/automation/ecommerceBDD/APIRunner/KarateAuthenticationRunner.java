package com.automation.ecommerceBDD.APIRunner;

import com.intuit.karate.junit5.Karate;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Karate Test Runner for Authentication APIs
 *
 * APIs Covered:
 * - API 7: POST to verify login with valid details
 * - API 8: POST to verify login without email parameter
 * - API 9: DELETE to verify login
 * - API 10: POST to verify login with invalid details
 * - API 11: POST to create/register user account
 * - API 12: DELETE method to delete user account
 * - API 13: PUT method to update user account
 * - API 14: GET user account detail by email
 */
public class KarateAuthenticationRunner {

    @Karate.Test
    Karate testAuthenticationAPIs() {
        return Karate.run("classpath:features/api/authentication")
                .relativeTo(getClass());
    }

    /**
     * Generate HTML report for Authentication API tests
     */
    public static void generateAuthenticationReport() {
        String karateOutputPath = "target/karate-reports/authentication";
        Collection<File> jsonFiles = FileUtils.listFiles(
                new File(karateOutputPath),
                new String[]{"json"},
                true
        );

        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        for (File file : jsonFiles) {
            jsonPaths.add(file.getAbsolutePath());
        }

        Configuration config = new Configuration(
                new File("target/cucumber-reports"),
                "Automation Exercise - Authentication API Tests"
        );
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();

        System.out.println("✅ Authentication API Report Generated");
    }

    /**
     * Run Command:
     * mvn test -Dtest=KarateAuthenticationRunner
     */
}
