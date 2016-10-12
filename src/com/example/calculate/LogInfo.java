package com.example.calculate;

import android.util.Log;

public class LogInfo {

	public static void printInfo(String info) {
		Log.i("caculate", "Info:" + info);
	}

	public static void printError(String error) {
		Log.e("caculate", "Error:" + error);
	}

	public static void printExStack(String stackName, ExStack _exStack) {
		Log.i("caculate", "Content of " + stackName + ":");
		StringBuffer stackStr = new StringBuffer("");
		String[] dataStr = _exStack.getDataArray();
		for (int i = 0; i < _exStack.size(); i++) {
			stackStr.append(" " + dataStr[i]);
		}
		Log.i("caculate", stackStr.toString());
	}
}
