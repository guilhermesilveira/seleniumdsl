package br.com.caelum.seleniumdsl;

import com.thoughtworks.selenium.Selenium;

public class Browser {

	private final Selenium selenium;

	private final int timeout;

	public Browser(Selenium selenium) {
		this(selenium, 10000);
	}

	public Browser(Selenium selenium, int timeout) {
		this.selenium = selenium;
		this.timeout = timeout;
	}

	public Page open(String url) {
		selenium.open(url);
		selenium.waitForPageToLoad(Integer.toString(timeout));
		return currentPage();
	}

	public Page currentPage() {
		return new Page(selenium, timeout);
	}

}
