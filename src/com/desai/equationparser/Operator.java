package com.desai.equationparser;

public class Operator {
	private char operator;
	private Operation operation;
	public static final char[] operators = {'+','-','*','/','%','&','|'};
	
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
	
	public Operator(char operator, Operation operation) {
		this.operator = operator;
		this.operation = operation;
	}
	
	public double doOperation(double left, double right) throws MathParserException {
		if(this.operation != null) return this.operation.doOperation(left, right);
		if(operator=='+') return left+right;
		if(operator=='-') return left-right;
		if(operator=='*') return left*right;
		if(operator=='/') return left/right;
		if(operator=='%') return left%right;
		if(operator=='&') return (int)left&(int)right;
		if(operator=='|') return (int)left|(int)right;
		throw new MathParserException("Attempted to use \""+operator+"\" as an operator, but it is not an operator");
	}
	
	public char getOperator() {
		return this.operator;
	}
	
}
