

package taxibookingsystem;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;


public class Station {
    private ArrayList<Taxi> AvailabelTaxies; 
    private Queue<Taxi> AllocationQ=new PriorityQueue<Taxi>();
    
    public Station() {
        AvailabelTaxies=new ArrayList<Taxi>();
    }
    
    public void addTaxi(Taxi taxi){
        AvailabelTaxies.add(taxi);
    }

    public Taxi isAvailable(Tour tour,String StartingTime) {
        int Count=0;
        Taxi taxi;
        //boolean Booked=false;
        while(Count<AvailabelTaxies.size()){
        taxi=AvailabelTaxies.get(Count++).bookFreeSlot(tour,StartingTime);
        if(taxi!=null)
        AllocationQ.add(taxi);
        }
        if(AllocationQ.isEmpty())
            return null;
        else{
            taxi=AllocationQ.poll();
            taxi.addTour(tour); 
            cleanAllocationQ();
            return taxi;
        }
    }
    private void cleanAllocationQ(){
        if(!AllocationQ.isEmpty())
            AllocationQ.clear();
    }
    
}
