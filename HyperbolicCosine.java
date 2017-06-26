//HyperbolicCosine.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class HyperbolicCosine extends Operator
{
	public HyperbolicCosine()
	{
	  	super("cosh(",15,1,1,OperatorGroupMode.NEEDING_CLOSED);
	}
	public double solve(double[] x)
	{
		return Math.cosh(x[0]);
	}
	public Object clone()
	{
		return new HyperbolicCosine();
	}
}
