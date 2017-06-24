//ArcCosine.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class ArcCosine extends Operator
{
	public ArcCosine()
	{
		super("arccos(",15,1,1,OperatorGroupMode.NEEDING_CLOSED);
	}
	public double solve(double[] x)
	{
		return Math.acos(x[0]);
	}
}
