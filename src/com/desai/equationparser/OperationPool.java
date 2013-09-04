package com.desai.equationparser;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class OperationPool extends HashMap<Character,Operator> {
	
	public OperationPool() {
		
		for(char op : Operator.operators) {
			try {
				this.put(op,new Operator(op));
			} catch (MathParserException e) {
				//do nothing..this exception will never be thrown here.
			}
		}
	}
	
	public void putOperation(Operator operator) {
		this.put(operator.getOperator(), operator);
	}
	
}
