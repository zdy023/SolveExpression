//Operator.java
package xyz.davidChangx.algorithms.math.operator;
import xyz.davidChangx.algorithms.math.ExpressionItem;
import java.util.ArrayDeque;
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
public abstract class Operator implements ExpressionItem
{
	private final String operator;
	private final int inStackPriority,outStackPriority; //the priority is larger than or equals 0 and less than 64
	private boolean behindOrNot;
	private ArrayDeque<Double> stack;
	private int hash;
	private OperatorGroupMode groupMode;
	public final Operator(String operator,int inStackPriority,int outStackPriority,OperatorGroupMode groupMode)
	{
		this.operator = operator;
		this.inStackPriority = inStackPriority;
		this.outStackPriority = outStackPriority;
		this.behindOrNot = true;
		this.groupMode = groupMode;
		this.stack = null;
		this.getHash();
	}
	public final Operator(String operator,int inStackPriority,int outStackPriority,boolean behindOrNot)
	{
		this(operator,inStackPriority,outStackPriority);
		this.behindOrNot = behindOrNot;
		this.getHash();
	}
	public final Operator(String operator,int inStackPriority,int outStackPriority,boolean behindOrNot,ArrayDeque<Double> stack)
	{
		this(operator,inStackPriority,outStackPriority,behindOrNot);
		this.stack = stack;
	}
	public final Operator(String operator,int inStackPriority,int outStackPriority,OperatorGroupMode groupMode,ArrayDeque<Double> stack)
	{
		this(operator,inStackPriority,outStackPriority,groupMode);
		this.stack = stack;
	}
	public String getChar()
	{
		return this.operator;
	}
	//the lower two bytes are fot the operator character,then the lower six bits of the higher two bytes are for the inStackPriority and outStackPriority,the higher two bits of the second highest byte are for the operandCount and the seventh bit of the highest byte is for the flag of behindOrNot
	private final void getHash()
	{
		int hash = operator.hashCode()&0x0000ffff;
		hash += inStackPriority<<16;
		hash += outStackPriority<<24;
		hash += groupMode.ordinal()<<22;
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
