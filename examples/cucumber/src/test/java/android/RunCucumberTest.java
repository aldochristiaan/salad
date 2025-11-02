package android;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("android")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "android")
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, json:target/report.json, rerun:rerun/failed_scenarios.txt"
)
public class RunCucumberTest {
}
