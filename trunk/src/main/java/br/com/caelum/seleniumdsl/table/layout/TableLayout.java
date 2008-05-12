package br.com.caelum.seleniumdsl.table.layout;

public interface TableLayout {
	/**
	 * @param col
	 *            the column index
	 * @return the text of the column
	 */
	public String getHeaderText(int col);

	/**
	 * Used when the header's text is a link. Common when doing pagination
	 * 
	 * @param col
	 *            the column index
	 * @return the '<a href' tag's text
	 */
	public String getHeaderLinkText(int col);

	/**
	 * @return The tbody rows. Some layouts will remove the first row and return all the others, ignoring if the last row is a footer or no.
	 */
	public int getContentCount();

	/**
	 * @param row
	 *            the row index
	 * @param col
	 *            the col index
	 * @return the text of the cell
	 */
	public String value(int row, int col);

	/**
	 * @return The number of columns on the header.
	 */
	public int getColCount();

	/**
	 * @return The full row count, including possible header and footer.
	 */
	public int getRowCount();
}
