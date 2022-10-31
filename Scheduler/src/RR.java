import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//Round Robin
public class RR {
    List<Integer> name=Scheduler.getName();
    List<Integer> at=Scheduler.getAT();
    List<Integer> bt=Scheduler.getBT();
    void run(int n){
        System.out.println("Which time quanta do you want to use?");
        Scanner intread = new Scanner(System.in);
        int T=intread.nextInt();
        List<Integer> bttemp = new ArrayList<>(bt);
        int max=0;
        for (int j = 0; j < n; j++) {
            if(bt.get(j)>max){
                max=bt.get(j);
            }
        }
        List<Integer>TurnT=new ArrayList<>();
        for (int j = 0; j < n; j++) {
            TurnT.add(j);
        }
        int Total=0;
        for (int h = 0; h < max/T; h++) {
            for (int j = 0; j < n; j++) {
                if(bttemp.get(j)!=0){
                    if(bttemp.get(j)<=T){
                        Total+=bttemp.get(j);
                        bttemp.set(j,0);
                        TurnT.set(j,Total- at.get(j));
                    } else{
                        Total+=T;
                        bttemp.set(j,bttemp.get(j)-T);
                    }
                }
            }
        }
        for (int j = 0; j < n; j++) {
            System.out.println(TurnT.get(j));
        }
        Scheduler.write(n, TurnT);
    }
}
