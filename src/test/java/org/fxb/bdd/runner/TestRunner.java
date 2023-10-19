package org.fxb.bdd.runner;

import org.junit.runner.RunWith;
import org.testng.annotations.BeforeClass;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)				
@CucumberOptions(features="C:\\Users\\Fxbytes\\eclipse-workspace\\Fxbytes_Experts-Staging\\Feature\\MyTest.feature",glue={"StepDefinition"})
public class TestRunner 
{
	@BeforeClass
	public static void generateRunner()
	{
		
	}
}
