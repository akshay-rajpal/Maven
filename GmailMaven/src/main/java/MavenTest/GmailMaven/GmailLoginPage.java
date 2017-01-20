package MavenTest.GmailMaven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailLoginPage {
	

	protected static WebDriver driver;
	private String url = "https://gmail.com/";

	@FindBy(id="Email")
	WebElement mail;

	@FindBy(id="next")
	WebElement next;

	@FindBy(id="Passwd")
	WebElement pass;
	
    @FindBy(id="signIn")
    WebElement signIn;
	
    //setting up the driver to load page and elements
	public void setDriver() {

		driver = new LaunchBrowser().getDriver();
		PageFactory.initElements(driver, this);
		loadUrl();
	
	}

	//loading url and initializing elements
	public void loadUrl() {

		driver.get(url);
		PageFactory.initElements(driver, this);
	}

	//entering email id in email text box
	public void enterEmail(String email) {
		mail.sendKeys(email);
	}

	//clicking next button
	public void clickNext() {

		next.click();
	}

	//entering password in password text box
	public void enterPassword(String password) {

		pass.sendKeys(password);
	}

	//clicking signin button
	public void clickSignIn() {

		signIn.click();

	}
/*
	public void captureScreenShot(String name) throws IOException {
		
		new CaptureScreenShot().takeScreenShot(name);
		
	}*/


}
