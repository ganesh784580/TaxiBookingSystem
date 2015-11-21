

package RefactoredTBS.TBSInterface;

import RefactoredTBS.taxibooking.Tour;
import java.util.ArrayList;


public interface DataAccessLayer {
    
    public ArrayList<Tour> getTourRecord(int i);
    public void updateDataBase(int TaxiNum,Tour tour); 
}
