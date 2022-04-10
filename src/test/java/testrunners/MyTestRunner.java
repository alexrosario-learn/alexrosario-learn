package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="Features/FRCViaSMS.feature",
		glue = {"stepDefenitions", "AppHooks"},
		dryRun= true, //corresponding feature file is having step definitin or not
		monochrome = true,
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		}
	)

public class MyTestRunner {


}
