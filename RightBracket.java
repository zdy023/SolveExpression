//RightBracket.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class RightBracket extends Operator
{
	public RightBracket()
	{
		super(")",0,0,0,OperatorGroupMode.CLOSING_ONE);
	}
	public double solve(double[] x)
	{
		return 0.;
	}
}