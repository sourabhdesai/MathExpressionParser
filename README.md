Math Expression Parser
------------------------------------------------------------------------------------------------------------

After looking through all the different Mathematical expression parsing libraries available online, I decided I wanted to learn how to make my own from scratch so I could build on it for my own purposes.  
This parser uses a Parse Tree data structure to interpret the sytax in Mathematical expressions.

Features
------------------------------------------------------------------------------------------------------------

It's features include:
- Full fledged Mathematical expression parsing
- Ability to use both **variables** and **constants** which are of type `double`  
- Support for basic math operators +,-,*,/,% and bitwise operators & and |  
- Ability to add custom operators
- No need to reparse expression when the variables change value
- Fully working Parenthesis encapsulation

**CAVEAT:** In the current implementation, there is no Order of operations hierarchy interpretation.

Ive tested it out for a variety of expressions in the [Test.java](https://github.com/sourabhdesai/MathExpressionParser/blob/master/src/com/desai/equationparser/Test.java) file and each time, it has come up with the correct answer.  

How to Use it
------------------------------------------------------------------------------------------------------------
1. Create Variable objects to store each of your variables.
  - `Variable x = new Variable("x",5.5); // declared a variable object with the name "x" and value 5.5`
  - `Variable y = new Variable("y",4.5); // declared a variable object with the name "y" and value 4.5`
2. Create a `HashMap<String, Variable>` object to store the variables.
  - `VariablePool variables = new VariablePool();`
  - `variables.putVariable(x);`
  - `variables.putVariable(y);`
3. Evaluate your expression using a `MathTree` object.
  - `MathTree tree;`  
		`try {`  
			`tree = MathTree.getInstance("(x+y)*4",variables);`  
			`System.out.println(tree.evaluate());`  
		`} catch (MathParserException e) {`  
			`e.printStackTrace();`  
		`}`  
4. Done! That should print out the value **40**  
5. If you want to use the same expression but with different variables values, just do `x.setValue(double value)` for whatever `Variable` object (In this case that's `x`) you want and then run `tree.evaluate()` to get the expression's `double` value with the different variable value(s). As you can see in the source code, the `evaluate()` method should not take *too* much time.
	- For a more complicated example using custom operators, see [Test.java](https://github.com/sourabhdesai/MathExpressionParser/blob/master/src/com/desai/equationparser/Test.java)  

Features to Come
------------------------------------------------------------------------------------------------------------

There are some features that I still want to include. These features are:  
- **JSON** encoding and decoding for easy reuse of a Parsed Expression tree.
- Ability to call methods within expressions
- Ability to use custom methods  
- Have an Order of operations hierarchy.

