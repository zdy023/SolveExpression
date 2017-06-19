//Operator.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.ExpressionItem;
import java.util.ArrayDeque;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public abstract class Operator implements ExpressionItem
{
	private final String operator;
	private final int inStackPriority,outStackPriority; //the priority is larger than or equals 0 and less than 64
	private ArrayDeque<Double> stack;
	private final int hash;
	private final OperatorGroupMode groupMode;
	private final int operandCount;
	private final Operator(String operator,int inStackPriority,int outStackPriority,int operandCount,OperatorGroupMode groupMode)
	{
		this.operator = operator;
		this.inStackPriority = inStackPriority;
		this.outStackPriority = outStackPriority;
		this.operandCount = operandCount%3;
		this.groupMode = groupMode;
		this.stack = null;
		this.getHash();
	}
	public abstract Operator();
	public String getChar()
	{
		return this.operator;
	}
	//the lower two bytes are for the operator character,then the lower six bits of the higher two bytes are for the inStackPriority and outStackPriority,the higher two bits of the highest two bytes are for the count of the operand and the group mode
	private final void getHash()
	{
		int hash = operator.hashCode()&0x0000ffff;
		hash += inStackPriority<<16;
		hash += outStackPriority<<24;
		hash += operandCount<<22;
		hash += groupMode.ordinal()<<30;
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
	public final boolean needsClosed()
	{
		return this.groupMode==OperatorGroupMode.NEEDING_CLOSED;
	}
	public final boolean isClosing()
	{
		return this.groupMode==OperatorGroupMode.CLOSING_ONE;
	}
	public abstract double solve(double x,double y);
	public abstract double solve(double x);
	public final void execute(double x)
	{
		double a,b;
		switch(operandCount)
		{
			case 1:
				a = stack.pop();
				stack.push(this.solve(a));
				break;
			case 2:
				a = stack.pop();
				b = stack.pop();
				stack.push(this.solve(a,b));
				break;
			case 0:
			default:
		}
	}
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
