import java.rmi.*;
public class Server
{
	public static void main(String args[]) throws Exception
	{
		AddC obj=new AddC();
		Naming.rebind("Add",obj);
		System.out.println("Server Started");
		
	}
}