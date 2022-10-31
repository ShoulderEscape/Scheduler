import java.util.ArrayList;
import java.util.List;
//Shortest Job First
public class SJF {
    List<Integer> name=Scheduler.getName();
    List<Integer> at=Scheduler.getAT();
    List<Integer> bt=Scheduler.getBT();

    void run(int n){
        int[] f = new int[n]; //flagga om process Ã¤r klar
        int[] complete = new int[n];//complete time
        List<Integer> turnAr = new ArrayList<>();//turnaround time
        List<Integer> waiting = new ArrayList<>();//waiting time
        for (int i = 0; i < n; i++) {
            turnAr.add(0);
            waiting.add(0);
        }
        int avgwt=0, avgta=0;

        int numberOfCompletedProcesses=0, time=0;
        while(true) {
            int c=n, inCPU=Integer.MAX_VALUE;
            if (numberOfCompletedProcesses == n) // break if completed processes = number of processes
                break;

            for (int i=0; i<n; i++) {
                // arrival <= time && process != done && burst < nuvarande burst time i cpu
                if ((at.get(i) <= time) && (f[i] == 0) && (bt.get(i)<inCPU))
                {
                    inCPU=bt.get(i);
                    c=i;
                }
            }
            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c==n) {
                time++;
            }else{
                complete[c]=time+bt.get(c);
                time+=bt.get(c);
                turnAr.set(c, complete[c] - at.get(c));
                waiting.set(c, turnAr.get(c) - bt.get(c));
                f[c]=1;
                avgwt += waiting.get(c);
                avgta += turnAr.get(c);
                numberOfCompletedProcesses++;
            }
        }
        Scheduler.writeout(n, waiting,turnAr, avgta, avgwt);

    }

}