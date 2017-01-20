package MavenTest.GmailMaven;

import java.io.FileNotFoundException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

@Listeners(TestReport.class)
public class TestStarMail {
  
	GmailHomePage home;
	
	@Test(priority=3)
	@Parameters("jsonPath")
	public void starTest(String jsonPath) throws InterruptedException, JsonIOException, JsonSyntaxException, FileNotFoundException {
	  
		String[] testData = new JSonReader().getData(jsonPath); 
		String mailFrom = testData[5];
		String subject = testData[6];
		home = TestDraftMail.home;
		//home.click_star();
		home.verify_star(mailFrom,subject);
	    home.logout();
  }
	
/*	@AfterMethod
	public void Screenshot(ITestResult result) throws IOException{
		if(result.getStatus()==ITestResult.FAILURE)
		home.captureScreenShot("star");
	}*/
	
	@AfterSuite
	public void close(){
		
		home.closeBrowser();
	}

  

}
