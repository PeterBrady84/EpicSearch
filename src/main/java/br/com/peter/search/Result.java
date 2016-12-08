package br.com.peter.search;

public class Result {	
	
	private String title, url, desc, logo;

	public Result() {
	}
	
	public Result(String title, String url, String desc, String logo) {
		this.title = title;
		this.url = url;
		this.desc = desc;
		this.logo = logo;
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