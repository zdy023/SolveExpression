//Power.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
/**
*执行乘方运算.
*<p>乘方运算为右结合运算。</p>
*@author david Chang
*@version 1.1
*/
public class Power extends Operator
{
	/**
	*构造方法.
	*/
	public Power()
	{
		super("^",10,9,2,OperatorGroupMode.SINGLE);
	}
	/**
	*进行乘方运算.
	*<p>实现Operator的solve()方法，用于进行具体的运算。<\p>
	*@param x 操作数数组
	*@return 运算结果
	*/
	public double solve(double[] x)
	{
		return Math.pow(x[0],x[1]);
	}
	/**
	*返回该类的克隆.
	*@return 该类的克隆
	*/
	public Object clone()
	{
		return new Power();
	}
}
