//Ln.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class Ln extends Operator
{
	public Ln()
	{
		super("ln(",15,1,1,OperatorGroupMode.NEEDING_CLOSED);
	}
	public double solve(double[] x)
	{
		return Math.log(x[0]);
	}
	public Object clone()
	{
		return new Ln();
	}
}
