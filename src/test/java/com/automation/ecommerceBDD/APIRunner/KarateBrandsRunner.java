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
 * Karate Test Runner for Brands APIs
 *
 * APIs Covered:
 * - API 3: Get all brands list
 * - API 4: PUT to all brands list
 */
public class KarateBrandsRunner {

    @Karate.Test
    Karate testBrandAPIs() {
        return Karate.run("classpath:features/api/brands")
                .relativeTo(getClass());
    }

    /**
     * Generate HTML report for Brands API tests
     */
    public static void generateBrandsReport() {
        String karateOutputPath = "target/karate-reports/brands";
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
                "Automation Exercise - Brands API Tests"
        );
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();

        System.out.println("✅ Brands API Report Generated");
    }

    /**
     * Run Command:
     * mvn test -Dtest=KarateBrandsRunner
     */
}