//Plus.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class Plus extends Operator
{
	public Plus()
	{
		super("+",5,5,2,OperatorGroupMode.SINGLE);
	}
	public double solve(double x,double y)
	{
		return x+y;
	}
}