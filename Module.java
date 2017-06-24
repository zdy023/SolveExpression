//Module.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class Module extends Operator
{
	public Module()
	{
		super("mod",6,6,2,OperatorGroupMode.SINGLE);
	}
	public double solve(double[] x)
	{
		return x[0]-x[1]*Math.floor(x[0]/x[1]);
	}
}
