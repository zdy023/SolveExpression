//Minus.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class Minus extends Operator
{
	public Minus()
	{
		super("-",5,5,2,OperatorGroupMode.SINGLE);
	}
	public double solve(double[] x)
	{
		return x[0]-x[1];
	}
	public Object clone()
	{
		return new Minus();
	}
}
