
public class RoundRobin extends Scheduler {

    private int quantum;
    
    public RoundRobin() {
        this.quantum = 1; // default quantum
        /* TODO: you _may_ need to add some code here */
    }
    
    public RoundRobin(int quantum) {
        this();
        this.quantum = quantum;
    }

    public void addProcess(Process p) {
        for (int i=0;i<quantum;i++) {
            processes.add(p);
        }
        /* TODO: you need to add some code here */
    }
    
    public Process getNextProcess() {

        try {
            if (processes.get(0).GetBurstTime()==0) {
                System.out.println("finished process");
            }
            processes.remove(0);
            if (processes.get(0).GetBurstTime()==0){
                while (processes.get(0).GetBurstTime()==0) {
                    processes.remove(0);
                }
            }
            System.out.println(processes.get(0).GetBurstTime());
            processes.get(0).SetBurstTime(processes.get(0).GetBurstTime()-1);
            /*if(processes.get(0).GetBurstTime()!=0){
                addProcess(processes.get(0));
            }*/
            /*int index = 0;
            for (int i=0;i<processes.size();i++){
                if(processes.get(i).GetBurstTime()==0){
                    processes.remove(i);
                    System.out.println("Proccess"+ i+1 +" finished RoundRobin");
                    i--;
                    continue;
                }
                if (i==0){
                    index = processes.get(0).GetBurstTime();
                }
                if (processes.get(i).GetBurstTime() < processes.get(0).GetBurstTime()){
                    index = i;
                }
            }
            processes.get(index).SetBurstTime(processes.get(index).GetBurstTime()-1);
            //reduce burst time -1
            /* TODO: you need to add some code here
             * and change the return value */
            return processes.get(0);

        }catch (Exception exception){       //if exception no active proccesses
            System.out.println(exception);
            return null;
        }
        /* TODO: you need to add some code here
         * and change the return value */

    }
}
