package com.desai.equationparser;

import java.util.HashMap;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VariablePool variables = new VariablePool();
		Variable x = new Variable("x",10);
		Variable y = new Variable("y",11);
		Variable z = new Variable("z",12);
		Operator xor = new Operator('!',new Operation() {
			@Override
			public double doOperation(double left, double right) {
				return (int)left^(int)right;
			}			
		});
		Operator pow = new Operator('^',new Operation() {
			@Override
			public double doOperation(double left, double right) {
				return Math.pow(left, right);
			}
		});
		OperationPool opPool = new OperationPool();
		opPool.putOperation(xor);
		opPool.putOperation(pow);
		variables.putVariable(x);
		variables.putVariable(y);
		variables.putVariable(z);
		MathTree tree;
		try {
			tree = MathTree.getInstance("(x^y)",variables,opPool);
			System.out.println(tree.evaluate());
		} catch (MathParserException e) {
			e.printStackTrace();
		}
	}

}
