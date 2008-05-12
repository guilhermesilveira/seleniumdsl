package br.com.caelum.seleniumdsl.test;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.seleniumdsl.Page;
import br.com.caelum.seleniumdsl.SelectField;

import com.thoughtworks.selenium.Selenium;

public class SelectFieldTest {
	private Selenium mock;
	private Mockery mockery;
	private SelectField field;

	@Before
	public void setUp() {
		mockery = new Mockery();
		mock = mockery.mock(Selenium.class);
		field = new Page(mock, 1).form("id").selectField("id");
	}

	@Test
	public void testChoose() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).select(with(any(String.class)), with(any(String.class)));
			}
		});
		field.choose("content");
		mockery.assertIsSatisfied();
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testChooseByIndex() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).getSelectOptions(with(any(String.class)));
				exactly(1).of(mock).select(with(any(String.class)), with(any(String.class)));
			}
		});
		field.chooseByIndex(1);
		mockery.assertIsSatisfied();
	}

	@Test
	public void testValue() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).getValue(with(any(String.class)));
			}
		});
		field.value();
		mockery.assertIsSatisfied();
	}
}