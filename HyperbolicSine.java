//HyperbolicSine.java
package xyz.davidchangx.algorithms.math.operator;
import xyz.davidchangx.algorithms.math.operator.Operator;
import xyz.davidchangx.algorithms.math.operator.OperatorGroupMode;
/**
*执行双曲正弦运算
*@author david Chang
*@version 1.1
*/
public class HyperbolicSine extends Operator
{
	/**
	*构造方法.
	*/
	public HyperbolicSine()
	{
		super("sinh(",15,1,1,OperatorGroupMode.NEEDING_CLOSED);
	}
	/**
	*进行双曲正弦运算.
	*<p>实现Operator的solve()方法，用于进行具体的运算。<\p>
	*@param x 操作数数组
	*@return 运算结果
	*/
	public double solve(double[] x)
	{
		return Math.sinh(x[0]);
	}
	/**
	*返回该类的克隆.
	*@return 该类的克隆
	*/
	public Object clone()
	{
		return new HyperbolicSine();
	}
}
