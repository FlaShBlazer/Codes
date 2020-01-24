import java.io.*;
import java.util.*;
public class FCFS
{
	public void execute(int n,int at[],int bt[]) throws Exception
	{
		int temp;
		
		
		int pid[]=new int[n];
		int ct[]=new int[n];
		int ta[]=new int[n];
		int wt[]=new int[n];
		float avgwt=0,avgtat=0;
		System.out.println("Enter Arrival Time:");
		for(int i=0;i<n;i++)
		{
			pid[i]=i+1;
		}
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n-(i+1);j++)
			{
				if(at[j]>at[j+1])
				{
					temp=at[j];
					at[j]=at[j+1];
					at[j+1]=temp;
					temp=bt[j];
					bt[j]=bt[j+1];
					bt[j+1]=temp;
					temp=pid[j];
					pid[j]=pid[j+1];
					pid[j+1]=temp;
				}
			}
		}
		for(int i=0;i<n;i++)
		{
			if(i==0)
				ct[i]=at[i]+bt[i];
			else
			{
				if(at[i]>ct[i-1])
					ct[i]=at[i]+bt[i];
				else
					ct[i]=ct[i-1]+bt[i];
			}
			ta[i]=ct[i]-at[i];          // turnaround time= completion time- arrival time
			wt[i]=ta[i]-bt[i];          // waiting time= turnaround time- burst time
			avgwt+=wt[i];               // total waiting time
			avgtat+=ta[i];               // total turnaround time
		}
		System.out.println("\nPID     Arrival     Burst    Complete    Turn     Waiting");
		for(int i=0;i<n;i++)
		{
			System.out.println(pid[i] + "  \t    " + at[i] + "\t    " + bt[i] + "\t       " + ct[i] + "\t   " + ta[i] + "\t    "  + wt[i]);
		}
		System.out.println("\nAverage waiting time :"+(avgwt/n));     // printing average waiting time.
		System.out.println("Average turnaround time:"+(avgtat/n));    // printing average turnaround time.
	}
}

