

package taxibookingsystem;

import Database.BookingHistory;
import java.util.ArrayList;

public class BookingHelper {

    private final int NumofStation=5;
    private int TaxiCount;
    private Station staions[];
    private BookingHistory HistoryRecord;
    
    public BookingHelper(int TaxiCount) {
        staions=new Station[NumofStation];
        for(int i=0;i<NumofStation;++i)
        staions[i]=new Station();
        HistoryRecord=new BookingHistory();
        this.initiation(TaxiCount);
        this.TaxiCount=TaxiCount;
    }
    
    private void initiation(int TaxiCount){
        for(int i=0;i<TaxiCount;++i){
         staions[0].addTaxi(new Taxi()); 
        }    
    } 

    Taxi isAvailable(Tour tour) {
       Taxi taxi=null;
       int tmp,count=1;
       
       tmp=tour.getIntialStaion().charAt(0)-'A';
       taxi=staions[tmp].isAvailable(tour,tour.getStartingTime());
       
       while(taxi==null&&count<5){
       if((tmp-count)>=0){
           taxi=staions[tmp-count].isAvailable(tour,tour.getReachingTime());
           ++count;
       }
       if((tmp+count)<5&&taxi==null){
           taxi=staions[tmp+count].isAvailable(tour,tour.getReachingTime());
        ++count;
       }
       }
       if(taxi!=null){
           taxi.setEarning(tour.getCost()); 
           staions[tour.getFinalStaion().charAt(0)-'A'].addTaxi(taxi); 
           HistoryRecord.updateDataBase(taxi.getTaxiNum(), tour); 
       }
       
    return taxi;
    }

    void showRecord() {
        ArrayList<Tour> tours;
        Tour tour;
        System.out.println("From Station       To Station       Time       TaxiNumber");
        for(int i=0;i<TaxiCount;++i){
            tours=HistoryRecord.showTourRecord(i);
            if(tours!=null){
                for(int j=0;j<tours.size();++j){
                    tour=tours.get(j);
                    System.out.println(tour.getIntialStaion()+"                  " +tour.getFinalStaion()+"                "+
                                       tour.getStartingTime()+tour.getDepartNoon()+"         "+i);
                }
            }
                
        }
        
   }

    
    
    
    
}
