package br.com.caelum.seleniumdsl.table.layout;

import com.thoughtworks.selenium.Selenium;

public class FullTableLayout implements TableLayout {

	private TableLayoutHelper helper;

	public FullTableLayout(Selenium selenium, String id, String type) {
		helper = new TableLayoutHelper(selenium, id, type);
	}

	public int getContentCount() {
		return helper.countXPath("/tbody/tr");
	}

	public int getColCount() {
		return helper.countXPath("/thead/tr/th");
	}

	public String getHeaderText(int col) {
		return helper.getXPathText("/thead/tr[1]/th[" + col + "]");
	}

	public String getHeaderLinkText(int col) {
		return helper.getXPathText("/thead/tr[1]/th[" + col + "]/a/text()");
	}

	public String value(int row, int col) {
		return helper.getXPathText("/*/tr[" + (row - 1) + "]/td[" + col + "]");
	}

	public int getRowCount() {
		return helper.getRowCount();
	}
}
