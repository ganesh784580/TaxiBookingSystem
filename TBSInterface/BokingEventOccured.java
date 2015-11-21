

package RefactoredTBS.TBSInterface;

import RefactoredTBS.taxibooking.Taxi;
import RefactoredTBS.taxibooking.Tour;


public interface BokingEventOccured {

    public Taxi isTaxiAvailable(Tour tour);
 
}
