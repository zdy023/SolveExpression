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
import xyz.davidChangx.algorithms.math.operator.OperatorGroupMode;
import java.util.ListIterator;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
/**
*<h1>class Expression 表达式类</h1>
*用于存储一个后缀表达式，提供了由中缀表达式构造后缀表达式的方法及求值的方法.
*@author David Chang
*@version v1.2
*/
public class Expression extends Operator implements Function
{
	private String strSufix,infix;
	private ArrayList<ExpressionItem> sufix;
	private double value;
	private char x;
	private HashMap<String,Operator> operatorMap;
	private ArrayDeque<Double> opdStack;
	private boolean newestOrNot,setOrNot;
	/**
	*构造方法.
	*<p>由一个中缀表达式构造后缀表达式。</p>
	*@param infix infix expression
	*/
	public Expression(String infix,HashMap<String,Operator> operatorMap,char x)
	{
		this("f",15,1,infix,operatorMap,x);
	}
	public Expression(String infix,HashMap<String,Operator> operatorMap)
	{
		this(infix,operatorMap,'\0');
	}
	public Expression(String functionName,int inStackPriority,int outStackPriority,String infix,HashMap<String,Operator> operatorMap,char x)
	{
		super(functionName + "(",inStackPriority,outStackPriority,1,OperatorGroupMode.NEEDING_CLOSED);
		
		this.infix = infix;
		this.x = x;
		this.operatorMap = this.cloneMap(operatorMap);
		this.opdStack = new ArrayDeque<Double>();
		this.operatorMap.forEach((String oprName,Operator oprtr)->oprtr.setStack(opdStack));
		
		Scanner s = new Scanner(infix + " #"); //In operatorMap there must be the infomation about '$'(head mark) and '#'(ending mark)
		ArrayDeque<Operator> stack = new ArrayDeque<Operator>();
		Pattern opPat = Pattern.compile("(?:" + x + "?[a-z]+)*\\W+"),unknownPat = Pattern.compile(String.valueOf(x));
		Operator nextOperator,topOperator;
		String nxtOpt;
		stack.push(this.operatorMap.get("$"));
		double theNum;
		StringBuilder strSufix = new StringBuilder();
		sufix = new ArrayList<ExpressionItem>();
		//System.out.println("node 8");
		for(;s.hasNext();)
		{
			//System.out.println("node 14");
			if(s.hasNextDouble())
			{
				theNum = s.nextDouble();
				strSufix.append(theNum + " ");
				sufix.add(new Operand(theNum,opdStack));
				//System.out.println("node 9: " + theNum);
			}
			else if(s.hasNext(unknownPat))
			{
				s.next(unknownPat);
				strSufix.append(x + " ");
				sufix.add(new Unknown(opdStack));
			}
			else if(s.hasNext(opPat))
			{
				//System.out.println("node 11");
				nxtOpt = s.next(opPat);
				//System.out.println("node 12");
				nextOperator = this.operatorMap.get(nxtOpt);
				//System.out.println("node 13");
				topOperator = stack.peek();
				//System.out.println("node 10: " + nxtOpt + " " + topOperator);
				for(int priority = nextOperator.getInStackPriority();topOperator.getOutStackPriority()>=priority;topOperator = stack.peek())
				{
					if(nxtOpt.equals("#")&&(stack.size()==1))
						break;
					strSufix.append(topOperator + " ");
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
		this.strSufix = strSufix.substring(0,strSufix.length()-1);
		
		newestOrNot = false;
		setOrNot = false;
	}
	private HashMap<String,Operator> cloneMap(HashMap<String,Operator> map)
	{
		HashMap<String,Operator> newMap = new HashMap<String,Operator>();
		Set<Map.Entry<String,Operator>> set = map.entrySet();
		Iterator<Map.Entry<String,Operator>> it = set.iterator();
		for(;it.hasNext();)
		{
			Map.Entry<String,Operator> ele = it.next();
			newMap.put(ele.getKey(),(Operator)ele.getValue().clone());
			//System.out.println("node 20: " + ele.getKey() + " " + ele.getValue());
		}
		return newMap;
	}
	public Object clone()
	{
		return new Expression(this.operator,this.inStackPriority,this.outStackPriority,this.infix,this.operatorMap,this.x);
	}
	public void solve(double k)
	{
		double[] x = {k};
		value = this.solve(x);
		setOrNot = true;
		newestOrNot = true;
	}
	public void solve()
	{
		this.solve(0);
	}
	public double solve(double[] x)
	{
		opdStack.clear();
		ListIterator<ExpressionItem> it = sufix.listIterator();
		for(;it.hasNext();)
			it.next().execute(x[0]);
		return opdStack.pop();
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
	protected ArrayList<ExpressionItem> getArraySufix()
	{
		return this.sufix;
	}
	public HashMap<String,Operator> getOperators()
	{
		return this.operatorMap;
	}
}
