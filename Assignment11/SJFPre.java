import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SJFPre {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> process=new ArrayList<Integer>();
		List<Integer> arrivaltime=new ArrayList<Integer>();
		List<Integer> bursttime=new ArrayList<Integer>();
		List<Integer> waitingtime=new ArrayList<Integer>();
		List<Integer> turnaroundtime=new ArrayList<Integer>();
		Scanner obj=new Scanner(System.in);
		
		int n;
		System.out.print("Enter number of processes: ");
		n=obj.nextInt();
		
		for(int i=0;  i<n; i++){
			process.add(i+1);
			System.out.print("Enter the arrival time for process "+(i+1)+": ");
			arrivaltime.add(obj.nextInt());
			System.out.print("Enter the burst time: ");
			bursttime.add(obj.nextInt());
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
		
		System.out.println("\nProcess\tAT\tBT");
		for(int i=0;  i<n; i++){
			System.out.print(process.get(i)+"\t");
			System.out.print(arrivaltime.get(i)+"\t");
			System.out.println(bursttime.get(i));
		}
		
		List<Integer> bursttimesecond=new ArrayList<Integer>();
		List<Integer> completiontimeeach=new ArrayList<Integer>();
		List<Boolean> flag=new ArrayList<Boolean>();
		for(int i=0; i<n; i++){
			flag.add(false);
			completiontimeeach.add(0);
			bursttimesecond.add(bursttime.get(i));
		}
		
		int completiontime=arrivaltime.get(0), completedprocesses=0;
		int tempminbursttime; 
		while(completedprocesses<n){	//terminate when all process completed
	    	int c=n;					//contains current process
	    	tempminbursttime=9999;		//contains remaining burst time of current process
	    	
	    	for (int i=0;i<n;i++){		//find the process with minimum burst time
	    		if ((arrivaltime.get(i)<=completiontime) && (flag.get(i)==false) && (bursttimesecond.get(i)<tempminbursttime)){	
	    			tempminbursttime=bursttimesecond.get(i);
	    			c=i;
	    		}
	    	}
	    	
	    	if (c==n)					//if minimum burst time process not found increase time by 1
	    		completedprocesses++;
	    	else{
	    		bursttimesecond.set(c, bursttimesecond.get(c)-1);	//decrement burst time by 1 of current process
	    		completiontime++;									//increment completion time by 1
	    		if (bursttimesecond.get(c)==0){						//if burst time of current process is 0
	    			completiontimeeach.set(c, completiontime);		//set completion time for the process
	    			flag.set(c, true);								//set flag to true to indicate completed process
	    			completedprocesses++;							//increment completed process counter by 1
	    		}
	    	}
	    }		
		
		for(int i=0; i<n; i++){
			turnaroundtime.add(completiontimeeach.get(i)-arrivaltime.get(i));	//turn around time calculation
			waitingtime.add(turnaroundtime.get(i)-bursttime.get(i));			//waiting time calculation
		}
		
		float avgwt=0, avgtat=0;
		
		System.out.println("\nProcess\tAT\tBT\tWT\tTT");
		for(int i=0;  i<n; i++){
			System.out.print(process.get(i)+"\t");
			System.out.print(arrivaltime.get(i)+"\t");
			System.out.print(bursttime.get(i)+"\t");
			System.out.print(waitingtime.get(i)+"\t");
			System.out.println(turnaroundtime.get(i));
			
			avgwt+=waitingtime.get(i);
			avgtat+=turnaroundtime.get(i);
		}
		
		avgwt/=n;
		avgtat/=n;
		System.out.println("\nAverage Waiting Time: "+avgwt);
		System.out.println("Average Turnaround Time: "+avgtat);
		
		obj.close();
	}

}
