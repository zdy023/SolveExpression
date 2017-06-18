//Operator.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.ExpressionItem;
import java.util.ArrayDeque;
public abstract class Operator implements ExpressionItem
{
	private final String operator;
	private final int inStackPriority,outStackPriority; //the priority is larger than or equals 0 and less than 64
	private final int operandCount; //1 - one operand,0 - two operands
	private boolean behindOrNot;
	private ArrayDeque<Double> stack;
	private int hash;
	public final Operator(String operator,int inStackPriority,int outStackPriority,int operandCount)
	{
		this.operator = operator;
		this.inStackPriority = inStackPriority;
		this.outStackPriority = outStackPriority;
		this.operandCount = operandCount&1;
		this.behindOrNot = true;
		this.stack = null;
		this.getHash();
	}
	public final Operator(String operator,int inStackPriority,int outStackPriority,boolean behindOrNot)
	{
		this(operator,inStackPriority,outStackPriority,1);
		this.behindOrNot = behindOrNot;
		this.getHash();
	}
	public final Operator(String operator,int inStackPriority,int outStackPriority,int operandCount,ArrayDeque<Double> stack)
	{
		this(operator,inStackPriority,outStackPriority,operandCount);
		this.stack = stack;
	}
	public final Operator(String operator,int inStackPriority,int outStackPriority,boolean behindOrNot,ArrayDeque<Double> stack)
	{
		this(operator,inStackPriority,outStackPriority,behindOrNot);
		this.stack = stack;
	}
	//the lower two bytes are fot the operator character,then the lower six bits of the higher two bytes are for the inStackPriority and outStackPriority,the seventh bit of the higher two bytes are for the operandCount and the flag of behindOrNot
	private final void getHash()
	{
		int hash = operator.hashCode()&0x0000ffff;
		hash += inStackPriority<<16;
		hash += outStackPriority<<24;
		hash += operandCount<<22;
		hash += (behindOrNot?1:0)<<30;
		this.hash = hash;
	}
	public final void setStack(ArrayDeque<Double> stack)
	{
		this.stack = stack;
	}
	public final ArrayDeque<Double> getStack()
	{
		return this.stack;
	}
	public final int getInStackPriority()
	{
		return this.inStackPriority;
	}
	public final int getOutStackPriority()
	{
		return this.outStackPriority;
	}
	public abstract double solve(double x,double y);
	public abstract double solve(double x);
	public abstract void execute(double x);
	public final String toString()
	{
		return this.operator;
	}
	public final int hashCode()
	{
		return this.hash;
	}
	public final boolean equals(Object x)
	{
		return this.hash==((Operator)x).hashCode();
	}
}
