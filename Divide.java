//Divide.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class Divide extends Operator
{
	public Divide()
	{
		super("/",7,7,2,OperatorGroupMode.SINGLE);
	}
	public double solve(double x,double y)
	{
		return x/y;
	}
}
