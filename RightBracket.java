//RightBracket.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
/**
*标示右括号.
*<p>右括号作为关闭其他带有左括号的运算符的运算符，不会被写入后缀表达式。</p>
*@author david Chang
*@version 1.1
*/
public class RightBracket extends Operator
{
	/**
	*构造方法.
	*/
	public RightBracket()
	{
		super(")",0,0,0,OperatorGroupMode.CLOSING_ONE);
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
		return new RightBracket();
	}
}
