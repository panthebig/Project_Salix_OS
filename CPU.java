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
        int tick=0;
        while(scheduler.getNextProcess()!= null){
            tick();
            //proccess .add make sure the
        }
        /* TODO: you need to add some code here
         * Hint: you need to run tick() in a loop, until there is nothing else to do... */

    }
    
    public void tick() {
        Process p;
        p = scheduler.getNextProcess();
        if(p == null){
            return;
        }
        /* TODO: you need to add some code here
         * Hint: this method should run once for every CPU cycle */
        
    }
}
