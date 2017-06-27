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
*用于存储一个后缀表达式，由中缀表达式构造后缀表达式,并求值.
*<p>以该类为核心的表达式API是可扩展的。该类内部未提供具体运算的实现，具体的运算可由Operator的子类动态扩展。</p>
*<p>该类同样被实现为Operator的子类，故可以由已给出的运算构造复杂的运算符。在数学表达式中，新运算符可以函数的形式书写。新操作符为一元运算符。</p>
*<p>该类实现了接口xyz.davidChangx.algorithms.Function接口，故可以在程序中作为数学函数调用的。</p>
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
	*<p>由带有未知数中缀表达式构造后缀表达式。得到的后缀表达式中的未知数可以被赋以具体值。</p>
	*<p>得到的后缀表达式可以作为新的运算符使用。通过该方法构造的后缀表达式默认的用于数学表达式中的函数名为f。入栈优先级为15，出栈优先级为1。</p>
	*@param infix infix expression
	*@param operatorMap 运算符库
	*@param x 未知数的字母
	*/
	public Expression(String infix,HashMap<String,Operator> operatorMap,char x)
	{
		this("f",15,1,infix,operatorMap,x);
	}
	/**
	*构造方法.
	*<p>由不带未知数中缀表达式构造后缀表达式。得到的后缀表达式只含有常数。</p>
	*<p>得到的后缀表达式可以作为新的运算符使用。通过该方法构造的后缀表达式默认的用于数学表达式中的函数名为f。入栈优先级为15，出栈优先级为1。</p>
	*@param infix infix expression
	*@param operatorMap 运算符库
	*/
	public Expression(String infix,HashMap<String,Operator> operatorMap)
	{
		this(infix,operatorMap,'\0');
	}
	/**
	*构造方法.
	*<p>由带有未知数中缀表达式构造后缀表达式。得到的后缀表达式中的未知数可以被赋以具体值。</p>
	*<p>得到的后缀表达式可以作为新的运算符使用。通过该方法构造的后缀表达式带有用户指定的数学函数名。</p>
	*@param functionName 用在中缀表达式中的数学函数名
	*@param inStackPriority 作为新运算符的入栈优先级，建议为15
	*@param outStackPriority 作为新运算符的出栈优先级，建议为1
	*@param infix infix expression
	*@param operatorMap 运算符库
	*@param x 未知数的字母
	*/
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
	/**
	*私有方法，用于构造指定HashMap的深复制.
	*@param map the map to be cloned
	*@return the deeply copy of the map
	*/
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
	/**
	*返回该类的克隆.
	*@return 该类的克隆
	*/
	public Object clone()
	{
		return new Expression(this.operator,this.inStackPriority,this.outStackPriority,this.infix,this.operatorMap,this.x);
	}
	/**
	*求表达式的值，之后可调用getValue()方法获得该值.
	*<p>对于带有未知数的表达式，会将k代入未知数进行求值。对于不含未知数的表达式，k的值对表达式的值无影响。</p>
	*@param k 代入未知数的值
	*/
	public void solve(double k)
	{
		double[] x = {k};
		value = this.solve(x);
		setOrNot = true;
		newestOrNot = true;
	}
	/**
	*求表达式的值，之后可调用getValue()方法获得该值.
	*<p>对于带有未知数的表达式，该方法等同于solve(0)。</p>
	*/
	public void solve()
	{
		this.solve(0);
	}
	/**
	*将该类作为运算符，求在指定操作数下的值.
	*<p>与另外两个solve()方法不同，该方法为从Operator继承而来，因此带有返回值。但它不会更新表达式的值，不能通过getValue()方法获得该值。</p>
	*@param x 操作数数组，事实上这是一个一元运算符
	*@return 运算结果
	*/
	public double solve(double[] x)
	{
		opdStack.clear();
		ListIterator<ExpressionItem> it = sufix.listIterator();
		for(;it.hasNext();)
			it.next().execute(x[0]);
		return opdStack.pop();
	}
	/**
	*提供数学函数的实现.
	*<p>可用于SecantRoot等类中。<p>
	*<code>double r = ex.f(x); //ex is an instance of Expression</code>
	*<p>相当于：</p>
	*<code>
	*	ex.solve(x);
	*	double r = ex.getValue();
	*</code>
	*@param x 将要代入未知数的值
	*@return 求得的表达式的值
	*/
	public double f(double x)
	{
		this.solve(x);
		newesOrNot = false;
		return this.value;
	}
	/**
	*判断当前getValue()的值是否是最新的.
	*<p>每一次调用不带返回值的solve()方法后，该方法都将返回true,否则返回false。</p>
	*@return true - 值最新 false - 值已过时
	*/
	public boolean isNewest()
	{
		return newestOrNot;
	}
	/**
	*获得上一次调用不带返回值的solve()或f()方法求得的表达式的值.
	*<p>若首次调用getValue()之前从未调用过不带返回值的solve()方法或f()方法，则会自动调用不带参数的solve()方法。</p>
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
	/**
	*获得此表达式的ArrayList，其中元素为ExpressionItem.
	*@return 后缀表达式的ArrayList形式
	*/
	protected ArrayList<ExpressionItem> getArraySufix()
	{
		return this.sufix;
	}
	/**
	*返回该后缀表达式所用的运算符库.
	*@return 该后缀表达式所用的运算符库
	*/
	public HashMap<String,Operator> getOperators()
	{
		return this.operatorMap;
	}
}
