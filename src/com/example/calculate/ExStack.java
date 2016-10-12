package com.example.calculate;

public class ExStack {

	private final static int STACK_LEN = 100;
	private String[] data = null;
	private int top = -1;

	public ExStack() {
		if (null == data) {
			data = new String[STACK_LEN];
		}
		top = -1;
	}

	public boolean isEmpty() {
		return -1 == top;
	}

	public void pop() {
		if (top <= -1) {
			LogInfo.printError("Pop Stack Error.");
		} else {
			// data[top] = null;
			top--;
		}
	}

	public void push(String _data) {
		if (top >= STACK_LEN) {
			LogInfo.printError("Stack Overflow.");
		}
		top++;
		data[top] = _data;
	}

	public String top() {
		if (-1 >= top) {
			LogInfo.printError("Stack is Empty.");
			return null;
		}
		return data[top];
	}

	public int size() {
		return top + 1;
	}

	public void clear() {
		top = -1;
	}

	public String[] getDataArray() {
		return data;
	}
}
