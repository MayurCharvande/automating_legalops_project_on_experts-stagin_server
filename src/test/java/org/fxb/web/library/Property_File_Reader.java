package org.fxb.web.library;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Property_File_Reader 
{
	public static String projectConfigurationReader(String ConfigValue) throws IOException
	{
		File f = new File("C:\\Mayur Automation Practice\\GitHub Expert_Staging Server Code\\automating_legalops_project_on_experts-stagin_server\\Configuration\\RestrictionPageCrediantials.properties");
		//(personal system path) C:\\Mayur Automation Practice\\GitHub Expert_Staging Server Code\\automating_legalops_project_on_experts-stagin_server\\Configuration\\RestrictionPageCrediantials.properties
		// (office system path) C:\Users\Fxbytes\eclipse-workspace\Fxbytes_Experts-Staging\Configuration\RestrictionPageCrediantials.properties
			
		FileReader fr = new FileReader(f);
		Properties prop = new Properties();
		prop.load(fr);
		return prop.get(ConfigValue).toString();
	}
}
