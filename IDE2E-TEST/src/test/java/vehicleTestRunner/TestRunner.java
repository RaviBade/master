package vehicleTestRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features="./src/test/features",
				 glue="vehicleStepDefinitions",
				 tags= {"@Feature01"},
				 monochrome=true,
				 dryRun=false,
				 strict=true,
				 plugin= {"pretty","html:target/cucumber-pretty", "json:target/cucumber.json"})

public class TestRunner {
	
	
}
