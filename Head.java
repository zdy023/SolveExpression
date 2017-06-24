//Head.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class Head extends Operator
{
	public Head()
	{
		super("$",1,1,0,OperatorGroupMode.SINGLE);
	}
	public double solve(double[] x)
	{
		return 0;
	}
}
