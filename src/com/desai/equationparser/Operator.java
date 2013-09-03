package com.desai.equationparser;

public class Operator {
	private char operator;
	private static  char[] operators = {'+','-','*','/','%','&','|'};
	
	public static boolean isOperator(char other) {
		for(char c : operators) {
			if(c==other) return true;
		}
		return false;
	}
	
	public Operator(char operator) throws MathParserException {
		if(isOperator(operator)) {
			this.operator=operator;
		} else throw new MathParserException("Attempted to use \""+operator+"\" as an operator, but it is not an operator");
	}
	
	public double doOperation(double left, double right) {
		if(operator=='+') return left+right;
		if(operator=='-') return left-right;
		if(operator=='*') return left*right;
		if(operator=='/') return left/right;
		if(operator=='%') return left%right;
		if(operator=='&') return (int)left&(int)right;
		return (int)left|(int)right;
	}
	
	
}
