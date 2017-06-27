//LeftBracket.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
/**
*标识左括号的运算符.
*@author david Chang
*@version 1.1
*/
public class LeftBracket extends Operator
{
	/**
	*构造方法.
	*/
	public LeftBracket()
	{
		super("(",15,1,0,OperatorGroupMode.NEEDING_CLOSED);
	}
	/**
	*返回0.
	*<p>实现Operator的solve()方法，用于进行具体的运算。<\p>
	*@param x 操作数数组
	*@return 0
	*/
	public double solve(double[] x)
	{
		return 0.;
	}
	/**
	*返回该类的克隆.
	*@return 该类的克隆
	*/
	public Object clone()
	{
		return new LeftBracket();
	}
}
