//Ln.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
/**
*执行自然对数运算
*@author david Chang
*@version 1.1
*/
public class Ln extends Operator
{
	/**
	*构造方法.
	*/
	public Ln()
	{
		super("ln(",15,1,1,OperatorGroupMode.NEEDING_CLOSED);
	}
	/**
	*进行自然对数运算.
	*<p>实现Operator的solve()方法，用于进行具体的运算。<\p>
	*@param x 操作数数组
	*@return 运算结果
	*/
	public double solve(double[] x)
	{
		return Math.log(x[0]);
	}
	/**
	*返回该类的克隆.
	*@return 该类的克隆
	*/
	public Object clone()
	{
		return new Ln();
	}
}
