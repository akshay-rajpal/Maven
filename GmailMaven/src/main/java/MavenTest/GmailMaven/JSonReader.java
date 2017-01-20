package MavenTest.GmailMaven;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JSonReader {

	public String[] getData(String jsonPath) throws JsonIOException,
			JsonSyntaxException, FileNotFoundException {
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(new FileReader(jsonPath));

		String email = object.get("email").getAsString();
		String password = object.get("password").getAsString();
		String searchText = object.get("searchText").getAsString();
		String mailTo = object.get("mailTo").getAsString();
		String subjectDraft = object.get("subjectDraft").getAsString();
		String mailFrom = object.get("mailFrom").getAsString();
		String starSubject = object.get("starSubject").getAsString();

		String[] testData = { email, password, searchText, mailTo,
				subjectDraft, mailFrom, starSubject };
		return testData;
	}
}
