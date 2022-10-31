import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
//First Come First Serve

public class FCFS {
    List<Integer> name=Scheduler.getName();
    List<Integer> at=Scheduler.getAT();
    List<Integer>bt=Scheduler.getBT();
    void run(int n){
        for (int j = 0; j < n; j++) {
            for (int l = 1; l < n-j; l++) {
                if(at.get(l-1)>at.get(l)){
                    int temp;
                    temp=at.get(l-1);
                    at.set(l-1,at.get(l));
                    at.set(l, temp);
                    temp=name.get(l-1);
                    name.set(l-1,name.get(l));
                    name.set(l, temp);
                    temp=bt.get(l-1);
                    bt.set(l-1,bt.get(l));
                    bt.set(l, temp);
                }
            }
        }
        List<Integer> TurnT= new ArrayList();
        int Total=0;
        for (int j = 0; j < n; j++) {
            if(Total<at.get(j)){
                Total+=at.get(j)-Total;
            }
            Total+=bt.get(j);
            TurnT.add(Total-at.get(j));
        }

        Scheduler.write(n, TurnT);

    }


}
