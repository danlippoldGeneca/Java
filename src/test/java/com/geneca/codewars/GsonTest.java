package com.geneca.codewars;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import com.geneca.codewars.data.GameCommandObject;
import com.geneca.codewars.data.GameStatusObject;

public class GsonTest {

	private final String json = "{\"data1\":100,\"data2\":\"hello\",\"list\":[\"String 1\",\"String 2\",\"String 3\"]}";

	@Test
	public void testToJSon() {
		GameCommandObject obj = new GameCommandObject();
		obj.setData1(100);
		obj.setData2("hello");
		ArrayList l = new ArrayList<String>();
		l.add("String 1");
		l.add("String 2");
		l.add("String 3");
		obj.setList(l);

		String json2 = JsonConverter.convertToJson(obj);
		Assert.assertEquals(json, json2);
	}

	@Test
	public void testFromJson() {
		GameStatusObject obj = new GameStatusObject();
		obj.setData1(100);
		obj.setData2("hello");
		ArrayList l = new ArrayList<String>();
		l.add("String 1");
		l.add("String 2");
		l.add("String 3");
		obj.setList(l);

		GameStatusObject obj2 = JsonConverter.convertFromJson(json);
		System.out.println("Game status object in = " + obj2.toString());
		Assert.assertEquals(obj.toString(), obj2.toString());
	}

	@Test
	public void testTakeTurn() {
		JavaGameShell shell = new JavaGameShell();
		String foundJson = shell.takeTurn(json);

		String expectedJson = "{\"data1\":200,\"data2\":\"COMMAND: hello\",\"list\":[\"String 1\",\"String 2\",\"String 3\"]}";

		Assert.assertEquals(expectedJson, foundJson);
	}
}
