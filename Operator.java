//Operator.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.ExpressionItem;
import java.util.ArrayDeque;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public abstract class Operator implements ExpressionItem,Cloneable
{
	protected final String operator;
	protected final int inStackPriority,outStackPriority; //the priority is larger than or equals 0 and less than 16
	protected ArrayDeque<Double> stack;
	protected final int hash;
	protected final OperatorGroupMode groupMode;
	protected final int operandCount;
	protected Operator(String operator,int inStackPriority,int outStackPriority,int operandCount,OperatorGroupMode groupMode)
	{
		this.operator = operator;
		this.inStackPriority = inStackPriority;
		this.outStackPriority = outStackPriority;
		this.operandCount = operandCount%3;
		this.groupMode = groupMode;
		this.stack = null;
		
		//the lower two bytes are for the operator character,then the lower four bits of the higher two bytes are for the inStackPriority and outStackPriority,the higher four bits of the highest two bytes are for the count of the operand and the group mode
		int hash = operator.hashCode()&0x0000ffff;
		hash += inStackPriority<<16;
		hash += outStackPriority<<24;
		hash += operandCount<<20;
		hash += groupMode.ordinal()<<28;
		this.hash = hash;
	}
	public abstract Object clone();
	public final void setStack(ArrayDeque<Double> stack)
	{
		//System.out.println("node 19: " + this.operator);
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
	public abstract double solve(double[] x);
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
