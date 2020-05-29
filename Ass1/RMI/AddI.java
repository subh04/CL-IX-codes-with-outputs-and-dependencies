import java.rmi.Remote;
public interface AddI extends Remote
{
	public int add(int x,int y) throws Exception;
}