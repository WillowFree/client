package fr.canalplus.client;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:scenarios",
        plugin = {"html:target/cucumber", "json:target/cucumber/cucumber.json", "junit:target/cucumber/cucumber.xml"},
        glue = "fr.canalplus.client.etapes",
        tags = "not @wip"
)
public class RunCukes {

}
