//Lg.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class Lg extends Operator
{
	public Lg()
	{
		super("lg(",15,1,1,OperatorGroupMode.NEEDING_CLOSED);
	}
	public double solve(double[] x)
	{
		return Math.log10(x[0]);
	}
}
