//Unknown.java
package xyz.davidchangx.algorithms.math;
import xyz.davidchangx.algorithms.math.ExpressionItem;
import java.util.ArrayDeque;
/**
*后缀表达式中的字母项.
*@author david Chang
*@version 1.1
*/
public class Unknown implements ExpressionItem
{
	private ArrayDeque<Double> stack;
	/**
	*由在求后缀表达式值时用到的栈构造未知数项.
	*@param stack 求后缀表达式值时用到的栈
	*/
	public Unknown(ArrayDeque<Double> stack)
	{
		this.stack = stack;
	}
	/**
	*统一接口.
	*<p>参数x会作为赋给字母的值被压入栈。</p>
	*@param x 赋给字母的值
	*/
	public void execute(double x)
	{
		stack.push(x);
	}
	/**
	*设置用于求后缀表达式值的栈.
	*@param stack 用于求后缀表达式值的栈
	*/
	public void setStack(ArrayDeque<Double> stack)
	{
		this.stack = stack;
	}
	/**
	*返回绑定的栈.
	*@return 绑定的栈
	*/
	public ArrayDeque<Double> getStack()
	{
		return this.stack;
	}
}
