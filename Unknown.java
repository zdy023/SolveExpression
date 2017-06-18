//Unknown.java
package xyz.davidChangx.algorithms.math;
import xyz.davidChangx.algorithms.math.ExpressionItem;
import java.util.ArrayDeque;
public class Unknown implements ExpressionItem
{
	private ArrayDeque<Double> stack;
	public Unknown(ArrayDeque<Double> stack)
	{
		this.stack = stack;
	}
	public void execute(double x)
	{
		stack.push(x);
	}
	public void setStack(ArrayDeque<Double> stack)
	{
		this.stack = stack;
	}
	public ArrayDeque<Double> getStack()
	{
		return this.stack;
	}
}
