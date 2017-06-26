//HyperbolicTangent.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class HyperbolicTangent extends Operator
{
	public HyperbolicTangent()
	{
		super("tanh(",15,1,1,OperatorGroupMode.NEEDING_CLOSED);
	}
	public double solve(double[] x)
	{
		return Math.tanh(x[0]);
	}
	public Object clone()
	{
		return new HyperbolicTangent();
	}
}
