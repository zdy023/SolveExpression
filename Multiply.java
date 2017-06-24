//Multiply.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class Multiply extends Operator
{
	public Multiply()
	{
		super("*",8,8,2,OperatorGroupMode.SINGLE);
	}
	public double solve(double[] x)
	{
		return x[0]*x[1];
	}
}
