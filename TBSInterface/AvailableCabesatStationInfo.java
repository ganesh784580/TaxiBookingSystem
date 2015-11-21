package RefactoredTBS.TBSInterface;

import RefactoredTBS.taxibooking.Taxi;
import RefactoredTBS.taxibooking.Tour;

public interface AvailableCabesatStationInfo {

    public Taxi isAvailable(Tour tour, String startingTime);

    public void addCabe(AvailableCabesInfo taxi);

}
