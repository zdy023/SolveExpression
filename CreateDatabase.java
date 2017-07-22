//CreateDatabase.java
import org.apache.derby.jdbc.EmbeddedDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class CreateDatabase
{
	public static void main(String[] args)
	{
		try
		{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection con = DriverManager.getConnection("jdbc:derby:operator;create=true");
			try
			{
				PreparedStatement crt = con.prepareStatement("create table Operator (operator varchar(10) constraint consOperator not null primary key,operatorClass varchar(30) constraint consClass not null unique,inStackPriority smallint default null,outStackPriority smallint default null,operandCount smallint constraint consOperand not null)");
				if(!crt.execute())
					throw new SQLException("create error!");
				crt.close();
			}
			catch(SQLException e)
			{
				if((e.getSQLState()!=null)&&(!e.getSQLState().equals("X0Y32")))
				{
					System.out.println(e);
					System.exit(0);
				}
				System.out.println(e);
			}
			PreparedStatement ist = con.prepareStatement("insert into Operator (operator,operatorClass,inStackPriority,outStackPriority,operandCount) values ('+','Plus',5,5,2),('-','Minus',5,5,2),('*','Multiply',8,8,2),('/','Divide',8,8,2),('mod','Module',6,6,2),('^','Power',10,9,2),('$','Head',null,1,0),('#','Tail',0,null,0),(')','RightBracket',0,null,0),('(','LeftBracket',15,1,0),('sin(','Sine',15,1,1),('cos(','Cosine',15,1,1),('tan(','Tangent',15,1,1),('e^(','Exponential',15,1,1),('sinh(','HyperbolicSine',15,1,1),('cosh(','HyperbolicCosine',15,1,1),('tanh','HyperbolicTangent',15,1,1),('arcsin(','ArcSine',15,1,1),('arccos(','ArcCosine',15,1,1),('arctan(','ArcTangent',15,1,1),('ln(','Ln',15,1,1),('lg(','Lg',15,1,1)");
			if(ist.executeUpdate()==11) ;
			else
				throw new SQLException("insert error");
			ist.close();
			con.close();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e);
			System.exit(0);
		}
		try
		{
			Connection con1 = DriverManager.getConnection("jdbc:derby:operator;shutdown=true");
		}
		catch(SQLException e)
		{
			if(!e.getSQLState().equals("08006"))
				System.out.println(e);
		}
	}
}
