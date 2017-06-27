//Module.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
/**
*执行取模运算运算.
*<p>应当留意，取模运算a mod b = a-b*floor(a/b),最终结果的符号将与b一致。</p>
*@author david Chang
*@version 1.1
*/
public class Module extends Operator
{
	/**
	*构造方法.
	*/
	public Module()
	{
		super("mod",6,6,2,OperatorGroupMode.SINGLE);
	}
	/**
	*进行取模运算运算.
	*<p>实现Operator的solve()方法，用于进行具体的运算。<\p>
	*@param x 操作数数组
	*@return 运算结果
	*/
	public double solve(double[] x)
	{
		return x[0]-x[1]*Math.floor(x[0]/x[1]);
	}
	/**
	*返回该类的克隆.
	*@return 该类的克隆
	*/
	public Object clone()
	{
		return new Module();
	}
}
