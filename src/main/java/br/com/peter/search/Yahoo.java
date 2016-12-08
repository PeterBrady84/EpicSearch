package br.com.peter.search;

public class Yahoo {	
	
	private String title, url, desc;
	private String logo ="google.png";

	public Yahoo() {
	}
	
	public Yahoo(String title, String url, String desc) {
		this.title = title;
		this.url = url;
		this.desc = desc;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
}