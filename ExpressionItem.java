//ExpressionItem.java
package xyz.davidChangx.algorithms.math;
/**
*表达式项.
*<p>作为后缀表达式中的项的抽象接口。它可能是常数、未知数或运算符</p>
*@author david Chang
*@version 1.1
*/
public interface ExpressionItem
{
	/**
	*抽象方法，在对后缀表达式求值时调用.
	*param x 可能需要的参数
	*/
	public void execute(double x);
}
