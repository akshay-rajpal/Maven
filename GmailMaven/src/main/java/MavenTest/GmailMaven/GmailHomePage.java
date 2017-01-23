package MavenTest.GmailMaven;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class GmailHomePage {

	private WebDriver driver;

	@FindBy(xpath = "//input[@aria-label='Search']")
	WebElement searchBox;

	@FindBy(xpath = "//*[@aria-label='Search Gmail']")
	WebElement searchButton;

	@FindBy(className = "z0")
	WebElement compose;

	@FindBy(name = "to")
	WebElement mailTo;

	@FindBy(name = "subjectbox")
	WebElement subjectBox;

	@FindBy(xpath = "//*[@aria-label='Save & Close']")
	WebElement closeButton;

	@FindBy(xpath = "//*[@href='https://mail.google.com/mail/#drafts']")
	WebElement draftLink;

	@FindBy(xpath = "//div[@role='main']/div/div/div/table/tbody/tr[1]/td[8]")
	WebElement draft;

	@FindBy(xpath = "//div[@role='main']/div/div/div/table/tbody/tr[1]/td[6]")
	WebElement draftSubject;

	@FindBy(xpath = " //*[@href='https://mail.google.com/mail/#inbox']")
	WebElement inboxLink;

	@FindBy(xpath = "//*[@href='https://mail.google.com/mail/#starred']")
	WebElement starLink;

	@FindBy(xpath = "//div[@role='main']/div/div/div/table/tbody/tr[1]/td[4]")
	WebElement starMailFrom;

	@FindBy(xpath = "//*[@id='gb']/div[1]/div[1]/div[2]/div[4]/div")
	WebElement accountButton;

	@FindBy(xpath = "//*[@id='gb_71']")
	WebElement signoutButton;

	// setting driver from this page by getting it from LoginPage
	public void setDriver() {

		driver = GmailLoginPage.driver;
		PageFactory.initElements(driver, this);
	}

	public void enter_search_text(String searchText) {

		searchBox.sendKeys(searchText);
	}

	public void click(WebElement element) {

		element.click();
	}

	public void click_search_button() {

		try {
			click(searchButton);
		} catch (Exception e) {
			System.out.println("Search button didnt work...try again");
		}
	}

	public void click_compose() {

		click(compose);
	}

	public void enter_subject(String subject) {

		subjectBox.sendKeys(subject);
	}

	public void click_close() {

		click(closeButton);
	}

	public void click_draft_link() {

		try {
			click(draftLink);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * public void click_star() {
	 * 
	 * click(inboxLink);
	 * 
	 * click(star); }
	 */

	public void click_star_link() {

		click(starLink);
	}

	// verifying that drafted mail is in draft folder
	public void verify_draft(String subject) throws InterruptedException {

		Date date = new Date();
		DateFormat format = new SimpleDateFormat("h:mm");

		String draftTime = draft.getText();
		String systemTime = format.format(date) + " pm";

		Thread.sleep(2000);
		if (draftSubject.getText().contains("(no subject)")) {
			System.out.println(draftTime + " " + systemTime);
			Assert.assertEquals(draftTime, systemTime, "draft verified");
		} else {
			Assert.assertEquals(draftSubject.getText(), subject);

			System.out.println("\nDraft with subject " + subject
					+ " moved to draft folder");
		}

		// System.out.println("Draft verified");
	}

	// verifying that starred mail is in star folder
	public void verify_star(String mailFrom, String subject)
			throws InterruptedException {

		click(inboxLink);

		int numberOfStarredMailInbox = get_starred_mails_in_inbox(mailFrom,
				subject);

		click(starLink);

		int numberOfStarredMail = get_starred_mails_in_star_folder(mailFrom,
				subject);

		Assert.assertEquals(numberOfStarredMail, numberOfStarredMailInbox);
		if (numberOfStarredMail == 0) {

			System.out.println("\nThere was no starred mail to verify");
		}
	}

	// Searching and getting number of starred mails in inbox with searched
	// details
	public int get_starred_mails_in_inbox(String name, String subject)
			throws InterruptedException {

		// checking number of mails in inbox
		int mailsInInbox = driver
				.findElements(
						By.xpath("//div[@role='main']/div/div/div/div[@class='Cp']/div/table/tbody/tr"))
				.size();

		int numberOfStarredMailInbox = 0;
		int alreadyStarredMails = 0;
		for (int i = 1; i <= mailsInInbox; i++) {

			WebElement mailFrom = driver
					.findElement(By
							.xpath("//div[@role='main']/div/div/div/div[@class='Cp']/div/table/tbody/tr["
									+ i + "]/td[4]"));
			WebElement mailSubject = driver
					.findElement(By
							.xpath("//div[@role='main']/div/div/div/div[@class='Cp']/div/table/tbody/tr["
									+ i + "]/td[6]"));
			WebElement star1 = driver
					.findElement(By
							.xpath("//div[@role='main']/div/div/div/div[@class='Cp']/div/table/tbody/tr["
									+ i + "]/td[3]/span"));

			// checking the mails with searched name and subject
			if (mailFrom.getText().equalsIgnoreCase(name)
					&& mailSubject.getText().startsWith(subject)) {

				String starStatus = star1.getAttribute("title");

				// checking whether the searched mail is starred already or not
				if (starStatus.equalsIgnoreCase("not starred")) {
					star1.click();
				} else {
					alreadyStarredMails++;
				}
				numberOfStarredMailInbox++;
			}
		}
		if (numberOfStarredMailInbox == 0) {
			System.out.println("\nThere was no mail from " + name
					+ " with subject " + subject);
		} else if (alreadyStarredMails == numberOfStarredMailInbox) {
			System.out.println("\nAll " + numberOfStarredMailInbox
					+ " mails in inbox from " + name + " with subject "
					+ subject + " were starred already");
		} else if (alreadyStarredMails == 0) {
			System.out.println("\nNo mail from " + name + " with subject "
					+ subject + " was starred");

			System.out.println("\nNumber of starred mails in inbox from "
					+ name + " with subject " + subject + " is:"
					+ numberOfStarredMailInbox);
		} else {
			System.out.println("\n" + alreadyStarredMails + " mail(s) from "
					+ name + " with subject " + subject + " was starred");

			System.out.println("\nNumber of starred mails in inbox from "
					+ name + " with subject " + subject + " is:"
					+ numberOfStarredMailInbox);
		}
		return numberOfStarredMailInbox;
	}

	// Searching and getting number of starred mails in star folder with
	// searched details
	public int get_starred_mails_in_star_folder(String name, String subject) {

		int numberOfStarredMail = 0;
		int mailsInStarFolder = driver.findElements(
				By.xpath("//div[@role='main']/div/div/div/table/tbody/tr"))
				.size();

		for (int i = 1; i <= mailsInStarFolder; i++) {

			WebElement mailFrom = driver.findElement(By
					.xpath("//div[@role='main']/div/div/div/table/tbody/tr["
							+ i + "]/td[4]"));

			WebElement mailSubject = driver.findElement(By
					.xpath("//div[@role='main']/div/div/div/table/tbody/tr["
							+ i + "]/td[6]"));

			int index = mailSubject.getText().indexOf(subject);
			if (mailFrom.getText().equalsIgnoreCase(name)
					&& mailSubject.getText().startsWith(subject, index)) {

				numberOfStarredMail++;
			}

		}
		if (numberOfStarredMail != 0)
			System.out.println("\nNumber of starred mails in star folder from "
					+ name + " with subject " + subject + " is:"
					+ numberOfStarredMail);

		return numberOfStarredMail;
	}

	public void logout() {

		click(accountButton);
		click(signoutButton);
		System.out.println("\nlogged out");
	}

	// closing the browser
	public void closeBrowser() {

		driver.quit();

	}

}
