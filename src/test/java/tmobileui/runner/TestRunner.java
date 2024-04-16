package tmobileui.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features={"classpath:featureFiles/Zadanie2.feature"},
        glue={"classpath:tmobileui/stepdef"}
)

public class TestRunner {
}