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
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		HashMap<String,Operator> optMap = new HashMap<String,Operator>();
		try
		{
			Connection con = DriverManager.getConnection("jdbc:derby:operator");
			PreparedStatement query = con.prepareStatement("select operator,operatorClass from Operator");
			ResultSet rs = query.executeQuery();
			for(;rs.next();)
			{
				String operator = rs.getString("operator"),operatorName = rs.getString("operatorClass");
				Operator opt = Class.forName("xyz.davidChangx.algorithms.math.operator." + operatorName).newInstance();
				optMap.put(operator,opt);
			}
			rs.close();
			query.close();
			con.close();
		}
		catch(SQLException e)
		{
			System.out.println(e);
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
		Expression e1 = new Expression("3 + 5 + ( 8 - 2 ) * 3 * sin( 3 )",optMap),e2 = new Expression("x ^ 3 + ( 2 * x ) ^ 2 - e^( x )",optMap,'x');
		e1.solve();
		System.out.println(e1.getValue());
		System.out.println(e2.f(0));
		System.out.println(e2.f(5));
		Expression e3 = new Expression("fgo",15,1,"e^( x ) * sin( 5 * x ) - arccos( 3. )",optMap,'x');
		optMap.put("fgo(",e3);
		Expression e4 = new Expression("3 * fgo( x )",optMap,'x');
		System.out.println(e4.f(5));
	}
}
