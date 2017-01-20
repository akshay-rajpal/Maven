package MavenTest.GmailMaven;

import java.io.FileNotFoundException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

@Listeners(TestReport.class)
public class TestSearchMail {
	
	protected static GmailHomePage home;
  
	@Test(priority=1)
	@Parameters("jsonPath")
  public void searchTest(String jsonPath) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
	  
	  String[] testData = new JSonReader().getData(jsonPath);
	  String searchText = testData[2];
	  home = new GmailHomePage();
	  home.setDriver();
	  home.enter_search_text(searchText);
	  home.click_search_button();
  }
	
/*	@AfterMethod
	public void Screenshot(ITestResult result) throws IOException{
		if(result.getStatus()==ITestResult.FAILURE)
		home.captureScreenShot("search");
	}*/
}
