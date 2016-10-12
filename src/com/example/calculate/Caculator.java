package com.example.calculate;

import java.math.BigDecimal;

public class Caculator {

	public static final String errorInfo = "Error";
	public static final String infinityInfo = "Infinity";
	public static final String naNInf = "NaN";
	public static final double maxNum = 1.5E16;
	
	// Factorial
	public static String factorial(int n) {
		double resDou = 1;
		if (n > 170) {
			return infinityInfo;
		} else {
			for (int i = 1; i <= n; i++) {
				resDou *= i;
				if (resDou > Double.MAX_VALUE)
					return infinityInfo;
			}
			return Double.toString(resDou);
		}
	}

	// 以10为低
	public static String log(double num) {
		double resDou = Math.log(num) / Math.log(10);
		if( resDou > maxNum){
			return infinityInfo;
		}
		return String.valueOf(resDou);
	}

	// ln指数计算
	public static String ln(double num) {
		double resDou = Math.log(num);
		if( resDou > maxNum){
			return infinityInfo;
		}
		return String.valueOf(resDou);
	}

	// sin: 1，按照弧度计算，2：按照度数计算
	public static String sin(int type, double num) {
		if (1 == type) {
			double resDou = Math.sin(num);
			if( resDou > maxNum){
				return infinityInfo;
			}
			return String.valueOf(resDou);
		} else if (2 == type) {
			double radian = num*Math.PI/180;
			double resDou = Math.sin(radian);
			if( resDou > maxNum){
				return infinityInfo;
			}
			return String.valueOf(resDou);
		} else {
			LogInfo.printError("Wrong Type in Sin:" + num);
			return "";
		}
	}

	// cos
	public static String cos(int type, double num) {
		if (1 == type) {
			double resDou = Math.cos(num);
			if( resDou > maxNum){
				return infinityInfo;
			}
			return String.valueOf(resDou);
		} else if (2 == type) {
			double radian = num * Math.PI / 180;// 角度转化为弧度
			double resDou = Math.cos(radian);
			if( resDou > maxNum){
				return infinityInfo;
			}
			return String.valueOf(resDou);
		} else {
			LogInfo.printError("Wrong Type in Sin:" + type);
			return "";
		}
	}

	// tan
	public static String tan(int type, double num) {
		if (1 == type) {
			double resDou = Math.tan(num);
			if( resDou > maxNum){
				return infinityInfo;
			}
			return String.valueOf(resDou);
		} else if (2 == type) {
			double radian = num * Math.PI / 180;// 角度转化为弧度
			double resDou = Math.tan(radian);
			if( resDou > maxNum){
				return infinityInfo;
			}
			return String.valueOf(resDou);
		} else {
			LogInfo.printError("Wrong Type in Sin:" + type);
			return "";
		}
	}

	// exp
	public static String exp(double num) {
		double resDou = Math.exp(num);
		if( resDou > maxNum){
			return infinityInfo;
		}
		return String.valueOf(resDou);
	}

	public static boolean isOperator(char _operator) {
		return ('+' == _operator || '-' == _operator || '*' == _operator
				|| '/' == _operator || '^' == _operator || '(' == _operator || ')' == _operator);
	}

	// 判断一个字符串是否为数字
	public static boolean isNumStr(String _numberStr) {
		return !("+".equals(_numberStr) || "^".equals(_numberStr)
				|| "-".equals(_numberStr) || "*".equals(_numberStr)
				|| "/".equals(_numberStr) || ")".equals(_numberStr)
				|| "(".equals(_numberStr) || "=".equals(_numberStr));
	}

	// 判断一个字符串是否为运算符
	public static boolean isOperatorStr(String _operatorStr) {
		return ("+".equals(_operatorStr) || "^".equals(_operatorStr)
				|| "-".equals(_operatorStr) || "*".equals(_operatorStr) || "/"
					.equals(_operatorStr));
	}

	public boolean isnumber(char ch) {
		return (ch >= '0' && ch <= '9');
	}

	// 比较运算符的优先级，返回值含义：
	// 1: 优先级高于 0:优先级低于 -1:错误的运算符 2:优先级相同
	public static int compareCaculatorOperator(String _caculatorOperator1,
			String _caculatorOperator2) {
		if ("^".equals(_caculatorOperator1)) {
			if ("^".equals(_caculatorOperator2)) {
				return 2;
			} else if ("*".equals(_caculatorOperator2)
					|| "/".equals(_caculatorOperator2)
					|| "+".equals(_caculatorOperator2)
					|| "-".equals(_caculatorOperator2)) {
				return 1;
			} else {
				return -1;
			}
		} else if ("*".equals(_caculatorOperator1)
				|| "/".equals(_caculatorOperator1)) {
			if ("^".equals(_caculatorOperator2)) {
				return 0;
			} else if ("*".equals(_caculatorOperator2)
					|| "/".equals(_caculatorOperator2)) {
				return 2;
			} else if ("+".equals(_caculatorOperator2)
					|| "-".equals(_caculatorOperator2)) {
				return 1;
			} else {
				return -1;
			}
		} else if ("+".equals(_caculatorOperator1)
				|| "-".equals(_caculatorOperator1)) {
			if ("^".equals(_caculatorOperator2)
					|| "*".equals(_caculatorOperator2)
					|| "/".equals(_caculatorOperator2)) {
				return 0;
			} else if ("+".equals(_caculatorOperator2)
					|| "-".equals(_caculatorOperator2)) {
				return 2;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

	// 将一个表达式转化为栈结构
	public static ExStack getInfxStackfromStr(String infx) {
		// 新建一个栈存储表达式
		ExStack myStack = new ExStack();
		// 做一个标记(是否获得了一个数字)
		// 如果为假，将‘-’看做数字的一部分，为真，将‘-’看做运算符
		boolean getANum = false;
		// 临时字符串
		StringBuffer temStr = new StringBuffer();
		char temChar = ' ';
		// 如果表达式以'-('开头，自动插入0
		if (infx.length() > 2 && '-' == infx.charAt(0) && '(' == infx.charAt(1)) {
			myStack.push("0");
		}
		for (int i = 0; i < infx.length(); i++) {
			temChar = infx.charAt(i);
			if ((!getANum && '-' == temChar) || !isOperator(temChar)) // 是数字
			{
				temStr.append(temChar);
				getANum = true;
			} else { // 是一个操作符
				if (!"".equals(temStr.toString())) {
					myStack.push(temStr.toString());// 压栈
					temStr.delete(0, temStr.length()); // 清空temStr
				}
				myStack.push("" + temChar); // 压入运算符
				if (')' != temChar) {
					getANum = false;
				} else { // ‘)’好后面的‘-’好应被看做运算符
					getANum = true;
				}
			}
		}
		if (0 != temStr.length()) {
			myStack.push(temStr.toString());
		}
		return myStack;
	}

	public static ExStack infixToPosfix(ExStack infixStack) {
		String[] exps = infixStack.getDataArray();
		ExStack myPosfixStack = new ExStack();
		ExStack temStack = new ExStack();
		boolean getNumber = false;
		boolean getOperator = false;
		for (int i = 0; i < infixStack.size(); i++) // 注意遍历区间控制!warrning!
		{
			if (isNumStr(exps[i])) // 是数字，直接放入posfix序列中
			{
				getOperator = false;
				if (getNumber) // 如果之前处理的也是数字，在它后面插入一个*号
				{
					myPosfixStack.push(exps[i]);
					myPosfixStack.push("*");
				} else {
					myPosfixStack.push(exps[i]);
					getNumber = true;
				}
			} else if ("(".equals(exps[i])) // 遇到开括号，压栈
			{
				temStack.push(exps[i]);
			} else if (")".equals(exps[i])) // 遇到闭括号时
			{
				if (temStack.isEmpty()) {
					LogInfo.printError("brackets not match！");
					return null;
				} else {
					while (!"(".equals(temStack.top())) {
						myPosfixStack.push(temStack.top());
						temStack.pop();
						if (temStack.isEmpty()) {
							LogInfo.printError("brackets not match！");
							return null;
						}
					}
					if ("(".equals(temStack.top())) {
						temStack.pop(); // 弹出开括号
					}
				}
			} else if (isOperatorStr(exps[i])) // 为运算符
			{
				getNumber = false;
				if (getOperator) // 连续两个运算符
				{
					LogInfo.printError("Wrong Exps.");
					return null;
				} else {
					getOperator = true;
				}
				while (!temStack.isEmpty()
						&& !"(".equals(temStack.top())
						&& compareCaculatorOperator(temStack.top(), exps[i]) > 0) {
					myPosfixStack.push(temStack.top());
					temStack.pop();
				}
				temStack.push(exps[i]); // 将输入运算符放入栈内
			}
		}
		while (!temStack.isEmpty()) {
			if ("(".equals(temStack.top())) {
				LogInfo.printError("brackets not match！");
				return null;
			}
			myPosfixStack.push(temStack.top());
			temStack.pop();
		}
		return myPosfixStack;
	}

	public static String caculate(ExStack posfix) {
		if (null == posfix) {
			LogInfo.printError("posfix stack is Empty!");
			return errorInfo;
		}
		String[] posfixStr = posfix.getDataArray();
		ExStack caculateStack = new ExStack();
		double num1 = 0.;
		double num2 = 0.;
		for (int i = 0; i < posfix.size(); i++) {
			if (isNumStr(posfixStr[i])) { // 如果是数字，进行压栈
				caculateStack.push(posfixStr[i]);
			} else if (isOperatorStr(posfixStr[i])) { // 如果是运算符，取出数字，进行计算
				if (caculateStack.size() < 2) // 如果栈中数字数目小于2，报错
				{
					LogInfo.printError("Wrong Posfix Exps:");
					LogInfo.printExStack("posfix", posfix);
					return errorInfo;
				}
				try {
					if ("+".equals(posfixStr[i])) {
						num1 = Double.parseDouble(caculateStack.top());
						caculateStack.pop();
						num2 = Double.parseDouble(caculateStack.top());
						caculateStack.pop();
						caculateStack.push(Double.toString(num2 + num1));
					} else if ("-".equals(posfixStr[i])) {
						num1 = Double.parseDouble(caculateStack.top());
						caculateStack.pop();
						num2 = Double.parseDouble(caculateStack.top());
						caculateStack.pop();
						caculateStack.push(Double.toString(num2 - num1));
					} else if ("*".equals(posfixStr[i])) {
						num1 = Double.parseDouble(caculateStack.top());
						caculateStack.pop();
						num2 = Double.parseDouble(caculateStack.top());
						caculateStack.pop();
						caculateStack.push(Double.toString(num2 * num1));
					} else if ("/".equals(posfixStr[i])) {
						num1 = Double.parseDouble(caculateStack.top());
						caculateStack.pop();
						num2 = Double.parseDouble(caculateStack.top());
						caculateStack.pop();
						if (Math.abs(num1) < 1e-10 && Math.abs(num2) < 1e-10) {
							LogInfo.printInfo("Both divide number and dividend number are zero.");
							return naNInf;
						} else if (Math.abs(num1) < 1e-10) {
							LogInfo.printInfo(" Dividend number is zero.");
							return infinityInfo;
						}
						caculateStack.push(Double.toString(num2 / num1));
					} else if ("^".equals(posfixStr[i])) {
						num1 = Double.parseDouble(caculateStack.top());
						caculateStack.pop();
						num2 = Double.parseDouble(caculateStack.top());
						caculateStack.pop();
						caculateStack
								.push(Double.toString(Math.pow(num2, num1)));
					} else {
						LogInfo.printError("Unknow caculator operator:"
								+ posfixStr[i]);
						return errorInfo;
					}

				} catch (NumberFormatException e) {
					LogInfo.printError("NumberFormatException:" + e.toString());
					return errorInfo;
				}
			}
		}
		if (1 == caculateStack.size()) {
			return caculateStack.top();
		} else {
			LogInfo.printError("Unknow Error:");
			LogInfo.printExStack("caculateStack:", caculateStack);
			return errorInfo;
		}
	}

	public String strFilter(String exps) {
		char temChar = ' ';
		// cut head
		int j = 0;
		do {
			temChar = exps.charAt(j);
			j++;
		} while (j < exps.length() && !isnumber(temChar) && '.' != temChar
				&& '(' != temChar && '-' != temChar);
		exps = exps.substring(j - 1);
		// cut tail
		j = exps.length() - 1;
		do {
			temChar = exps.charAt(j);
			j--;
		} while (j > 0 && !isnumber(temChar) && ')' != temChar);
		exps = exps.substring(0, j + 2);
		StringBuffer expsBuffer = new StringBuffer("");
		// 过滤其他字符
		for (int i = 0; i < exps.length(); i++) {
			temChar = exps.charAt(i);
			if (isnumber(temChar) || isOperator(temChar) || '.' == temChar
			/* || 'E'==temChar || 'e'==temChar */// do not allow SN
			) {
				expsBuffer.append(temChar);
			}
		}
		return expsBuffer.toString();
	}

	//四舍五入到第12位
	public static String formatNumber( double dou, int scale){
		BigDecimal bd = new BigDecimal(dou);
    	bd = bd.setScale(scale,BigDecimal.ROUND_HALF_EVEN);
    	double d = bd.doubleValue(); 
    	return String.valueOf(d);
	}
	
	public static String caculateFromString(String expStr) {
		if (null == expStr || expStr.length() == 0) {
			LogInfo.printInfo("exps string is empty.");
			return errorInfo;
		}
		ExStack infixStack = getInfxStackfromStr(expStr);
		LogInfo.printInfo("infx :" + expStr);
		LogInfo.printExStack("infix Stack", infixStack);
		ExStack posfixStack = infixToPosfix(infixStack);
		if (null == posfixStack) {
			return "Error";
		}
		LogInfo.printExStack("posfix Stack", posfixStack);
		String resultString = caculate(posfixStack);
		LogInfo.printInfo("=" + resultString);
		return resultString;
	}
}
