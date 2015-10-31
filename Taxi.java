
package taxibookingsystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Taxi implements Comparable<Taxi>{ 
    private int TaxiNum;
    private String CurrentStaion;
    private static int TaxiCount;
    private ArrayList<Tour> tours;
    private double Earning;

    public int getTaxiNum() {
        return TaxiNum;
    }

    public double getEarning() {
        return Earning;
    }

    public void setEarning(double Earning) {
        this.Earning =this.Earning+ Earning;
    }
    
    public Taxi() {
        TaxiNum=TaxiCount++;
        CurrentStaion="A";
        tours=new ArrayList<Tour>();
    }
    public Taxi bookFreeSlot(Tour Newtour,String StartingTime){
        int Count=0;
        Date date;
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        try {
              date=sdf.parse(StartingTime);
             //System.out.println("coming here" + tours.size());
             if(tours.size()==0)
                 return this;
             else
        while(Count<tours.size()){
            Tour Tmp_Tour=tours.get(Count++);
            if(date.compareTo(sdf.parse(Tmp_Tour.getReachingTime()))!=-1&&Newtour.getIntialStaion().equals(Tmp_Tour.getFinalStaion()))
                return this;    
            else if(sdf.parse(Newtour.getReachingTime()).compareTo(sdf.parse(Tmp_Tour.getStartingTime()))!=1&&Newtour.getIntialStaion().equals(Tmp_Tour.getIntialStaion()))
            return this;
          
            }
          
                
        }
        catch (ParseException ex) {
                Logger.getLogger(Taxi.class.getName()).log(Level.SEVERE, null, ex);
            }
    return null;
    }

    @Override
    public int compareTo(Taxi o) {
        System.out.println("from compare to this-->" + this.Earning+ " and o ---> " + o.Earning);
        if(this.Earning > o.Earning)
            return 1;
        else
            return -1;
    }

    public void addTour(Tour Newtour) {
    this.tours.add(Newtour);
    }
    
}
