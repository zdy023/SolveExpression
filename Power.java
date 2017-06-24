//Power.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class Power extends Operator
{
	public Power()
	{
		super("^",9,9,2,OperatorGroupMode.SINGLE);
	}
	public double solve(double[] x)
	{
		return Math.pow(x[0],x[1]);
	}
}
