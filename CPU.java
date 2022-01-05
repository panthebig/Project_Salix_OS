import java.util.ArrayList;
import java.util.Arrays;

public class CPU {

    public static int clock = 0; // this should be incremented on every CPU cycle
    
    private Scheduler scheduler;
    private MMU mmu;
    private Process[] processes;
    private int currentProcess;
    
    public CPU(Scheduler scheduler, MMU mmu, Process[] processes) {
        this.scheduler = scheduler;
        this.mmu = mmu;
        this.processes = processes;
    }
    
    public void run() {



        int tick=0;
        int processesLoaded = 0;
        boolean isLoaded[] = new boolean[processes.length];
        Arrays.fill(isLoaded,false);

        while(!scheduler.processes.isEmpty() || processesLoaded < processes.length ){
            for (int i=0;i<processes.length;i++){
                if (processes[i].GetArrivalTime() <= tick && !isLoaded[i]) {   //TODO change the == to >= depending on mem && process not loaded
                    scheduler.addProcess(processes[i]);
                    isLoaded[i] = true;
                    System.out.println("added proccess " + isLoaded[i]);
                    processesLoaded++;
                }
            }

            /*for (Process p :
                    processes) {
                if (p.GetArrivalTime() == tick) {
                    scheduler.addProcess(p);
                    processesLoaded++;
                }
            }*/
            tick();
            tick++;
            //add processes check arrival depending on arrival time             // TODO think when a process can be added depending on the ram
        }
        /* TODO: you need to add some code here
         * Hint: you need to run tick() in a loop, until there is nothing else to do... */

    }
    
    public void tick() {
        Process p;
        p = scheduler.getNextProcess();
        if(p == null){
            System.out.println("CPU Idle");
        }
        /* TODO: you need to add some code here
         * Hint: this method should run once for every CPU cycle */
        
    }
}
