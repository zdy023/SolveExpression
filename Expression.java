package xyz.davidChangx.algorithms.math;
import java.util.ArrayDeque;
import java.util.Scanner;
import xyz.davidChangx.algorithms.Function;
import java.util.HashMap;
import xyz.davidChangx.algorithms.math.operator.Operator;
import xyz.davidChangx.algorithms.math.Operand;
import xyz.davidChangx.algorithms.math.Unknown;
import xyz.davidChangx.algorithms.math.ExpressionItem;
import java.util.regex.Pattern;
import java.util.ArrayList;
/**
*<h1>class Expression 表达式类</h1>
*用于存储一个后缀表达式，提供了由中缀表达式构造后缀表达式的方法及求值的方法.
*@author David Chang
*@version v1.2
*/
public class Expression implements Function
{
	private String strSufix;
	private ArrayList<ExpressionItem> sufix;
	private double value;
	private char x;
	private HashMap<Character,Operator> operatorMap;
	private ArrayDeque<Double> opdStack;
	/**
	*构造方法.
	*<p>由一个中缀表达式构造后缀表达式。</p>
	*@param infix infix expression
	*/
	public Expression(String infix,HashMap<String,Operator> operatorMap,char x)
	{
		this.operatorMap = operatorMap;
		this.x = x;
		this.opdStack = new ArrayDeque<Double>();
		
		Scanner s = new Scanner(infix + " #"); //In operatorMap there must be the infomation about '$'(head mark) and '#'(ending mark)
		ArrayDeque<Operator> stack = new ArrayDeque<Operator>();
		Pattern opPat = Pattern.compile("\\W"),unknownPat = Pattern.compile(String.valueOf(x));
		Operator nextOperator,topOperator;
		stack.push(operatorMap.get("$"));
		double theNum;
		StringBuilder strSufix = new StringBuilder();
		sufix = new ArrayList<ExpressionItem>();
		for(;s.hasNext();)
		{
			if(s.hasNextDouble())
			{
				theNum = s.nextDouble();
				strSufix.append(theNum + " ");
				sufix.add(new Operand(theNum,opdStack));
			}
			else if(s.hasNext(unknownPat))
			{
			}
			else if(s.hasNext(pattern))
			{
				nextOperator = s.next(pattern).charAt(0);
				topOperator = operator.peek();
				for(int priority = this.getInStackPriority(nextOperator);this.getOutStackPriority(topOperator)>=priority;topOperator = operator.peek())
				{
					long c,a,b;
					switch(this.getOperandCount(topOperator))
					{
						case 0:
							break;
						case 1:
							a = nums.pop();
							c = solve(a,topOperator);
							nums.push(c);
							sufix.append(topOperator + " ");
							break;
						case 2:
							b = nums.pop();
							a = nums.pop();
							c = solve(a,b,topOperator);
							nums.push(c);
							sufix.append(topOperator + " ");
							break;
					}
					operator.pop();
					if(operator.isEmpty())
						break;
				}
				operator.push(nextOperator);
			}
			else ;
			//System.out.println("node 6");
		}
		//System.out.println("node 1");
		this.sufix = sufix.substring(0,sufix.length()-1);
		
		value = nums.pop();
	}
	/**
	*获得优先级.
	*@param operator 操作符
	*@param inOrNot 指明入栈优先级或出栈优先级\n\ttrue - 入栈\n\tfalse - 出栈
	*@return 优先级
	*/
	private int getPriority(char operator,boolean inOrNot)
	{
		switch(operator)
		{
			case '(':
				return inOrNot?Integer.MAX_VALUE:1;
			case '!':
				return 5;
			case '^':
				return 4;
			case '*':
			case '/':
				return 3;
			case '+':
			case '-':
				return 2;
			case '#':
			case ')':
				return 0;
			case '$':
				return 1;
		}
		return -1;
	}
	/**
	*获得入栈优先级.
	*@param operator 运算符
	*@return 入栈优先级
	*/
	private int getInStackPriority(char operator)
	{
		return this.getPriority(operator,true);
	}
	/**
	*获得出栈优先级.
	*@param operator 运算符
	*@return 出栈优先级
	*/
	private int getOutStackPriority(char operator)
	{
		return this.getPriority(operator,false);
	}
	/**
	*获得运算符的操作数个数.
	*@param operator 运算符
	*@return 操作数个数
	*/
	public static int getOperandCount(char operator)
	{
		switch(operator)
		{
			case '(':
			case ')':
			case '$':
			case '#':
				return 0;
			case '+':
			case '-':
			case '*':
			case '/':
			case '^':
				return 2;
			case '!':
				return 1;
		}
		return -1;
	}
	/**
	*获得表达式的值.
	*@return the value of the expression
	*/
	public long getValue()
	{
		return this.value;
	}
	/**
	*获得表达式的后缀表示形式.
	*@return sufix expression
	*/
	public String getSufix()
	{
		return this.sufix;
	}
	/**
	*求解某二元运算的值.
	*@param a the first operand
	*@param b the second operand
	*@param operator the operator
	*@return the value of the operation
	*/
	public static long solve(long a,long b,char operator)
	{
		switch(operator)
		{
			case '*':
				return a*b;
			case '/':
				return a/b;
			case '+':
				return a+b;
			case '-':
				return a-b;
			case '^':
				return power(a,b);
		}
		return -1;
	}
	/**
	*求解某一元运算的值.
	*@param a the operand
	*@param operator the operator
	*@return the value of the operation
	*/
	public static long solve(long a,char operator)
	{
		switch(operator)
		{
			case '!':
				return frac(a);
		}
		return -1;
	}
	/**
	*乘方.
	*<p>求解a^x。</p>
	*@param a 底数
	*@param x 指数
	*@return a^x
	*/
	private static long power(long a,long x)
	{
		if(x==0)
			return 1;
		if(x==1)
			return a;
		if(x==2)
			return a*a;
		if(x==3)
			return a*a*a;
		if(x<0)
			return 1/a;
		long pwr;
		if((x&1L)==0L)
		{
			pwr = power(a,x>>1);
			pwr *= pwr;
			return pwr;
		}
		pwr = power(a,x>>1);
		pwr *= pwr*a;
		return pwr;
	}
	/**
	*非负整数阶乘.
	*<p>负整数会返回错误结果1。</p>
	*@param n operand
	*@return n!
	*/
	private static long frac(long n)
	{
		long frc = 1L;
		if(n==0L)
			return 1;
		for(;n>0L;n--)
			frc *= n;
		return frc;
	}
	public static void main(String[] args)
	{
		Expression ex = new Expression(args[0]);
		System.out.println(ex.getSufix());
		System.out.println(ex.getValue());
	}
}
