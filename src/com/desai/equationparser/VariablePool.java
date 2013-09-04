package com.desai.equationparser;

import java.util.HashMap;

public class VariablePool extends HashMap<String,Variable>{
	
	public void putVariable(Variable variable) {
		this.put(variable.getName(), variable);
	}
}
