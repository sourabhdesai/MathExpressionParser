package com.desai.equationparser;

import java.util.HashMap;

public class MathTree {
	private MathTree left;
	private MathTree right;
	private Operator operator;
	private Variable variable;
	
	public MathTree(String expression,HashMap<String,Variable> variables) throws MathParserException {
		while(expression.charAt(0)=='(') {
			int closingParenth = getIndexForClosingParenth(expression,0);
			if(closingParenth != expression.length()-1) {
				this.left = new MathTree(expression.substring(1,closingParenth),variables);//Example: (y)+x
				this.operator = new Operator(expression.charAt(closingParenth+1));
				this.right = new MathTree(expression.substring(closingParenth+2),variables);
				return;
			} else if(expression.charAt(1)=='(') {
				int innerClosingParenth = getIndexForClosingParenth(expression,1);
				if(Operator.isOperator(expression.charAt(innerClosingParenth+1))) {
					this.left = new MathTree(expression.substring(2,innerClosingParenth),variables);
					this.operator = new Operator(expression.charAt(innerClosingParenth+1));
					this.right = new MathTree(expression.substring(innerClosingParenth+2,closingParenth),variables);
					return;
				}
				else expression = expression.substring(2,innerClosingParenth);
			}
			else {
				this.evaluateExpression(expression.substring(1,closingParenth), variables); // Example: (x+y)
				return;
			}
		} 
		this.evaluateExpression(expression,variables); // Example: x+y
	}
	
	private void evaluateExpression(String expression, HashMap<String,Variable> variables) throws MathParserException {
		double constant;
		try {
			constant = Double.parseDouble(expression);
			this.variable = new Variable(expression,constant);
		} catch(NumberFormatException e) {
			this.variable = variables.get(expression);
		}
		if(this.variable!=null) return;
		for(int i=0;i<expression.length();i++) {
			if(Operator.isOperator(expression.charAt(i))) {
				this.left = new MathTree(expression.substring(0,i),variables);
				this.right = new MathTree(expression.substring(i+1),variables);
				this.operator = new Operator(expression.charAt(i));
				return;
			}
		}
		throw new MathParserException("Error Caused by \""+expression+"\"");
	}
	
	
	public double evaluate() {
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
