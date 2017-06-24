//ArcTangent.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class ArcTangent extends Operator
{
	public ArcTangent()
	{
		super("arctan(",15,1,1,OperatorGroupMode.NEEDING_CLOSED);
	}
	public double solve(double[] x)
	{
		return Math.atan(x[0]);
	}
}
