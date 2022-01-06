import java.util.ArrayList;


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



        //int tick=0;
        int processesLoaded = 0;
        boolean isLoaded[] = new boolean[processes.length];
        for (int i =0;i<processes.length;i++){
            isLoaded[i] = false;
        }
        //Arrays.fill(isLoaded,false);

        while(!scheduler.processes.isEmpty() || processesLoaded < processes.length ){
            for (int i=0;i<processes.length;i++){
                if (mmu.loadProcessIntoRAM(processes[i])){
                    if (processes[i].GetArrivalTime() <= clock && !isLoaded[i]) {   //TODO change the == to >= depending on mem && process not loaded
                        processes[i].getPCB().setState(ProcessState.READY,clock);
                        scheduler.addProcess(processes[i]);
                        isLoaded[i] = true;
                        System.out.println("added proccess " + isLoaded[i]);
                        processesLoaded++;


                    }
                }
            }


            if(scheduler instanceof RoundRobin && scheduler.processes.size() == 1){
                for (int i=0;i<processes.length;i++){
                    if (processes[i].GetBurstTime() > 0 && processes[i].GetArrivalTime() <= clock && isLoaded[i]) {  //TODO rethink this
                        scheduler.addProcess(processes[i]);
                        //System.out.println("found process not finished reloading it");


                    }
                }
            }
            tick();
            //tick++;
            //add processes check arrival depending on arrival time             // TODO think when a process can be added depending on ram
        }

        System.out.println("Total ticks : " + --clock);
        /* TODO: you need to add some code here
         * Hint: you need to run tick() in a loop, until there is nothing else to do... */

    }
    
    public void tick() {
        Process p;
        p = scheduler.getNextProcess();
        if(p == null){
            System.out.println("CPU Idle");
        }else {
            p.run();
            p.waitInBackground();
        }
        clock++;
        /* TODO: you need to add some code here
         * Hint: this method should run once for every CPU cycle */
        
    }
}
