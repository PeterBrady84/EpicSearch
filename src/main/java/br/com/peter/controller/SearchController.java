package br.com.peter.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.peter.search.Result;
import br.com.peter.search.Search;

import com.gargoylesoftware.htmlunit.WebClient;

@ManagedBean
@SessionScoped
public class SearchController implements Serializable {

	private static final long serialVersionUID = 1L;
	private String query;
	// private Person person = new Person();
	// private PersonSite personSite = new PersonSite();
	private List<Result> searchRes;
	private List<String> urls;
	private String[] searchSites;
	private Boolean[] searchSelected;

	// private List<PersonSite> personSites;

	@PostConstruct
	public void init() {
		searchSites = new String[4];
		searchSites[0] = "Google";
		searchSites[1] = "Yahoo";
		searchSites[2] = "Bing";
		searchSites[3] = "Duck Duck Go";
		searchSelected = new Boolean[searchSites.length];
		for (int i = 0; i < 4; i++) {
			searchSelected[i] = true;
			System.out.println(searchSelected[i]);
		}
	}

	public String getQuery() {
		System.out.println("getQuery" + query);
		return query;
	}

	public void setQuery(String query) {
		System.out.println("setQuery" + query);
		this.query = query;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public String searchQuery() throws IOException {
		for (int i = 0; i < searchSelected.length; i++) {
			System.out.println(searchSelected[i]);
		}
		Search search = new Search();
		WebClient webClient = search.openWebClient();
		if (!searchSelected[0])
			setSearchRes(search.searchGoogle(webClient, query));
		if (!searchSelected[1])
			setSearchRes(search.searchYahoo(webClient, query));
		if (!searchSelected[2])
			setSearchRes(search.searchBing(webClient, query));
		if (!searchSelected[3])
			setSearchRes(search.searchDuckDuckGo(webClient, query));
		List<Result> deleteDupResults = new ArrayList<>(new LinkedHashSet<>(
				searchRes));
		searchRes = deleteDupResults;

		return "/resultsUr.faces";
	}

	public List<Result> getSearchRes() {
		System.out.println("getGoogleRes" + searchRes);
		return searchRes;
	}

	public void setSearchRes(List<Result> searchRes) {
		this.searchRes = searchRes;
	}
	
	public String[] getSearchSites() {
		return searchSites;
	}

	public void setSearchSites(String[] searchSites) {
		this.searchSites = searchSites;
	}

	public Boolean[] getSearchSelected() {
		return searchSelected;
	}

	public void setSearchSelected(Boolean[] searchSelected) {
		this.searchSelected = searchSelected;
	}
}
