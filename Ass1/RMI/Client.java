import java.rmi.*;
public class Client
{
	public static void main(String args[]) throws Exception
	{
		AddI obj=(AddI)Naming.lookup("Add");
		int n=obj.add(5,4);
		System.out.println("addition is "+ n);
	}
}