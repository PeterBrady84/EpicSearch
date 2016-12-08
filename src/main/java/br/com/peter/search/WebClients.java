package br.com.peter.search;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

public class WebClients {
	
	private WebClient webClient;
	
	public void getWebClient() {
	
		//Initialising Webclient Object to imitate chrome browser
		webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
	}
	
	public void closeWebClient() {
		webClient.close();
	}
}