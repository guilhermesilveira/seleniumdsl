package br.com.caelum.seleniumdsl.table;


import com.thoughtworks.selenium.Selenium;

public class Cell {

	private final Table table;
	private final int row;
	private final int col;
	private final Selenium selenium;

	public Cell(Selenium selenium, Table table, int row, int col) {
		this.selenium = selenium;
		this.table = table;
		this.row = row;
		this.col = col;
	}

	public String value() {
		return table.getLayout().value(row, col);
	}

	public String getLink() {
		return selenium.getEval("dom=selenium.page().findElement(\"" + getXPath() + "/a\").href");
	}

	public String headerValue() {
		return table.getLayout().getHeaderText(col);
	}

	public String headerLinkValue() {
		return table.getLayout().getHeaderLinkText(col);
	}

	private String getXPath() {
		return "//table[@" + table.getType() + "='" + table.getId() + "']/*/tr[" + row + "]/td[" + col + "]";
	}

	public Cell check() {
		selenium.check(getXPath() + "/input");
		return this;
	}

	public Cell uncheck() {
		selenium.uncheck(getXPath() + "/input");
		return this;
	}

	public boolean checked() {
		return selenium.isChecked(getXPath() + "/input");
	}

}
