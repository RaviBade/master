package utilities;

import java.io.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class CaptureScreenshot {

	public static int counter=0;
	public static void CaptureScreenshotUtility(WebDriver driver, String screenName) throws Exception {
		
		try {
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./src/test/resources/screenshots/"+screenName+"00"+Integer.toString(counter)+".png"));
			counter++;
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
	}

}
