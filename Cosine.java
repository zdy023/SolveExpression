//Cosine.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class Cosine extends Operator
{
	public Cosine()
	{
		super("cos(",15,1,1,OperatorGroupMode.NEEDING_CLOSED);
	}
	public double solve(double[] x)
	{
		return Math.cos(x[0]);
	}
}
