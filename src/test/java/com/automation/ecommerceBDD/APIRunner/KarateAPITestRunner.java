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
 * Complete Karate API Runner for Automation Exercise
 *
 * Features:
 * - JUnit 5 compatible
 * - Karate 1.3.1 compatible
 * - Multiple test methods for different API modules
 * - Automatic report generation
 */
public class KarateAPITestRunner {

    // Run all authentication API tests
    @Karate.Test
    Karate testAuthenticationAPIs() {
        return Karate.run("classpath:features/api/authentication")
                .relativeTo(getClass());
    }

    // Run all product API tests
    @Karate.Test
    Karate testProductAPIs() {
        return Karate.run("classpath:features/api/products")
                .relativeTo(getClass());
    }

    // Run all brand API tests
    @Karate.Test
    Karate testBrandAPIs() {
        return Karate.run("classpath:features/api/brands")
                .relativeTo(getClass());
    }

    // Generate HTML report
    public static void generateReport() {
        String outputPath = "target/karate-reports";
        Collection<File> jsonFiles = FileUtils.listFiles(
                new File(outputPath),
                new String[]{"json"},
                true
        );

        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        for (File file : jsonFiles) {
            jsonPaths.add(file.getAbsolutePath());
        }

        Configuration config = new Configuration(
                new File("target"),
                "Automation Exercise - API Test Report"
        );
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}