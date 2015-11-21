package RefactoredTBS.taxibooking;

import RefactoredTBS.TBSInterface.AvailableCabesInfo;
import RefactoredTBS.TBSInterface.AvailableCabesatStationInfo;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Station implements AvailableCabesatStationInfo {

    private ArrayList<AvailableCabesInfo> AvailabelTaxies;
    private Queue<Taxi> AllocationQ = new PriorityQueue<Taxi>();

    public Station() {
        AvailabelTaxies = new ArrayList<AvailableCabesInfo>();
    }

    @Override
    public void addCabe(AvailableCabesInfo taxi) {
        AvailabelTaxies.add(taxi);
    }

    @Override
    public Taxi isAvailable(Tour tour, String StartingTime) {
        int Count = 0;
        Taxi taxi;
        //boolean Booked=false;
        while (Count < AvailabelTaxies.size()) {
            taxi = AvailabelTaxies.get(Count++).bookFreeSlot(tour, StartingTime);
            if (taxi != null) {
                AllocationQ.add(taxi);
            }
        }
        if (AllocationQ.isEmpty()) {
            return null;
        } else {
            taxi = AllocationQ.poll();
            taxi.addTour(tour);
            cleanAllocationQ();
            return taxi;
        }
    }

    private void cleanAllocationQ() {
        if (!AllocationQ.isEmpty()) {
            AllocationQ.clear();
        }
    }

}
