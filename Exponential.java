//Exponential.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class Exponential extends Operator
{
	public Exponential()
	{
		super("e^(",15,1,1,OperatorGroupMode.NEEDING_CLOSED);
	}
	public double solve(double[] x)
	{
		return Math.exp(x[0]);
	}
	public Object clone()
	{
		return new Exponential();
	}
}
