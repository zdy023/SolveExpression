//Test.java
import xyz.davidChangx.algorithms.math.operator.Operator;
import org.apache.derby.jdbc.EmbeddedDriver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.sql.SQLException;
import xyz.davidChangx.algorithms.math.Expression;
public class Test
{
	public static void main(String[] args)
	{
		try
		{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		HashMap<String,Operator> optMap = new HashMap<String,Operator>();
		try
		{
			//System.out.println("node 2");
			Connection con = DriverManager.getConnection("jdbc:derby:operator");
			//System.out.println("node 1");
			PreparedStatement query = con.prepareStatement("select operator,operatorClass from Operator");
			ResultSet rs = query.executeQuery();
			for(;rs.next();)
			{
				String operator = rs.getString("operator"),operatorName = rs.getString("operatorClass");
				Operator opt = (Operator)Class.forName("xyz.davidChangx.algorithms.math.operator." + operatorName).newInstance();
				optMap.put(operator,opt);
			}
			//System.out.println("node 3");
			rs.close();
			query.close();
			con.close();
		}
		catch(SQLException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		catch(InstantiationException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		catch(IllegalAccessException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		try
		{
			Connection con = DriverManager.getConnection("jdbc:derby:operator;shutdown=true");
		}
		catch(SQLException e)
		{
			if(!e.getSQLState().equals("08006"))
				System.out.println("Closing Error!\n" + e);
		}
		//System.out.println("node 6");
		Expression e1 = new Expression("sin( tan( 3 + 5 * 2 ) )",optMap);
		e1.solve();
		boolean test1 = e1.isNewest();
		System.out.println(test1);
		boolean test2 = e1.getValue()==Math.sin(Math.tan(13.));
		System.out.println(test2);
		boolean test3 = !e1.isNewest();
		System.out.println(test3);
		//System.out.println("node 7");
		Expression e2 = new Expression("3 + ( ( 5 - 2 ) + 3 ^ x ) - x",optMap,'x');
		//System.out.println("node 5");
		//System.out.println("node 15: " + e1.getSufix());
		//System.out.println("node 4");
		boolean test4 = e2.f(0.)==Test.e2(0.);
		System.out.println(test4);
		boolean test5 = !e2.isNewest();
		System.out.println(test5);
		e2.solve(5.);
		boolean test6 = e2.getValue()==Test.e2(5.);
		System.out.println(test6);
		Expression e3 = new Expression("fgo",15,1,"( 5 - 3 - 2 ) - ( 2 * x + e^( x ) - ln( x ) ) + ( sin( x + cosh( x ^ 2 - x + 4 ) ) - 2 )",optMap,'x');
		System.out.println(e3.getSufix());
		optMap.put("fgo(",e3);
		Expression e4 = new Expression("e^( fgo( x ) ) - arctan( x )",optMap,'x');
		//System.out.println("node 21: " + Test.fgo(5.) + " " + (e3.f(5.)==Test.fgo(5.)));
		System.out.println(e4.getSufix());
		boolean test7 = e4.f(5.)==Math.exp(Test.fgo(5.))-Math.atan(5.);
		System.out.println(test7);
		System.out.println(test1&&test2&&test3&&test4&&test5&&test6&&test7?"Right":"Wrong");
	}
	public static double e2(double x)
	{
		return 6.+Math.pow(3.,x)-x;
	}
	public static double fgo(double x)
	{
		return -2.*x-Math.exp(x)+Math.log(x)+Math.sin(x+Math.cosh(Math.pow(x,2.)-x+4.))-2.;
	}
}
