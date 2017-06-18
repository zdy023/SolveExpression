//Operand.java
package xyz.davidChangx.algorithms.math;
import xyz.davidChangx.algorithms.math.ExpressionItem;
import java.util.ArrayDeque;
public Operand implements ExpressionItem
{
	private int value;
	private ArrayDeque<double> stack;
	public Operand(int value,ArrayDeque<double> stack)
	{
		this.value = value;
		this.stack = stack;
	}
	public void execute()
	{
		stack.push(value);
	}
}
