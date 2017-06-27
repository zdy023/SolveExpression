//OperatorGroupMode.java
package xyz.davidChangx.algorithms.math.operator;
/**
*操作符的某种模式.
*author david Chang
*version 1.1
*/
public enum OperatorGroupMode
{
	/**
	*用于关闭某需要关闭的运算符，通常为")".
	*/
	CLOSING_ONE,
	/**
	*需要关闭的运算符，如："(","sin("等.
	*/
	NEEDING_CLOSED,
	/**
	*单独出现的运算符，不需要关闭，也不能关闭其他运算符.
	*/
	SINGLE;
}
