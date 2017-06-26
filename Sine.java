//Sine.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class Sine extends Operator
{
	public Sine()
	{
		super("sin(",15,1,1,OperatorGroupMode.NEEDING_CLOSED);
	}
	public double solve(double[] x)
	{
		return Math.sin(x[0]);
	}
	public Object clone()
	{
		return new Sine();
	}
}
