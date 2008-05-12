package br.com.caelum.seleniumdsl.table.layout;

import com.thoughtworks.selenium.Selenium;

public class PlainTableLayout implements TableLayout {

	private TableLayoutHelper helper;

	public PlainTableLayout(Selenium selenium, String id, String type) {
		helper = new TableLayoutHelper(selenium, id, type);
	}

	public int getContentCount() {
		return getRowCount() - 1;
	}

	public int getColCount() {
		return helper.countXPath("/tbody/tr[1]/td");
	}

	public String getHeaderText(int col) {
		return helper.getXPathText("/*/tr[1]/td[" + col + "]");
	}

	public String getHeaderLinkText(int col) {
		return helper.getXPathText("/*/tr[1]/td[" + col + "]/a/text()");
	}

	public String value(int row, int col) {
		return helper.getXPathText("/*/tr[" + row + "]/td[" + col + "]");
	}

	public int getRowCount() {
		return helper.getRowCount();
	}
}
