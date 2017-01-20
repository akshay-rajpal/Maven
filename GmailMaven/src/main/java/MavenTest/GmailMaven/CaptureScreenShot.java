package MavenTest.GmailMaven;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenShot {
	
	WebDriver driver;
	
	public void takeScreenShot(String name) throws IOException{
		
		driver = GmailLoginPage.driver;
		TakesScreenshot scrShot = (TakesScreenshot)driver;
		File source = scrShot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("./Screenshots/"+name+".png"));
		System.out.println("screenshot taken");
	}

}
