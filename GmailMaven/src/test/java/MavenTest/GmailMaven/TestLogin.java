package MavenTest.GmailMaven;

import java.io.FileNotFoundException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

@Listeners(TestReport.class)
public class TestLogin {
	
	GmailLoginPage login;
  
  @Test(priority=0)
  @Parameters("jsonPath")
  public void loginTest(String jsonPath) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
	
	  String[] testData = new JSonReader().getData(jsonPath); 
	  String email = testData[0];
	  String password = testData[1];
	  login = new GmailLoginPage();
	  login.setDriver();
	  login.enterEmail(email);
	  login.clickNext();
	  login.enterPassword(password);
	  login.clickSignIn();
  }
  
/*	@AfterMethod
	public void Screenshot(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE)
		login.captureScreenShot("login");
	}*/
	

}
