package RefactoredTBS.TBSInterface;

import RefactoredTBS.taxibooking.Taxi;
import RefactoredTBS.taxibooking.Tour;

public interface AvailableCabesInfo {

    public Taxi bookFreeSlot(Tour tour, String StartingTime);

}
