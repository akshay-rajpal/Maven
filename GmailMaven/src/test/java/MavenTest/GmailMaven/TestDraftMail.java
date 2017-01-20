package MavenTest.GmailMaven;

import java.io.FileNotFoundException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

@Listeners(TestReport.class)
public class TestDraftMail {
	
	protected static GmailHomePage home;
  
	@Test(priority=2)
	@Parameters("jsonPath")
  public void draftTest(String jsonPath) throws InterruptedException, JsonIOException, JsonSyntaxException, FileNotFoundException {
	  
	 String[] testData = new JSonReader().getData(jsonPath); 
	 //String mailTo = testData[3];
	 String subject = testData[4];
	 home = TestSearchMail.home;
	 home.click_compose();
	 //home.enter_mailTo();
	 home.enter_subject(subject);
	 home.click_close();
	 home.click_draft_link();
	 home.verify_draft(subject);
  }
	
/*	@AfterMethod
	public void Screenshot(ITestResult result) throws IOException{
		if(result.getStatus()==ITestResult.FAILURE)
		home.captureScreenShot("draft");
	}*/
	
}
