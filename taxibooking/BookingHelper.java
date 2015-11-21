package RefactoredTBS.taxibooking;

import RefactoredTBS.TBSInterface.AvailableCabesatStationInfo;
import RefactoredTBS.TBSInterface.BokingEventOccured;
import RefactoredTBS.TBSInterface.UpdateBookingRecord;

public class BookingHelper implements BokingEventOccured {

    private final int NumofStation = 5;
    private final int TaxiCount;
    private final AvailableCabesatStationInfo CabesatStation[];
    private final UpdateBookingRecord UpdateRecord;

    public BookingHelper(int TaxiCount, UpdateBookingRecord UpdateRecord, AvailableCabesatStationInfo[] CabesInfo) {

        CabesatStation = CabesInfo;
        this.TaxiCount = TaxiCount;
        this.UpdateRecord = UpdateRecord;
    }

    @Override
    public Taxi isTaxiAvailable(Tour tour) {
        Taxi taxi = null;
        int CurentStationNumber, StaionChecked = 0, RghtCounter = 1, LeftCounter = 1;
        CurentStationNumber = tour.getIntialStaion().charAt(0) - 'A';
        taxi = CabesatStation[CurentStationNumber].isAvailable(tour, tour.getStartingTime());
        ++StaionChecked;
        while (taxi == null && StaionChecked < 5) {
            if ((CurentStationNumber - LeftCounter) >= 0) {
                taxi = CabesatStation[CurentStationNumber - LeftCounter].isAvailable(tour, tour.getReachingTime());
                ++LeftCounter;
                ++StaionChecked;
            }
            if ((CurentStationNumber + RghtCounter) < 5 && taxi == null) {
                taxi = CabesatStation[CurentStationNumber + RghtCounter].isAvailable(tour, tour.getReachingTime());
                ++RghtCounter;
                ++StaionChecked;
            }
        }
        if (taxi != null) {
            taxi.setEarning(tour.getCost());
            CabesatStation[tour.getFinalStaion().charAt(0) - 'A'].addCabe(taxi);
            UpdateRecord.updateDataBase(taxi.getTaxiNum(), tour);
        }

        return taxi;
    }

}
