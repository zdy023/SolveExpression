//Operator.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.ExpressionItem;
import java.util.ArrayDeque;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
/**
*运算符的抽象超类.
*<p>该类是所有运算符的超类，包括Expression。该类实现了ExpressionItem接口，故可以作为后缀表达式的表达式项。</p>
*@author david Chang
*@version 1.1
*/
public abstract class Operator implements ExpressionItem,Cloneable
{
	protected final String operator;
	/**
	*运算符的入栈、出栈优先级，决定由中缀表达式构造后缀表达式时各运算符的进出栈顺序.
	*优先级必须在区间[0,15)内。
	*/
	protected final int inStackPriority,outStackPriority;
	protected ArrayDeque<Double> stack;
	protected final int hash;
	protected final OperatorGroupMode groupMode;
	protected final int operandCount;
	/**
	*构造方法.
	*<p>设置操作符、进出栈优先级、操作数、运算符模式等参数。</p>
	*@param operator 操作符的数学形式
	*@param inStackPriority 入栈优先级
	*@param outStackPriority 出栈优先级
	*@param operandCount 操作数个数
	*@param groupMode 运算符模式
	*/
	protected Operator(String operator,int inStackPriority,int outStackPriority,int operandCount,OperatorGroupMode groupMode)
	{
		this.operator = operator;
		this.inStackPriority = inStackPriority&15;
		this.outStackPriority = outStackPriority&15;
		this.operandCount = operandCount%3;
		this.groupMode = groupMode;
		this.stack = null;
		
		int hash = operator.hashCode()&0x0000ffff;
		hash += inStackPriority<<16;
		hash += outStackPriority<<24;
		hash += operandCount<<20;
		hash += groupMode.ordinal()<<28;
		this.hash = hash;
	}
	/**
	*获得该运算符的可靠复制.
	*@return 该运算符的克隆
	*/
	public abstract Object clone();
	/**
	*设置用于后缀表达式求值的栈.
	*@param stack 用于后缀表达式求值的栈
	*/
	public final void setStack(ArrayDeque<Double> stack)
	{
		//System.out.println("node 19: " + this.operator);
		this.stack = stack;
	}
	/**
	*返回绑定的栈.
	*@return 绑定的栈
	*/
	public final ArrayDeque<Double> getStack()
	{
		return this.stack;
	}
	/**
	*返回入栈优先级.
	*@return 入栈优先级
	*/
	public final int getInStackPriority()
	{
		return this.inStackPriority;
	}
	/**
	*返回出栈优先级.
	*@return 出栈优先级
	*/
	public final int getOutStackPriority()
	{
		return this.outStackPriority;
	}
	/**
	*判断运算符模式.
	*@return true - 若该运算符需要被关闭 false - 若该运算符不需要被关闭
	*/
	public final boolean needsClosed()
	{
		return this.groupMode==OperatorGroupMode.NEEDING_CLOSED;
	}
	/**
	*判断运算符模式.
	*@return true - 若该运算符可以关闭其他运算符 false - 若该运算符不能关闭其他运算符
	*/
	public final boolean isClosing()
	{
		return this.groupMode==OperatorGroupMode.CLOSING_ONE;
	}
	/**
	*执行运算.
	*@param x 操作数数组
	*@return 运算结果
	*/
	public abstract double solve(double[] x);
	/**
	*统一接口.
	*<p>从栈中取需要的操作数执行运算，并将结果重新压入栈</p>
	*<p>参数无实际用处，可取任意值。</p>
	*@param x 无用处
	*/
	public final void execute(double x)
	{
		if(operandCount==0)
			return;
		double[] y = new double[operandCount];
		for(int i = operandCount-1;i>=0;i--)
		{
			y[i] = stack.pop();
			//System.out.println("node 16: " + this.operator + " " + y[i]);
		}
		stack.push(this.solve(y));
		//System.out.println("node 17: " + this.operator + " " + stack.peek());
	}
	/**
	*获得该类的字符串表示.
	*<p>仅返回该运算符的数学形式。</p>
	*@return 运算符的数学形式
	*/
	public final String toString()
	{
		return this.operator;
	}
	/**
	*获得该类的哈希码.
	*<p>该类的哈希码如下构造：</p>
	*<li>
	*	<ul>低两字节，取operator（运算符的数学形式）的哈希码的低两字节。由于此原因，该哈希码可能不保证每个哈希码对应唯一运算符；</ul>
	*	<ul>第三字节低4位，为入栈优先级inStackPriority；</ul>
	*	<ul>最高字节低4位，为出栈优先级outStackPriority；</ul>
	*	<ul>第三字节高4位，为操作数个数operandCount；</ul>
	*	<ul>最高字节5,6位，为运算符模式对应的枚举序数groupMode.ordinal()；</ul>
	*</li>
	*@return 哈希码
	*/
	public final int hashCode()
	{
		return this.hash;
	}
	/**
	*判断两运算符是否相等.
	*@param x 另一运算符
	*@return true - 两运算符完全一致 false - 两运算符不相同
	*/
	public final boolean equals(Object x)
	{
		if(x instanceof Operator)
		{
			Operator y = (Operator)x;
			return (this.operator.equals(y.toString()))&&(this.hash==y.hashCode());
		}
		return false;
	}
}
