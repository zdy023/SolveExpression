//LeftBracket.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public class LeftBracket extends Operator
{
	public LeftBracket()
	{
		super("(",15,1,0,OperatorGroupMode.NEEDING_CLOSED);
	}
	public double solve(double[] x)
	{
		return 0.;
	}
}
