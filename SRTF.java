
public class SRTF extends Scheduler {

    public SRTF() {
        /* TODO: you _may_ need to add some code here */
    }

    public void addProcess(Process p) {
        //int index=0;
        //select index for entry
        processes.add(p);
        /* TODO: you need to add some code here */
    }
    
    public Process getNextProcess() {
        try {
            int index = 0;
            for (int i=0;i<processes.size();i++){
                if(processes.get(i).GetBurstTime()==0){
                    processes.remove(i);
                    System.out.println("Proccess"+ i+1 +" finished SRTF");
                    i--;
                    continue;
                }
                if (i==0){
                    index = processes.get(0).GetBurstTime();
                }
                if (processes.get(i).GetBurstTime() < processes.get(0).GetBurstTime()){                 //TODO rethink this loop
                    index = i;
                }
            }
            processes.get(index).SetBurstTime(processes.get(index).GetBurstTime()-1);
            //reduce burst time -1
            /* TODO: you need to add some code here
             * and change the return value */
            return processes.get(index);

        }catch (Exception exception){
            System.out.println(exception);
            return null;
        }
    }
}