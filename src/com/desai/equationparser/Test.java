package com.desai.equationparser;

import java.util.HashMap;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String,Variable> variables = new HashMap<String, Variable>();
		Variable x = new Variable("x",10);
		Variable y = new Variable("y",11);
		Variable z = new Variable("z",12);
		variables.put(x.getName(), x);
		variables.put(y.getName(), y);
		variables.put(z.getName(), z);
		MathTree tree;
		try {
			tree = new MathTree("4*40",variables);
			System.out.println(tree.evaluate());
		} catch (MathParserException e) {
			e.printStackTrace();
		}
	}

}
