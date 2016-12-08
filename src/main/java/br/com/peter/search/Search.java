package br.com.peter.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Search {

	private List<Result> searchRes = new ArrayList<Result>();
	private List<String> titles;
	private List<String> urls;
	private List<String> descs;
	private HtmlPage newPage;

	public WebClient openWebClient() throws IOException {
		WebClient webClient = new WebClient();
		webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);

		return webClient;
	}

	public List<Result> searchGoogle(WebClient webClient, String query)
			throws IOException {

		titles = new ArrayList<String>();
		urls = new ArrayList<String>();
		descs = new ArrayList<String>();

		String searchString = query;
		HtmlPage page = webClient.getPage("https://www.google.ie");

		// Getting form from Google home page. tsf is the form name
		HtmlForm form = page.getHtmlElementById("tsf");

		// Setting query value in search box in google search home page
		form.getInputByName("q").setValueAttribute(searchString);

		// Creating a virtual Submit Button
		HtmlButton submitButton = (HtmlButton) page.createElement("button");
		submitButton.setAttribute("type", "submit");
		form.appendChild(submitButton);

		// Submitting the form and getting the result
		newPage = submitButton.click();

		// System.out.println(newPage.asXml());

		// Getting the result as text
		String pageText = newPage.asText();
		String[] results = pageText.split("\n");

		// Getting the lines which contains the number of results value.
		for (int i = 0; i < results.length; i++) {
			if (results[i].contains("About") && results[i].contains("results")) {
				System.out.println(searchString + "-----" + results[i]);
			}
		}

		// Get the title of all search results
		@SuppressWarnings("unchecked")
		List<DomElement> titleDiv = (List<DomElement>) newPage
				.getByXPath("//div[@class='g']/h3[@class='r']/a");
		int j = 1;
		for (DomElement titleElements : titleDiv) {
			// System.out.println("Google Title " + j + " : " +
			// titleElements.asText());
			if (titleElements.asText().contains("Images for " + query)) {
			} else if (titleElements.asText().contains("Google Books Result")) {
			} else {
				titles.add(titleElements.asText());
			}
			j++;
		}
		// Get the url of all search results
		@SuppressWarnings("unchecked")
		List<DomElement> urlDiv = (List<DomElement>) newPage
				.getByXPath("//div[@class='kv']/cite");
		j = 0;
		for (DomElement elements : urlDiv) {
			// System.out.println("Google Link " + j + " : " +
			// elements.asText());
			urls.add(elements.asText());
			j++;
		}
		// Get the description of all search results
		@SuppressWarnings("unchecked")
		List<DomElement> descDiv = (List<DomElement>) newPage
				.getByXPath("//div[@class='g']/div[@class='s']/span[@class='st']");
		j = 0;
		for (DomElement elements : descDiv) {
			// System.out.println("Google Desc " + j + " : " +
			// elements.asText());
			descs.add(elements.asText());
			j++;
		}

		for (int i = 0; i < titles.size(); i++) {
			Result r = new Result(titles.get(i), urls.get(i), descs.get(i),
					"google.png");
			searchRes.add(r);
		}
		return searchRes;
	}

	public List<Result> searchYahoo(WebClient webClient, String query)
			throws IOException {

		titles = new ArrayList<String>();
		urls = new ArrayList<String>();
		descs = new ArrayList<String>();

		String searchString = query;
		HtmlPage page = webClient.getPage("https://ie.yahoo.com/");

		// Getting form from Google home page. tsf is the form name
		HtmlForm form = page.getHtmlElementById("UHSearch");

		// Setting query value in search box in google search home page
		form.getInputByName("p").setValueAttribute(searchString);

		// Creating a virtual Submit Button
		HtmlButton submitButton = (HtmlButton) page.createElement("button");
		submitButton.setAttribute("type", "submit");
		form.appendChild(submitButton);

		// Submitting the form and getting the result
		newPage = submitButton.click();

		// System.out.println(newPage.asXml());

		// Getting the lines which contains the number of results value.
		DomElement results = (DomElement) newPage.getByXPath(
				"//div[@class='compPagination']//span").get(0);
		System.out.println(searchString + "-----" + results.asText());

		// Get the title of all search results
		@SuppressWarnings("unchecked")
		List<DomElement> titleDiv = (List<DomElement>) newPage
				.getByXPath("//div[@class='compTitle options-toggle']//h3[@class='title']");
		int j = 1;
		for (DomElement titleElements : titleDiv) {
			// System.out.println("Yahoo Title " + j + " : " +
			// titleElements.asText());
			titles.add(titleElements.asText());
			j++;
		}
		// Get the url of all search results
		@SuppressWarnings("unchecked")
		List<DomElement> urlDiv = (List<DomElement>) newPage
				.getByXPath("//div[@class='compTitle options-toggle']//h3[@class='title']//a");
		j = 1;
		for (DomElement elements : urlDiv) {
			// System.out.println("Yahoo Link " + j + " : " +
			// elements.getAttribute("href"));
			urls.add(elements.asText());
			j++;
		}
		// Get the description of all search results
		@SuppressWarnings("unchecked")
		List<DomElement> descDiv = (List<DomElement>) newPage
				.getByXPath("//div[@class='compText aAbs']");
		j = 1;
		for (DomElement elements : descDiv) {
			// System.out.println("Yahoo Desc " + j + " : " +
			// elements.asText());
			descs.add(elements.asText());
			j++;
		}

		for (int i = 0; i < titles.size(); i++) {
			Result r = new Result(titles.get(i), urls.get(i), descs.get(i),
					"yahoo.png");
			searchRes.add(r);
		}
		return searchRes;
	}

	public List<Result> searchBing(WebClient webClient, String query)
			throws IOException {

		titles = new ArrayList<String>();
		urls = new ArrayList<String>();
		descs = new ArrayList<String>();

		String searchString = query;
		HtmlPage page = webClient.getPage("http://www.bing.com/");

		// Getting form from Google home page. sb_form is the form name
		HtmlForm form = page.getHtmlElementById("sb_form");

		// Setting query value in search box in bing search home page
		form.getInputByName("q").setValueAttribute(searchString);

		// Creating a virtual Submit Button
		HtmlButton submitButton = (HtmlButton) page.createElement("button");
		submitButton.setAttribute("type", "submit");
		form.appendChild(submitButton);

		// Submitting the form and getting the result
		newPage = submitButton.click();

		// System.out.println(newPage.asXml());

		// Getting the lines which contains the number of results value.
		DomElement results = (DomElement) newPage.getByXPath(
				"//span[@class='sb_count']").get(0);
		System.out.println(searchString + "-----" + results.asText());

		// Get the title of all search results
		@SuppressWarnings("unchecked")
		List<DomElement> titleDiv = (List<DomElement>) newPage
				.getByXPath("//li[@class='b_algo']//h2//a");
		int j = 1;
		for (DomElement titleElements : titleDiv) {
			// System.out.println("Bing Title " + j + " : " +
			// titleElements.asText());
			titles.add(titleElements.asText());
			j++;
		}
		// Get the url of all search results
		@SuppressWarnings("unchecked")
		List<DomElement> urlDiv = (List<DomElement>) newPage
				.getByXPath("//li[@class='b_algo']//h2//a");
		j = 1;
		for (DomElement elements : urlDiv) {
			// System.out.println("Bing Link " + j + " : " +
			// elements.getAttribute("href"));
			urls.add(elements.asText());
			j++;
		}
		// Get the description of all search results
		@SuppressWarnings("unchecked")
		List<DomElement> descDiv = (List<DomElement>) newPage
				.getByXPath("//li[@class='b_algo']//div[@class='b_caption']");
		j = 1;
		for (DomElement elements : descDiv) {
			// System.out.println("Bing Desc " + j + " : " + elements.asText());
			descs.add(elements.asText());
			j++;
		}

		for (int i = 0; i < titles.size(); i++) {
			Result r = new Result(titles.get(i), urls.get(i), descs.get(i),
					"bing.png");
			searchRes.add(r);
		}
		return searchRes;
	}

	public List<Result> searchDuckDuckGo(WebClient webClient, String query)
			throws IOException {

		titles = new ArrayList<String>();
		urls = new ArrayList<String>();
		descs = new ArrayList<String>();

		String searchString = query;
		HtmlPage page = webClient.getPage("https://duckduckgo.com/");

		// Getting form from Google home page. sb_form is the form name
		HtmlForm form = page.getHtmlElementById("search_form_homepage");

		// Setting query value in search box in bing search home page
		form.getInputByName("q").setValueAttribute(searchString);

		// Creating a virtual Submit Button
		HtmlButton submitButton = (HtmlButton) page.createElement("button");
		submitButton.setAttribute("type", "submit");
		form.appendChild(submitButton);

		// Submitting the form and getting the result
		newPage = submitButton.click();

		// System.out.println(newPage.asXml());

		// Getting the lines which contains the number of results value.
		// DomElement results = (DomElement) newPage.getByXPath(
		// "//span[@class='sb_count']").get(0);
		// System.out.println(searchString + "-----" + results.asText());

		// Get the title of all search results
		@SuppressWarnings("unchecked")
		List<DomElement> titleDiv = (List<DomElement>) newPage
				.getByXPath("//h2[@class='result__title']//a");
		int j = 1;
		for (DomElement titleElements : titleDiv) {
			// System.out.println("DuckDuckGo Title " + j + " : " +
			// titleElements.asText());
			titles.add(titleElements.asText());
			j++;
		}
		// Get the url of all search results
		@SuppressWarnings("unchecked")
		List<DomElement> urlDiv = (List<DomElement>) newPage
				.getByXPath("//h2[@class='result__title']//a");
		j = 1;
		for (DomElement elements : urlDiv) {
			// System.out.println("DuckDuckGo Link " + j + " : " +
			// elements.getAttribute("href"));
			urls.add(elements.asText());
			j++;
		}
		// Get the description of all search results
		@SuppressWarnings("unchecked")
		List<DomElement> descDiv = (List<DomElement>) newPage
				.getByXPath("//a[@class='result__snippet']");
		j = 1;
		for (DomElement elements : descDiv) {
			// System.out.println("DuckDuckGo Desc " + j + " : " +
			// elements.asText());
			descs.add(elements.asText());
			j++;
		}

		for (int i = 0; i < titles.size(); i++) {
			Result r = new Result(titles.get(i), urls.get(i), descs.get(i),
					"duckduckgo.png");
			searchRes.add(r);
		}
		return searchRes;
	}

	public List<Result> getSearchRes() {
		return searchRes;
	}

	public void setSearchRes(List<Result> googleRes) {
		this.searchRes = googleRes;
	}

	public List<String> getTitles() {
		return titles;
	}

	public void setTitles(List<String> titles) {
		this.titles = titles;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public List<String> getDescs() {
		return descs;
	}

	public void setDescs(List<String> descs) {
		this.descs = descs;
	}

	// public void getGoogleResults(HtmlPage n) {
	//
	// //Get the title of all search results
	// @SuppressWarnings("unchecked")
	// List<DomElement> titleDiv =
	// (List<DomElement>)n.getByXPath("//div[@class='rc']//h3");
	// for (DomElement elements : titleDiv) {
	// System.out.println("WEB Title " + " : " + elements.asText());
	// titles.add(elements.asText());
	// }
	// @SuppressWarnings("unchecked")
	// List<DomElement> urlDiv =
	// (List<DomElement>)n.getByXPath("//div[@class='kv']//cite");
	// for (DomElement elements : urlDiv) {
	// System.out.println("WEB Link " + " : " + elements.asText());
	// urls.add(elements.asText());
	// }
	// System.out.println("Size : " + urls.size());
	// for (int i = 0; i < titles.size(); i ++) {
	// Google g = new Google(titles.get(i), urls.get(i));
	// System.out.println("Google " + i + " : " + g);
	// googleRes.add(g);
	// }
	// System.out.println("\n");
	// }

	// public List<String> getUrls() {
	//
	// //Get the urls of all search results
	// @SuppressWarnings("unchecked")
	// List<DomElement> div =
	// (List<DomElement>)newPage.getByXPath("//div[@class='kv']//cite");
	// int i =1;
	// for (DomElement elements : div) {
	// System.out.println("WEB Link " + i + " : " + elements.asText());
	// url = elements.asText();
	// urls.add(url);
	// i++;
	// }
	// System.out.println("\n");
	//
	// return urls;
	// }
}