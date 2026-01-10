package com.automation.ecommerceBDD.UIRunner;

import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.ConfigurationParameter;

import static io.cucumber.junit.platform.engine.Constants.EXECUTION_DRY_RUN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/ui/authentication")
@ConfigurationParameter(key = "cucumber.glue", value = "com.automation.ecommerceBDD.stepdefinitions")
@ConfigurationParameter(key = "cucumber.publish.enabled", value = "false")
@ConfigurationParameter(key = "cucumber.filter.tags", value = "@RegressionUITest and not @Skip")
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value =
                "pretty, " +
                        "html:target/cucumber-reports/index.html, " +
                        "json:target/cucumber-reports/cucumber.json, " +
                        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
)
@ConfigurationParameter(key = EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false")
public class AuthRunner {

}