import mpi.*;

public class MPI_Scatter_Gather_Demo 
{
    public static void main(String[] args) throws Exception 
    {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank() ;
        int size = MPI.COMM_WORLD.Size() ; 
        System.out.println("size is"+size);
        int unitsize=2;
        int root=0; 
	int[] send_buffer=null;    
	//if(rank==0){
	send_buffer = new int[]{0,1,2,3,4,5,6,7};
	//}
	int recv_buffer[] = new int[unitsize];
	float gathsend[]=new float[1];
	float gathrecv[]=new float[4];	
		
	MPI.COMM_WORLD.Scatter(
	/*java.lang.Object sendbuf*/	send_buffer,
	/*int sendoffset*/				0,
	/*int sendcount*/				2,
	/*Datatype sendtype*/			MPI.INT,
	/*java.lang.Object recvbuf*/	recv_buffer,
	/*int recvoffset*/				0,
	/*int recvcount*/				2,
	/*Datatype recvtype*/			MPI.INT,
	/*int root*/					root
	);	
	int avg=0;
	for(int i=0; i<2; i++)
	{
		 System.out.println("Process "+rank+" recieved num ="+recv_buffer[i]);
		avg+=recv_buffer[i];
	}
	gathsend[0]=avg/2f;
	MPI.COMM_WORLD.Gather(
	/*java.lang.Object sendbuf,*/	gathsend,
        /*int sendoffset,*/			0,
        /*int sendcount,*/			1,
        /*Datatype sendtype,*/		MPI.FLOAT,
        /*java.lang.Object recvbuf,*/  gathrecv,
        /*int recvoffset,*/			0,
        /*int recvcount,*/			1,
        /*Datatype recvtype,*/		MPI.FLOAT,
        /*int root*/					root
        );
	if(rank == root)
	{
	float res=0;
		for(int i=0; i < (4); i++)
		{	
		System.out.println(gathrecv[i] + " "); 
		res+=gathrecv[i];			
		}
		//System.out.println("Result is :"+res);
		res=res/4f;
		System.out.println("Result is :"+res);
	}		
        MPI.Finalize();
    }   
}