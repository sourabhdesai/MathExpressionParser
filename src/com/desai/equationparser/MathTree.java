package com.desai.equationparser;

import java.util.HashMap;

public class MathTree {
	private MathTree left;
	private MathTree right;
	private Operator operator;
	private Variable variable;
	
	public static MathTree getInstance(String expression) throws MathParserException {
		return new MathTree(expression,new VariablePool(), new OperationPool());
	}
	
	public static MathTree getInstance(String expression, VariablePool variables) throws MathParserException {
		return new MathTree(expression, variables, new OperationPool());
	}
	
	public static MathTree getInstance(String expression, OperationPool operations) throws MathParserException {
		return new MathTree(expression, new VariablePool(), operations);
	}
	
	public static MathTree getInstance(String expression, VariablePool variables, OperationPool operations) throws MathParserException {
		return new MathTree(expression, variables, operations);
	}
	
	private MathTree(String expression,VariablePool variables, OperationPool operations) throws MathParserException {
		while(expression.charAt(0)=='(') {
			int closingParenth = getIndexForClosingParenth(expression,0);
			if(closingParenth != expression.length()-1) {
				this.left = new MathTree(expression.substring(1,closingParenth),variables, operations);//Example: (y)+x
				this.operator = operations.get(expression.charAt(closingParenth+1));
				this.right = new MathTree(expression.substring(closingParenth+2),variables, operations);
				return;
			} else if(expression.charAt(1)=='(') {
				int innerClosingParenth = getIndexForClosingParenth(expression,1);
				if(Operator.isOperator(expression.charAt(innerClosingParenth+1))) {
					this.left = new MathTree(expression.substring(2,innerClosingParenth),variables, operations);
					this.operator = operations.get(expression.charAt(innerClosingParenth+1));
					this.right = new MathTree(expression.substring(innerClosingParenth+2,closingParenth),variables, operations);
					return;
				}
				else expression = expression.substring(2,innerClosingParenth);
			}
			else {
				this.evaluateExpression(expression.substring(1,closingParenth), variables,operations); // Example: (x+y)
				return;
			}
		} 
		this.evaluateExpression(expression,variables,operations); // Example: x+y
	}
	
	private void evaluateExpression(String expression, VariablePool variables, OperationPool operations) throws MathParserException {
		double constant;
		try {
			constant = Double.parseDouble(expression);
			this.variable = new Variable(expression,constant);
		} catch(NumberFormatException e) {
			this.variable = variables.get(expression);
		}
		if(this.variable!=null) return;
		for(int i=0;i<expression.length();i++) {
			Operator operator = operations.get(expression.charAt(i));
			if(operator!=null) {
				this.left = new MathTree(expression.substring(0,i),variables, operations);
				this.right = new MathTree(expression.substring(i+1),variables, operations);
				this.operator = operator;
				return;
			}
		}
		throw new MathParserException("Error Caused by \""+expression+"\"");
	}
	
	
	public double evaluate() throws MathParserException {
		if(this.variable != null) return this.variable.getValue();
		else return this.operator.doOperation(left.evaluate(), right.evaluate());
	}
	
	private static int getIndexForClosingParenth(String expression,int openingParenth) throws MathParserException {
		int counter=0;
		for(int i=openingParenth;i<expression.length();i++) {
			if(expression.charAt(i)==')') counter--;
			else if(expression.charAt(i)=='(') counter++;
			if(counter==0) return i;
		}
		throw new MathParserException("Missing Closing \")\", need "+counter+" more \")\"s in expression "+expression);
	}
}
