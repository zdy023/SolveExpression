//Operand.java
package xyz.davidchangx.algorithms.math;
import xyz.davidchangx.algorithms.math.ExpressionItem;
import java.util.ArrayDeque;
/**
*操作数类，可以作为后缀表达式的常数项.
*@author david Chang
*@version 1.1
*/
public class Operand implements ExpressionItem,Comparable<Operand>
{
	private double value;
	private ArrayDeque<Double> stack;
	/**
	*由常数以及表达式求值中会用到的栈构造表达式项.
	*@param value 该常数项的常数值
	*@param stack 求后缀表达式时用到的栈
	*/
	public Operand(double value,ArrayDeque<Double> stack)
	{
		this.value = value;
		this.stack = stack;
	}
	/**
	*统一接口.
	*<p>将常数压入栈。</p>
	*<p>参数对于该类没有实际用处可取任意值。
	*@param x 无用处
	*/
	public void execute(double x)
	{
		stack.push(value);
		//System.out.println("node 18");
	}
	/**
	*设置表达式求值时会用到的栈.
	*@param stack 后缀表达式求值时用到的数栈
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
	/**
	*重新设置常数值.
	*@param value 新常数值
	*/
	public void setValue(double value)
	{
		this.value = value;
	}
	/**
	*返回常数值.
	*@return 常数值
	*/
	public double getValue()
	{
		return this.value;
	}
	/**
	*返回该类的字符串表示.
	*<p>字符串的字面量为常数值。</p>
	*/
	public String toString()
	{
		return String.valueOf(value);
	}
	/**
	*与另一Operand比较大小.
	*@param x 待比较的Operand
	*@return 1 - 该数大于x 0 - 两数相等 -1 - 该数小于x
	*/
	public int compareTo(Operand x)
	{
		double diff = value-x.getValue();
		return diff>0.?1:(diff<0.?-1:0);
	}
	/**
	*判断与另一Operand是否相等.
	*@param x 另一Operand
	*@return true - 两数相等 false - 两数不等
	*/
	public boolean equals(Object x)
	{
		if(x instanceof Operand)
			return value==((Operand)x).getValue();
		return false;
	}
}
