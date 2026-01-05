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
 * Karate Test Runner for Products APIs
 *
 * APIs Covered:
 * - API 1: Get all products list
 * - API 2: POST to all products list
 * - API 5: POST to search product
 * - API 6: POST to search product without search_product parameter
 */
public class KarateProductsRunner {

    @Karate.Test
    Karate testProductAPIs() {
        return Karate.run("classpath:features/api/products")
                .relativeTo(getClass());
    }

    /**
     * Generate HTML report for Products API tests
     */
    public static void generateProductsReport() {
        String karateOutputPath = "target/karate-reports/products";
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
                "Automation Exercise - Products API Tests"
        );
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();

        System.out.println("✅ Products API Report Generated");
    }

    /**
     * Run Command:
     * mvn test -Dtest=KarateProductsRunner
     */
}
