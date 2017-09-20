//SecantRoot.java
package xyz.davidchangx.algorithms.equation;
import xyz.davidchangx.algorithms.Function;
/**
*使用弦割法求实函数的零点.
*@version 1.2
*@author David Chang
*/
public final class SecantRoot
{
	/**
	*用于求解弦割零点的静态方法.
	*<p>此方法不保证总能在有限时间内求出期望的数值解。但总体上，该方法应优于二分法求解函数零点。在收敛的情况下，该方法不要求零点一定位于求解区间上，且可以比二分法更快的趋近于零点。</p>
	*@param a 求解区间的端点a
	*@param b 求解区间的端点b
	*@param f xyz.davidchangx.algorithms.Function类型的函数接口，在Java 1.8中可以使用λ表达式作为实参，同时可以使用xyz.davidchangx.algorithms.math.Expression作为实参
	*@see xyz.davidchangx.algorithms.Function
	*@param accuracy 目标精确度，最终的解x保证|f(x)|&lt;accuracy，因此应保证该参数为正数
	*@throws ArithmeticException 在求解过程中若出现f(a),f(b)或f(x)缺乏定义的情况，会抛出该错误。对于f(a)=f(b)的情况，不会抛出该错误。
	*@return x,s.t.&nbsp;|f(x)|&lt;accuracy
	*/
	public static double solve(double a,double b,Function f,double accuracy) throws ArithmeticException
	{
		if(accuracy<=0)
			throw new ArithmeticException("Wrong accuracy!The accuracy should be strictly bigger than zero!");
		double fa,fb,x;
		try
		{
			fa = f.f(a);
			fb = f.f(b);
		}
		catch(ArithmeticException e)
		{
			throw e;
		}
		try
		{
			x = (a*fb-b*fa)/(fb-fa);
		}
		catch(ArithmeticException e)
		{
			x = (a+b)/2.;
		}
		double fx;
		try
		{
			fx = f.f(x);
		}
		catch(ArithmeticException e)
		{
			throw e;
		}
		for(;Math.abs(fx)>=accuracy;)
		{
			if(Math.abs(x-a)<=Math.abs(x-b))
			{
				b = x;
				fb = fx;
			}
			else
			{
				a = x;
				fa = fx;
			}
			try
			{
				x = (a*fb-b*fa)/(fb-fa);
			}
			catch(ArithmeticException e)
			{
				x = (a+b)/2.;
			}
			try
			{
				fx = f.f(x);
			}
			catch(ArithmeticException e)
			{
				throw e;
			}
		}
		return x;
	}
}
