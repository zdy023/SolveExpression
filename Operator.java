//Operator.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.ExpressionItem;
import java.util.ArrayDeque;
public abstract class Operator implements ExpressionItem
{
	private final char operator;
	private final int inStackPriority,outStackPriority;
	private final int operandCount;
	private final boolean behindOrNot;
	private ArrayDeque<double> stack;
	public final Operator(char operator,int inStackPriority,int outStackPriority,int operandCount)
	{
		this.operator = operator;
		this.inStackPriority = inStackPriority;
		this.outStackPriority = outStackPriority;
		this.operandCount = operandCount;
		this.stack = null;
	}
	public final Operator(char operator,int inStackPriority,int outStackPriority,boolean behindOrNot)
	{
		this(operator,inStackPriority,outStackPriority,1);
		this.behindOrNot = behindOrNot;
	}
	public final Operator(char operator,int inStackPriority,int outStackPriority,int operandCount,ArrayDeque<double> stack)
	{
		this(operator,inStackPriority,outStackPriority,operandCount);
		this.stack = stack;
	}
	public final Operator(char operator,int inStackPriority,int outStackPriority,boolean behindOrNot,ArrayDeque<double> stack)
	{
		this(operator,inStackPriority,outStackPriority,behindOrNot);
		this.stack = stack;
	}
	public final void setStack(ArrayDeque<double> stack)
	{
		this.stack = stack;
	}
	public final int getStack(ArrayDeque<double> stack)
	{
		return this.stack;
	}
	public abstract double solve(double x,double y);
	public abstract double solve(double x);
	public abstract void execute
}
