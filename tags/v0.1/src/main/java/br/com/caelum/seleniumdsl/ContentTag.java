package br.com.caelum.seleniumdsl;

import com.thoughtworks.selenium.Selenium;

public class ContentTag {

	private final Selenium selenium;
	private final String id;

	public ContentTag(Selenium selenium, String id) {
		this.selenium = selenium;
		this.id = id;
	}

	/**
	 * @param content
	 *            the string to be searched
	 * @return true if the tag contains the string
	 */
	public boolean contains(String content) {
		return selenium.getText(id).contains(content);
	}

	/**
	 * @return if this element exists on the page
	 */
	public boolean exists() {
		return selenium.isElementPresent(id);
	}

	public String innerHTML() {
		return selenium.getText(id);
	}

}
