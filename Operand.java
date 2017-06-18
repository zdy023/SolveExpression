//Operand.java
package xyz.davidChangx.algorithms.math;
import xyz.davidChangx.algorithms.math.ExpressionItem;
import java.util.ArrayDeque;
public Operand implements ExpressionItem,Comparable<Operand>
{
	private double value;
	private ArrayDeque<Double> stack;
	public Operand(double value,ArrayDeque<Double> stack)
	{
		this.value = value;
		this.stack = stack;
	}
	public void execute(double x)
	{
		stack.push(value);
	}
	public void setStack(ArrayDeque<Double> stack)
	{
		this.stack = stack;
	}
	public ArrayDeque<Double> getStack()
	{
		return this.stack;
	}
	public void setValue(double value)
	{
		this.value = value;
	}
	public double getValue()
	{
		return this.value;
	}
	public String toString()
	{
		return String.valueOf(value);
	}
	public int compareTo(Operand x)
	{
		return value-x;
	}
	public boolean equals(Object x)
	{
		return value==((Operand)x).getValue();
	}
}
