//ArcSine.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class ArcSine extends Operator
{
	public ArcSine()
	{
		super("arcsin(",15,1,1,OperatorGroupMode.NEEDING_CLOSED);
	}
	public double solve(double[] x)
	{
		return Math.asin(x[0]);
	}
}
