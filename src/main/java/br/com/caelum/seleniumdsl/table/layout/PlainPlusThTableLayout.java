package br.com.caelum.seleniumdsl.table.layout;

import com.thoughtworks.selenium.Selenium;

public class PlainPlusThTableLayout implements TableLayout {

	private TableLayoutHelper helper;

	public PlainPlusThTableLayout(Selenium selenium, String id, String type) {
		helper = new TableLayoutHelper(selenium, id, type);
	}

	public int getContentCount() {
		return getRowCount() - 1;
	}

	public int getColCount() {
		return helper.countXPath("/tbody/tr/th");
	}

	public String getHeaderText(int col) {
		return helper.getXPathText("/*/tr[1]/th[" + col + "]");
	}

	public String getHeaderLinkText(int col) {
		return helper.getXPathText("/*/tr[1]/th[" + col + "]/a/text()");
	}

	public String value(int row, int col) {
		return helper.getXPathText("/*/tr[" + row + "]/td[" + col + "]");
	}

	public int getRowCount() {
		return helper.getRowCount();
	}
}
