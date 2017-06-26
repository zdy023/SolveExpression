//HyperbolicSine.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class HyperbolicSine extends Operator
{
	public HyperbolicSine()
	{
		super("sinh(",15,1,1,OperatorGroupMode.NEEDING_CLOSED);
	}
	public double solve(double[] x)
	{
		return Math.sinh(x[0]);
	}
	public Object clone()
	{
		return new HyperbolicSine();
	}
}
