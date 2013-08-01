package com.geneca.codewars.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy object to represent the status of a game
 */
public class GameStatusObject {

	private int data1;
	private String data2;
	private List<String> list = new ArrayList<String>();

	public int getData1() {
		return data1;
	}

	public void setData1(int data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GameStatusObject [data1=" + data1 + ", data2=" + data2
				+ ", list=" + list + "]";
	}

}