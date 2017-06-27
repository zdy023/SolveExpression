//Head.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
/**
*表达式开始的标识.
*<p>任何运算符库应当包含该运算符</p>
*@author david Chang
*@version 1.1
*/
public class Head extends Operator
{
	/**
	*构造方法.
	*/
	public Head()
	{
		super("$",1,1,0,OperatorGroupMode.SINGLE);
	}
	/**
	*返回0.
	*<p>实现Operator的solve()方法，用于进行具体的运算。<\p>
	*@param x 操作数数组
	*@return 0
	*/
	public double solve(double[] x)
	{
		return 0;
	}
	/**
	*返回该类的克隆.
	*@return 该类的克隆
	*/
	public Object clone()
	{
		return new Head();
	}
}
