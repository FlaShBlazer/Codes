import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
public class RoundRobin1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> process=new ArrayList<Integer>();
		List<Integer> arrivaltime=new ArrayList<Integer>();
		List<Integer> bursttime=new ArrayList<Integer>();
		List<Boolean> done=new ArrayList<Boolean>();
		List<Boolean> started=new ArrayList<Boolean>();
		
		List<Integer> bursttimesecond=new ArrayList<Integer>();
		Queue<Integer> ready=new LinkedList<Integer>();
		List<Integer> waitingtime=new ArrayList<Integer>();
		List<Integer> turnaroundtime=new ArrayList<Integer>();
		List<Integer> completiontimeeach=new ArrayList<Integer>();
		
		Scanner obj=new Scanner(System.in);
		
		int n, timequantum=0;
		
		System.out.print("Enter the time quantum for each process: ");
		timequantum=obj.nextInt();
		
		System.out.print("Enter number of processes: ");
		n=obj.nextInt();
		
		for(int i=0;  i<n; i++){
			process.add(i+1);
			System.out.print("Enter the arrival time for process "+(i+1)+": ");
			arrivaltime.add(obj.nextInt());
			System.out.print("Enter the burst time: ");
			bursttime.add(obj.nextInt());
			done.add(false);
			started.add(false);
			completiontimeeach.add(0);
		}
		
		//Sort according to arrival time
		for(int i=0; i<n-1; i++){
			for(int j=0; j<n-1; j++){
				if(arrivaltime.get(j)>arrivaltime.get(j+1)){
					
					int temp=arrivaltime.get(j);
					arrivaltime.set(j, arrivaltime.get(j+1));
					arrivaltime.set(j+1, temp);
					
					temp=bursttime.get(j);
					bursttime.set(j, bursttime.get(j+1));
					bursttime.set(j+1, temp);
					
					temp=process.get(j);
					process.set(j, process.get(j+1));
					process.set(j+1, temp);
				}
			}
		}
		
		System.out.println("\nProcess\tAT\tBT\tPriority");
		for(int i=0;  i<n; i++){
			System.out.print(process.get(i)+"\t");
			System.out.print(arrivaltime.get(i)+"\t");
			System.out.println(bursttime.get(i));
			
			bursttimesecond.add(bursttime.get(i));
		}
		
		int completiontime=arrivaltime.get(0), completedprocesses=0, k=-1;
		
		while(completedprocesses<n){
			for(int i=0; i<n; i++){
				if(arrivaltime.get(i)<=completiontime && done.get(i)!=true && started.get(i)!=true){
					ready.add(i);
					started.set(i, true);
				}
			}
			
			if(k!=-1){
				ready.add(k);
				k=-1;
			}
			
			if(bursttimesecond.get(ready.peek())<=timequantum){
				completiontime+=bursttimesecond.get(ready.peek());
				completiontimeeach.set(ready.peek(), completiontime);
				bursttimesecond.set(ready.peek(), 0);
				done.set(ready.peek(), true);
				completedprocesses++;
				ready.poll();
			}
			else{
				completiontime+=timequantum;
				bursttimesecond.set(ready.peek(), bursttimesecond.get(ready.peek())-timequantum);
				k=ready.poll();
			}	
		}
		
		for(int i=0; i<n; i++){
			turnaroundtime.add(completiontimeeach.get(i)-arrivaltime.get(i));	//turn around time calculation
			waitingtime.add(turnaroundtime.get(i)-bursttime.get(i));			//waiting time calculation
		}
		
		float avgwt=0, avgtat=0;
		
		System.out.println("\nProcess\tAT\tBT\tCT\tWT\tTT");
		for(int i=0;  i<n; i++){
			System.out.print(process.get(i)+"\t");
			System.out.print(arrivaltime.get(i)+"\t");
			System.out.print(bursttime.get(i)+"\t");
			System.out.print(completiontimeeach.get(i)+"\t");
			System.out.print(waitingtime.get(i)+"\t");
			System.out.println(turnaroundtime.get(i));
			
			avgwt+=waitingtime.get(i);
			avgtat+=turnaroundtime.get(i);
		}
		
		avgwt/=n;
		avgtat/=n;
		System.out.println("\nAverage Waiting Time: "+avgwt);
		System.out.println("Average Turnaround Time: "+avgtat);
	}

}
