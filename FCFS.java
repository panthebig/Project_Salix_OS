
public class FCFS extends Scheduler {

    public FCFS() {
        /* TODO: you _may_ need to add some code here */
    }

    public void addProcess(Process p) {
        processes.add(p);
        /* TODO: you need to add some code here */
    }
    
    public Process getNextProcess() {

        try {
            if (processes.get(0).GetBurstTime()==0){
                System.out.println("Proccess 0 finished FCFS");
                removeProcess(processes.get(0));
            }
            processes.get(0).SetBurstTime(processes.get(0).GetBurstTime()-1);
            //reduce burst time


            /* TODO: you need to add some code here
             * and change the return value */
            return processes.get(0);

        }catch (Exception exception){       //if exception no active proccesses
            System.out.println(exception);
            return null;
        }
    }
}
