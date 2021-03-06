import java.io.*;
public class Scheduling 
{
	public static void main(String[] args) throws Exception
	{
		String a="";
		int n;
		System.out.println("Enter Number of Processes:");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		System.out.println("Enter Time Quantum:");
		int q=Integer.parseInt(br.readLine());
		int at[]=new int[n];
		int bt[]=new int[n];
		int p[]=new int[n];
		String pid[]=new String[n];
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter Arrival Time for Process "+(i+1)+":");
			at[i]=Integer.parseInt(br.readLine());
			System.out.println("Enter Burst Time for Process "+(i+1)+":");
			bt[i]=Integer.parseInt(br.readLine());
			System.out.println("Enter Priority for Process	"+(i+1)+":");
			p[i]=Integer.parseInt(br.readLine());	
			pid[i]="p"+i;
		}	
		
						System.out.println("FCFS:");
					FCFS fcfs=new FCFS();
					fcfs.execute(n,at,bt);
			
						System.out.println("SJFP:");
					SJFP sjf=new SJFP();
					sjf.execute(n,at,bt);
			
						System.out.println("Priority:");
					PriorityS pr=new PriorityS();
					pr.execute(n,at,p);
			
						System.out.println("Round-Robin:");
					RoundRobin r=new RoundRobin();        	
					r.roundRobin(pid,n,bt,at,q);
 		}
}
