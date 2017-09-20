//Multiply.java
package xyz.davidchangx.algorithms.math.operator;
import xyz.davidchangx.algorithms.math.operator.Operator;
import xyz.davidchangx.algorithms.math.operator.OperatorGroupMode;
/**
*执行乘法运算
*@author david Chang
*@version 1.1
*/
public class Multiply extends Operator
{
	/**
	*构造方法.
	*/
	public Multiply()
	{
		super("*",8,8,2,OperatorGroupMode.SINGLE);
	}
	/**
	*进行乘法运算.
	*<p>实现Operator的solve()方法，用于进行具体的运算。<\p>
	*@param x 操作数数组
	*@return 运算结果
	*/
	public double solve(double[] x)
	{
		return x[0]*x[1];
	}
	/**
	*返回该类的克隆.
	*@return 该类的克隆
	*/
	public Object clone()
	{
		return new Multiply();
	}
}
