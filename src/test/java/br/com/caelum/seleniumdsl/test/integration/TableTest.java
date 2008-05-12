package br.com.caelum.seleniumdsl.test.integration;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.seleniumdsl.Page;
import br.com.caelum.seleniumdsl.table.Table;
import br.com.caelum.seleniumdsl.table.layout.FullTableLayout;
import br.com.caelum.seleniumdsl.table.layout.PlainPlusThTableLayout;
import br.com.caelum.seleniumdsl.table.layout.PlainTableLayout;

public class TableTest extends SeleniumTestCase {
	private Page page;

	private Table plain;
	private Table plainTh;
	private Table full;

	@Override
	public void setUp() {
		super.setUp();
		page = browser.open("table.html");

		plain = page.table("plain");
		plainTh = page.table("plain_with_th");
		full = page.table("full");
	}

	@Test
	public void testTableLayoutType() {
		Assert.assertTrue(plain.getLayout().getClass().equals(PlainTableLayout.class));
		Assert.assertTrue(plainTh.getLayout().getClass().equals(PlainPlusThTableLayout.class));
		Assert.assertTrue(full.getLayout().getClass().equals(FullTableLayout.class));
	}

	@Test
	public void testRowCount() {
		Assert.assertEquals(plain.getRowCount(), 5);
		Assert.assertEquals(plainTh.getRowCount(), 5);
		Assert.assertEquals(full.getRowCount(), 5);
	}

	@Test
	public void testContentCount() {
		Assert.assertEquals(plain.getContentCount(), 4);
		Assert.assertEquals(plainTh.getContentCount(), 4);
		Assert.assertEquals(full.getContentCount(), 3);
	}

	@Test
	public void testHeaderLabel() {
		Assert.assertEquals(plain.getRow(1).get(1).headerValue(), "header_1");
		Assert.assertEquals(plainTh.getRow(1).get(1).headerValue(), "header_1");
		Assert.assertEquals(full.getRow(1).get(1).headerValue(), "header_1");
	}

	@Test
	public void testHeaderLinkLabel() {
		Assert.assertEquals(plain.getRow(1).get(2).headerLinkValue(), "header_2");
		Assert.assertEquals(plainTh.getRow(1).get(2).headerLinkValue(), "header_2");
		Assert.assertEquals(full.getRow(1).get(2).headerLinkValue(), "header_2");
	}

	@Test
	public void testGetValue() {
		Assert.assertEquals(plain.getRow(2).get(1).value(), "cell_1_1");
		Assert.assertEquals(plainTh.getRow(2).get(1).value(), "cell_1_1");
		Assert.assertEquals(full.getRow(2).get(1).value(), "cell_1_1");
	}

	@Test
	public void testCheck() {
		Assert.assertTrue(plain.getRow(2).get(3).check().checked());
		Assert.assertTrue(plainTh.getRow(2).get(3).check().checked());
		Assert.assertTrue(full.getRow(2).get(3).check().checked());
	}

	@Test
	public void testUnheck() {
		Assert.assertFalse(plain.getRow(2).get(3).uncheck().checked());
		Assert.assertFalse(plainTh.getRow(2).get(3).uncheck().checked());
		Assert.assertFalse(full.getRow(2).get(3).uncheck().checked());
	}
}
