package br.com.caelum.seleniumdsl.test.table;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.seleniumdsl.table.Table;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;

public class FullTableTest {
	private static final String BASE_COUNT_PATH = "//table[@id='id']/";
	private static final String BASE_TEXT_PATH = "xpath=//table[@id='id']/";

	private Selenium mock;
	private Mockery mockery;
	private Table table;

	@Before
	public void setUp() {
		mockery = new Mockery();
		mock = mockery.mock(Selenium.class);

		mockery.checking(new Expectations() {
			{
				String testThead = "id('%s')/thead";
				String testTh = "id('%s')//th";

				one(mock).getXpathCount(String.format(testThead, "id"));
				will(returnValue(1));

				one(mock).getXpathCount(String.format(testTh, "id"));
				will(returnValue(3));

			}
		});

		table = new Table(mock, "id");
	}

	@Test
	public void testRowCount() {
		mockery.checking(new Expectations() {
			{
				String rowCount = BASE_COUNT_PATH + "/*/tr";

				one(mock).getXpathCount(rowCount);
				will(returnValue(5));
			}
		});

		Assert.assertEquals(table.getRowCount(), 5);
		mockery.assertIsSatisfied();
	}

	@Test
	public void testContentCount() {
		mockery.checking(new Expectations() {
			{
				String contentCount = BASE_COUNT_PATH + "/tbody/tr";

				one(mock).getXpathCount(contentCount);
				will(returnValue(3));
			}
		});

		Assert.assertEquals(table.getContentCount(), 3);
		mockery.assertIsSatisfied();
	}

	@Test
	public void testColCount() {
		mockery.checking(new Expectations() {
			{
				String colCount = BASE_COUNT_PATH + "/thead/tr/th";
				one(mock).getXpathCount(colCount);
				will(returnValue(3));
			}
		});

		Assert.assertEquals(table.getColCount(), 3);
		mockery.assertIsSatisfied();
	}

	@Test
	public void testFindColumnWithTHead() {
		mockery.checking(new Expectations() {
			{
				String colCount = BASE_COUNT_PATH + "/thead/tr/th";
				one(mock).getXpathCount(colCount);
				will(returnValue(1));

				String headerValue = BASE_TEXT_PATH + "/thead/tr[1]/th[1]";
				one(mock).getText(headerValue);
				will(returnValue("columnName"));
			}
		});

		Assert.assertEquals(table.findColumn("columnName"), new Integer(1));
		mockery.assertIsSatisfied();
	}

	@Test
	public void testFindColumnWithoutTHead() {
		mockery.checking(new Expectations() {
			{
				String colCount = BASE_COUNT_PATH + "/thead/tr/th";
				one(mock).getXpathCount(colCount);
				will(returnValue(1));

				String headerValue = BASE_TEXT_PATH + "/thead/tr[1]/th[1]";
				one(mock).getText(headerValue);
				will(throwException(new SeleniumException("mocked exception")));

				String value = BASE_TEXT_PATH + "/*/tr[0]/td[1]";
				one(mock).getText(value);
				will(returnValue("columnName"));
			}
		});

		Assert.assertEquals(table.findColumn("columnName"), new Integer(1));
		mockery.assertIsSatisfied();
	}
}
