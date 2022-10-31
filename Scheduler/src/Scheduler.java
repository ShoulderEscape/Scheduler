import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scheduler {
    public static List<Integer> Name;
    public static List<Integer> AT;
    public static List<Integer> BT;
    public static List<Integer> getBT() { return BT; }
    public static List<Integer> getAT() { return AT; }
    public static List<Integer> getName() { return Name; }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which scheduling method wo8udl you like to use?");
        System.out.println("----------------------------------------------");
        System.out.println("1: First Come, First Served");
        System.out.println("2: Shortest Job First");
        System.out.println("3: Round Robin");
        System.out.println("----------------------------------------------");
        int choice = sc.nextInt();
        File input = new File("src/input.txt");
        Scanner reader = new Scanner(input);

        int i = 0;
        Name = new ArrayList<>();
        AT = new ArrayList<>();
        BT = new ArrayList<>();

        while (reader.hasNext()) {
            if (i % 3 == 0) {
                Name.add(Integer.parseInt(reader.next()));
            } else if (i % 3 == 1) {
                AT.add(Integer.parseInt(reader.next()));
            } else {
                BT.add(Integer.parseInt(reader.next()));
            }
            i++;
        }
        FCFS FCFS = new FCFS();
        SJF SJF = new SJF();
        RR RR = new RR();

        int k = i / 3;
        switch (choice) {
            case (1) -> FCFS.run(k);
            case (2) -> SJF.run(k);
            case (3) -> RR.run(k);
        }
    }
    static void write(int n, List<Integer> turnT) {
        List<Integer> WaitT= new ArrayList<>();
        for (int j = 0; j < n; j++) {
            WaitT.add(turnT.get(j)- BT.get(j));
        }
        int avgta=0;
        int avgwt=0;
        for (int i = 0; i < n; i++) {
            avgta+= turnT.get(i);
            avgwt+=WaitT.get(i);
        }
        writeout(n,WaitT, turnT,avgta,avgwt);
    }
    static void writeout(int n, List<Integer> WaitT, List<Integer> TurnT, int avgta, int avgwt){
        System.out.println("\npid  arrival brust  complete turn waiting");
        for (int i = 0; i < n; i++) {
            System.out.println("Pid:" + Name.get(i) + "\twaiting: " + WaitT.get(i) + "\t\tTurnaround: " + TurnT.get(i));
        }
        System.out.println ("\naverage turnaround is "+ (float)(avgta/n));
        System.out.println ("average waiting time is "+ (float)(avgwt/n));
    }
}
