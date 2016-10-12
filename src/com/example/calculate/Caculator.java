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

	// ��10Ϊ��
	public static String log(double num) {
		double resDou = Math.log(num) / Math.log(10);
		if( resDou > maxNum){
			return infinityInfo;
		}
		return String.valueOf(resDou);
	}

	// lnָ������
	public static String ln(double num) {
		double resDou = Math.log(num);
		if( resDou > maxNum){
			return infinityInfo;
		}
		return String.valueOf(resDou);
	}

	// sin: 1�����ջ��ȼ��㣬2�����ն�������
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
			double radian = num * Math.PI / 180;// �Ƕ�ת��Ϊ����
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
			double radian = num * Math.PI / 180;// �Ƕ�ת��Ϊ����
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

	// �ж�һ���ַ����Ƿ�Ϊ����
	public static boolean isNumStr(String _numberStr) {
		return !("+".equals(_numberStr) || "^".equals(_numberStr)
				|| "-".equals(_numberStr) || "*".equals(_numberStr)
				|| "/".equals(_numberStr) || ")".equals(_numberStr)
				|| "(".equals(_numberStr) || "=".equals(_numberStr));
	}

	// �ж�һ���ַ����Ƿ�Ϊ�����
	public static boolean isOperatorStr(String _operatorStr) {
		return ("+".equals(_operatorStr) || "^".equals(_operatorStr)
				|| "-".equals(_operatorStr) || "*".equals(_operatorStr) || "/"
					.equals(_operatorStr));
	}

	public boolean isnumber(char ch) {
		return (ch >= '0' && ch <= '9');
	}

	// �Ƚ�����������ȼ�������ֵ���壺
	// 1: ���ȼ����� 0:���ȼ����� -1:���������� 2:���ȼ���ͬ
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

	// ��һ�����ʽת��Ϊջ�ṹ
	public static ExStack getInfxStackfromStr(String infx) {
		// �½�һ��ջ�洢���ʽ
		ExStack myStack = new ExStack();
		// ��һ�����(�Ƿ�����һ������)
		// ���Ϊ�٣�����-���������ֵ�һ���֣�Ϊ�棬����-�����������
		boolean getANum = false;
		// ��ʱ�ַ���
		StringBuffer temStr = new StringBuffer();
		char temChar = ' ';
		// ������ʽ��'-('��ͷ���Զ�����0
		if (infx.length() > 2 && '-' == infx.charAt(0) && '(' == infx.charAt(1)) {
			myStack.push("0");
		}
		for (int i = 0; i < infx.length(); i++) {
			temChar = infx.charAt(i);
			if ((!getANum && '-' == temChar) || !isOperator(temChar)) // ������
			{
				temStr.append(temChar);
				getANum = true;
			} else { // ��һ��������
				if (!"".equals(temStr.toString())) {
					myStack.push(temStr.toString());// ѹջ
					temStr.delete(0, temStr.length()); // ���temStr
				}
				myStack.push("" + temChar); // ѹ�������
				if (')' != temChar) {
					getANum = false;
				} else { // ��)���ú���ġ�-����Ӧ�����������
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
		for (int i = 0; i < infixStack.size(); i++) // ע������������!warrning!
		{
			if (isNumStr(exps[i])) // �����֣�ֱ�ӷ���posfix������
			{
				getOperator = false;
				if (getNumber) // ���֮ǰ�����Ҳ�����֣������������һ��*��
				{
					myPosfixStack.push(exps[i]);
					myPosfixStack.push("*");
				} else {
					myPosfixStack.push(exps[i]);
					getNumber = true;
				}
			} else if ("(".equals(exps[i])) // ���������ţ�ѹջ
			{
				temStack.push(exps[i]);
			} else if (")".equals(exps[i])) // ����������ʱ
			{
				if (temStack.isEmpty()) {
					LogInfo.printError("brackets not match��");
					return null;
				} else {
					while (!"(".equals(temStack.top())) {
						myPosfixStack.push(temStack.top());
						temStack.pop();
						if (temStack.isEmpty()) {
							LogInfo.printError("brackets not match��");
							return null;
						}
					}
					if ("(".equals(temStack.top())) {
						temStack.pop(); // ����������
					}
				}
			} else if (isOperatorStr(exps[i])) // Ϊ�����
			{
				getNumber = false;
				if (getOperator) // �������������
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
				temStack.push(exps[i]); // ���������������ջ��
			}
		}
		while (!temStack.isEmpty()) {
			if ("(".equals(temStack.top())) {
				LogInfo.printError("brackets not match��");
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
			if (isNumStr(posfixStr[i])) { // ��������֣�����ѹջ
				caculateStack.push(posfixStr[i]);
			} else if (isOperatorStr(posfixStr[i])) { // ������������ȡ�����֣����м���
				if (caculateStack.size() < 2) // ���ջ��������ĿС��2������
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
		// ���������ַ�
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

	//�������뵽��12λ
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
