//Operand.java
package xyz.davidChangx.algorithms.math;
import xyz.davidChangx.algorithms.math.ExpressionItem;
import java.util.ArrayDeque;
public class Operand implements ExpressionItem,Comparable<Operand>
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
		System.out.println("node 18");
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
		double diff = value-x.getValue();
		return diff>0.?1:(diff<0.?-1:0);
	}
	public boolean equals(Object x)
	{
		return value==((Operand)x).getValue();
	}
}
