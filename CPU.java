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
        int blockedCycles = 0;
        Process previousProcess = null;
        Process currentProcess = null;
        boolean isLoaded[] = new boolean[processes.length];
        for (int i =0;i<processes.length;i++){
            isLoaded[i] = false;
        }
        //Arrays.fill(isLoaded,false);

        while(!scheduler.processes.isEmpty() || processesLoaded < processes.length ){
            for (int i=0;i<processes.length;i++){
                if (mmu.loadProcessIntoRAM(processes[i])){
                    //if (processes[i].GetArrivalTime() <= clock && !isLoaded[i]) {
                    if (processes[i].getPCB().getState() == ProcessState.NEW && processes[i].GetArrivalTime() <= clock) {
                        processes[i].getPCB().setState(ProcessState.READY,clock);
                        scheduler.addProcess(processes[i]);
                        isLoaded[i] = true;
                        System.out.println("added proccess " + isLoaded[i] + " " + clock);
                        processesLoaded++;
                        blockedCycles++;        //chahge it with  set state
                        break;


                    }
                }
            }


            if(blockedCycles > 0){
                blockedCycles--;
                continue;
            }


            if(scheduler instanceof RoundRobin && scheduler.processes.size() <= ((RoundRobin) scheduler).GetQuantum()){
                for (int i=0;i<processes.length;i++){
                    if(processes[i].getPCB().getState() == ProcessState.READY || processes[i].GetBurstTime() > ((RoundRobin) scheduler).GetQuantum() ){
                        scheduler.addProcess(processes[i]);
                        System.out.println("found process not finished reloading it" + i);
                    }
                    /*
                    if (processes[i].GetBurstTime() > 0 && processes[i].GetArrivalTime() <= clock && isLoaded[i]) {  //TODO only one process can change state to running from ready  time 2 clocks , change state here
                        scheduler.addProcess(processes[i]);


                    }*/
                }
            }




            currentProcess = scheduler.getNextProcess();
            if(currentProcess == null){
                System.out.println("CPU Idle");
            }else {
                if (currentProcess != previousProcess && previousProcess != null && previousProcess.GetBurstTime() >0){
                    //process push to background and start new
                    System.out.println("Process moved to background + start of another process");
                    previousProcess.waitInBackground();
                    currentProcess.run();

                }else if (currentProcess != previousProcess) {
                    // just start process either first process or the previous terminated
                    System.out.println("Process started running");
                    currentProcess.run();
                }
                previousProcess = currentProcess;
                //continue;

            }


            tick();

            System.out.println("Total ticks : " + clock);


            //add processes check arrival depending on arrival time             // TODO think when a process can be added depending on ram
        }

        System.out.println("Total ticks : " + --clock);
        /* TODO: you need to add some code here
         * Hint: you need to run tick() in a loop, until there is nothing else to do... */

    }
    
    public void tick() {
        /*
        Process p;
        p = scheduler.getNextProcess();
        if(p == null){
            System.out.println("CPU Idle");
        }else {
            p.run();
            p.waitInBackground();
        }
        */
        clock++;
        /* TODO: you need to add some code here
         * Hint: this method should run once for every CPU cycle */
        
    }
}
