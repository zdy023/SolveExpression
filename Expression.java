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
	private boolean newestOrNot,setOrNot;
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
		operatorMap.forEach((String oprName,Operator oprtr)->oprtr.setStack(opdStack));
		
		Scanner s = new Scanner(infix + " #"); //In operatorMap there must be the infomation about '$'(head mark) and '#'(ending mark)
		ArrayDeque<Operator> stack = new ArrayDeque<Operator>();
		Pattern opPat = Pattern.compile("\\W"),unknownPat = Pattern.compile(String.valueOf(x));
		Operator nextOperator,topOperator;
		String nxtOpt;
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
				s.next(unknownPat);
				strSufix.append(x + " ");
				sufix.add(new Unknown(opdStack));
			}
			else if(s.hasNext(opPat))
			{
				nxtOpt = s.next(opPat);
				nextOperator = operatorMap.get(nxtOpt);
				topOperator = stack.peek();
				for(int priority = nextOperator.getInStackPriority();topOperator.getOutStackPriority()>=priority;topOperator = stack.peek())
				{
					if((nxtOpt.equals("#")&&(stack.size()==1))
						break;
					strSufix.append(topOperator.getChar() + " ");
					sufix.add(topOperator);
					stack.pop();
					if(topOperator.needsClosed())
						break;
				}
				if(!nextOperator.isClosing())
					stack.push(nextOperator);
			}
			else ;
		}
		this.sufix = sufix.substring(0,sufix.length()-1);
		
		newestOrNot = false;
		setOrNot = false;
	}
	public Expression(String infix,HashMap<String,Operator> operatorMap)
	{
		this.operatorMap = operatorMap;
		this.opdStack = new ArrayDeque<Double>();
		
		Scanner s = new Scanner(infix + " #"); //In operatorMap there must be the infomation about '$'(head mark) and '#'(ending mark)
		ArrayDeque<Operator> stack = new ArrayDeque<Operator>();
		Pattern opPat = Pattern.compile("\\W");
		Operator nextOperator,topOperator;
		String nxtOpt;
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
			else if(s.hasNext(opPat))
			{
				nxtOpt = s.next(opPat);
				nextOperator = operatorMap.get(nxtOpt);
				topOperator = stack.peek();
				for(int priority = nextOperator.getInStackPriority();topOperator.getOutStackPriority()>=priority;topOperator = stack.peek())
				{
					if((nxtOpt.equals("#")&&(stack.size()==1))
						break;
					strSufix.append(topOperator.getChar() + " ");
					sufix.add(topOperator);
					stack.pop();
				}
				stack.push(nextOperator);
			}
			else ;
		}
		this.sufix = sufix.substring(0,sufix.length()-1);
		
		newestOrNot = false;
		setOrNot = false;
	}
	public void solve(double k)
	{
		opdStack.clear();
		Iterator it = sufix.iterator();
		for(;it.hasNext();)
			it.next().execute(k);
		value = opdStack.pop();
		setOrNot = true;
		newestOrNot = true;
	}
	public void solve()
	{
		this.solve(0)
	}
	public double f(double x)
	{
		this.solve(x);
		return this.value;
	}
	public boolean isNewest()
	{
		return newestOrNot;
	}
	/**
	*获得表达式的值.
	*@return the value of the expression
	*/
	public double getValue()
	{
		if(!setOrNot)
			this.solve();
		newestOrNot = false;
		return this.value;
	}
	/**
	*获得表达式的后缀表示形式.
	*@return sufix expression
	*/
	public String getSufix()
	{
		return this.strSufix;
	}
	public String toString()
	{
		return this.strSufix;
	}
	public static void main(String[] args)
	{
		Expression ex = new Expression(args[0]);
		System.out.println(ex.getSufix());
		System.out.println(ex.getValue());
	}
}
