
package Database;

import java.util.ArrayList;
import taxibookingsystem.Tour;

public class BookingHistory {
    BTree<Integer, ArrayList<Tour>> HistoryRecord;

    public BookingHistory() {
        HistoryRecord=new BTree<>(); 
    }
    
    public void updateDataBase(int TaxiNum,Tour tour){
      ArrayList<Tour> tours;
        if(HistoryRecord.get(TaxiNum)==null){
          tours=new ArrayList<>();
          tours.add(tour);
          HistoryRecord.put(TaxiNum, tours); 
      }
        else{
            tours=HistoryRecord.get(TaxiNum);
            tours.add(tour);
        }
          
    }
    
    public ArrayList<Tour> showTourRecord(int TaxiNum){
        return HistoryRecord.get(TaxiNum); 
    }
}
