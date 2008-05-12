package br.com.caelum.seleniumdsl.table;

import br.com.caelum.seleniumdsl.search.RowMatcher;
import br.com.caelum.seleniumdsl.search.RowVisitor;
import br.com.caelum.seleniumdsl.search.TableCriteria;
import br.com.caelum.seleniumdsl.table.layout.TableLayout;
import br.com.caelum.seleniumdsl.table.layout.TableLayoutChooser;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;

public class Table {
	private final Selenium selenium;

	private final String id;

	/**
	 * The property that holds this table's key
	 */
	private final String type;

	private final TableLayout layout;

	public Table(Selenium selenium, String id) {
		this(selenium, id, "id");
	}

	public Table(Selenium selenium, String value, String type) {
		this.selenium = selenium;
		this.id = value;
		this.type = type;

		layout = new TableLayoutChooser(selenium, value, type).choose();
	}

	public String getType() {
		return type;
	}

	/**
	 * @param columnIndex
	 *            the index of the column.
	 * @return the br.com.caelum.seleniumdsl.Column object
	 */
	public Column column(int columnIndex) {
		return new Column(this, selenium, columnIndex);
	}

	/**
	 * @param columnName
	 *            the label of the column.
	 * @return the br.com.caelum.seleniumdsl.Column object
	 */
	public Column column(String columnName) {
		return column(findColumn(columnName));
	}

	public int getColCount() {
		return layout.getColCount();
	}

	public int getRowCount() {
		return layout.getRowCount();
	}

	public int getContentCount() {
		return layout.getContentCount();
	}

	public Cell cell(int row, int col) {
		return new Cell(selenium, this, row, col);
	}

	public Cell cell(int row, String col) {
		return new Cell(selenium, this, row, findColumn(col));
	}

	public String getId() {
		return id;
	}

	public boolean exists() {
		return selenium.isElementPresent(getId());
	}

	public void iterate(RowVisitor visitor) {
		int count = getRowCount();
		for (int row = 1; row <= count; row++)
			visitor.visit(new Row(this, selenium, row));
	}

	public Row getRow(Integer row) {
		return new Row(this, selenium, row);
	}

	public Integer findColumn(String columnName) {
		Row row = new Row(this, selenium, 1);
		int colCount = getColCount();
		for (int i = 0; i < colCount; i++) {
			String current;
			try {
				current = row.get(i + 1).headerValue();
			} catch (SeleniumException e) {
				current = row.get(i + 1).value();
			}
			if (columnName.equals(current))
				return i + 1;
		}
		throw new IllegalArgumentException("Column " + columnName + " not found");
	}

	public RowMatcher select(RowMatcher matcher) {
		matcher.setTable(this);
		return matcher;
	}

	public TableCriteria createCriteria() {
		return new TableCriteria(this);
	}

	public TableLayout getLayout() {
		return layout;
	}
}
