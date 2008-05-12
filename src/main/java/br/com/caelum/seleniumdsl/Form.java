package br.com.caelum.seleniumdsl;

import com.thoughtworks.selenium.Selenium;

public class Form {

	private final String id;
	private final Selenium selenium;
	private int timeout;

	// FIXME: é suposto que id venha ou vazio, ou da forma "<id>."
	public Form(Selenium selenium, int timeout, String id) {
		this.selenium = selenium;
		this.timeout = timeout;
		this.id = id;
	}

	public Field field(String fieldName) {
		return new Field(selenium, this, id + fieldName);
	}

	public void click(String buttonLabel) {
		selenium.click(buttonLabel);
		selenium.waitForPageToLoad(Integer.toString(timeout));
	}

	public SelectField selectField(String fieldName) {
		return new SelectField(selenium, this, id + fieldName);
	}

	public Form check(String checkbox) {
		selenium.check(checkbox);
		return this;
	}

	public Form uncheck(String checkbox) {
		selenium.uncheck(checkbox);
		return this;
	}

	public boolean isChecked(String checkbox) {
		return selenium.getEval("selenium.page().findElement(\"" + checkbox + "\").checked").equals("true");
	}

	public void submit() {
		selenium.submit(id == "" ? "//form" : id.substring(0, id.length() - 1));
		selenium.waitForPageToLoad(Integer.toString(timeout));
	}

}
