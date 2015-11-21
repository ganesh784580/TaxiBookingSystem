package RefactoredTBS.taxibooking;

import RefactoredTBS.TBSInterface.BookingRecordHistory;
import RefactoredTBS.TBSInterface.DataAccessLayer;
import RefactoredTBS.TBSInterface.UpdateBookingRecord;
import java.util.ArrayList;

public class TaxiBookingRecordManager implements UpdateBookingRecord,BookingRecordHistory {

    private final DataAccessLayer DataBase;
    private final int TaxiCount;

    public TaxiBookingRecordManager(DataAccessLayer DataBase, int TaxiCount) {
        this.DataBase = DataBase;
        this.TaxiCount = TaxiCount;
    }

    @Override
    public void showRecord() {
        ArrayList<Tour> tours;
        Tour tour;
        System.out.println("From Station       To Station       Time       TaxiNumber");
        for (int i = 0; i < TaxiCount; ++i) {
            tours = DataBase.getTourRecord(i);
            if (tours != null) {
                for (int j = 0; j < tours.size(); ++j) {
                    tour = tours.get(j);
                    System.out.println(tour.getIntialStaion() + "                  " + tour.getFinalStaion() + "                "
                            + tour.getStartingTime() + "         " + i);
                }
            } else {
                System.out.println(" No record Found ");
            }

        }

    }

    @Override
    public void updateDataBase(int taxiNum, Tour tour) {
        DataBase.updateDataBase(taxiNum, tour);
    }

}
