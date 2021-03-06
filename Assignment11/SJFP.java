import java.io.*;
import java.util.*;
public class SJFP
{
	public void execute(int n,int at[],int bt[]) throws Exception{
		
		int pid[]=new int[n];
		int ct[]=new int[n];
		
		int ta[]=new int[n];
		int wt[]=new int[n];
		int f[]=new int[n];
		int k[]=new int[n];
		int i=0,st=0,tot=0;
		float avgwt=0,avgat=0;
		for(i=0;i<n;i++)
		{
			pid[i]=i+1;
			f[i]=0;
			k[i]=bt[i];						
		}
		while(true)
		{
			int min=99,c=n;
			if(tot==n)
				break;
			for(i=0;i<n;i++)
			{
				if(at[i]<=st && f[i]==0 && bt[i]<min)
				{	
					min=bt[i];
					c=i;
				}
			}	
			if(c==n)
				st++;
			else
			{	
				bt[c]--;
				st++;
				if(bt[c]==0)	
				{
					ct[c]=st;
					f[c]=1;
					tot++;
				}
			}
		}
		for(i=0;i<n;i++)
		{
			ta[i]=ct[i]-at[i];
			wt[i]=ta[i]-k[i];
			avgwt+=wt[i];
			avgat+=at[i];
		}
		System.out.println("PID  Arrival  Burst  Complete Turn Waiting");
		for(i=0;i<n;i++)
		{
			System.out.println(pid[i] +"\t"+ at[i]+"\t"+ k[i] +"\t"+ ct[i] +"\t"+ ta[i] +"\t"+ wt[i]);
		} 	
		System.out.println("\nAverage Turnaround Time: "+ (float)(avgat/n));
		System.out.println("Average Waiting Time: "+ (float)(avgwt/n));
	}
}
