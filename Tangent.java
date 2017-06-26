//Tangent.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class Tangent extends Operator
{
	public Tangent()
	{
	  	super("tan(",15,1,1,OperatorGroupMode.NEEDING_CLOSED);
	}
	public double solve(double[] x)
	{
		return Math.tan(x[0]);
	}
	public Object clone()
	{
		return new Tangent();
	}
}
