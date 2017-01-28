package alls.algorithms.math.Expression;
import java.util.ArrayDeque;
import java.util.Scanner;
public class Expression
{
	private String sufix;
	private int value;
	public Expression(String infix)
	{
		String formatted = new StringBuilder(infix).append("#");
		Scanner s = new Scanner(formatted);
		ArrayDeque<Integer> nums = new ArrayDeque<Integer>();
		ArrayDeque<Character> operator = new ArrayDeque<Character>();
		String pattern = "[\\(!^*/+-#\\)$]";
		operator.push('$');
		char nextOperator;
		char topOperator;
		topOperator = '$':
		int theNum;
		StringBuilder sufix = new StringBuilder();
		for(;s.hasNext();)
		{
			if(s.hasNextInt())
			{
				theNum = s.nextInt();
				nums.push(theNum);
				sufix.append(theNum);
			}
			else if(s.hasNext(pattern))
			{
				nextOperator = s.next(pattern).charAt(0);
				topOperator = s.peek();
				for(int priority = this.getInStackPriority(nextOperator);this.getOutStackPriority(topOperator)>priority;)
				{
					sufix.append(topOperator);
				}
			}
			else ;
		}
	}
	private int getPriority(char operator,boolean inOrNot)
	{
		switch(operator)
		{
			case '(':
				return inOrNot:Integer.MAX_VALUE?1;
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
	}
	private int getInStackPriority(char operator)
	{
		return this.getPriority(operator,true);
	}
	private int getOutStackPriority(char operator)
	{
		return this.getPriority(operator,false);
	}
	private int solve(int a,int b,char operator)
	{
	}
	private int solve(int a,char operator)
	{
	}
	private int power(int a,int x)
	{
	}
	private int frac(int n)
	{
	}
