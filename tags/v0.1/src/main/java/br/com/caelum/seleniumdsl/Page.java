package br.com.caelum.seleniumdsl;

import br.com.caelum.seleniumdsl.table.Table;

import com.thoughtworks.selenium.Selenium;

public class Page {

	private final Selenium selenium;
	private final int timeout;

	public Page(Selenium selenium, int timeout) {
		this.selenium = selenium;
		this.timeout = timeout;
	}

	public String getText(String xpath) {
		return selenium.getText(xpath);
	}

	public Form form(String id) {
		return new Form(selenium, timeout, id.equals("") ? "" : id + ".");
	}

	public ContentTag div(String id) {
		return new ContentTag(selenium, id);
	}

	public ContentTag span(String id) {
		return new ContentTag(selenium, id);
	}

	public Table table(String id) {
		return new Table(selenium, id);
	}

	public Page click(String link) {
		selenium.click(link);
		selenium.waitForPageToLoad(Integer.toString(timeout));
		return this;
	}

	public Page clickDontWait(String link) {
		selenium.click(link);
		return this;
	}

	public boolean hasLink(String link) {
		return selenium.isTextPresent(link);
	}

	public boolean isFilled(String textBoxId, String value) {
		return selenium.getValue(textBoxId).equals(value);
	}

	public Page check(String checkbox) {
		selenium.click(checkbox);
		return this;
	}

}
