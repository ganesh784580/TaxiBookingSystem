

package taxibookingsystem;

import java.io.BufferedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tour {

    
    private static int TourCount=0;
    private final int BookingId=TourCount++;
    private String IntialStaion; 
    private String FinalStaion;
    private Date DepartingTime;
    private Date ReachingTime;
    private double cost;
    private String DepartingNoon="";
    
   //////////////////////can be improved////////////
    void createTour(BufferedReader br) throws Exception{
            System.out.println("Enter your current station");
            IntialStaion=br.readLine();
            System.out.println("Enter your Final station");
            FinalStaion=br.readLine();
            System.out.println("Enter Your Departing Time");
            
            char DeprtTime[]=br.readLine().toCharArray();
            String Time="";
            
            for(int i=0;i<DeprtTime.length;++i){
                
                if(DeprtTime[i]==':'){
                    Time=Time+":";
                }
                else if(DeprtTime[i]>57)
                    DepartingNoon=DepartingNoon+DeprtTime[i];
                else
                    Time=Time+DeprtTime[i];
            }
            SimpleDateFormat parser=new SimpleDateFormat("HH:mm");
            DepartingTime=parser.parse(Time);
            //System.out.println("time is" +cal.getTime());
            
            ReachingTime=reachingTime(IntialStaion,FinalStaion);
            //ReachingTime
            cost=CostCalci.tourCost(IntialStaion,FinalStaion);
            
    }
    
     private Date reachingTime(String IntialStaion, String FinalStaion)  {
      float Temp_Var=(float).30*Math.abs(IntialStaion.charAt(0)-FinalStaion.charAt(0));
      float Count=0.0f;
      
      while(Temp_Var>=.60f){
          Temp_Var=(float)(Temp_Var-.60);
          ++Count;
      }
      int Temp=(int)(Temp_Var*100);
      SimpleDateFormat parser=new SimpleDateFormat("HH:mm");
      try {
            Date Tmp=parser.parse(parser.format(DepartingTime)); 
            Calendar cal=Calendar.getInstance();
            cal.setTime(Tmp); 
            cal.add(Calendar.HOUR, (int)Count);
            cal.add(Calendar.MINUTE, Temp);
            return cal.getTime();
        } catch (ParseException ex) {
            Logger.getLogger(Tour.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
            return null ;
        
        
    }

    double getCost() {
        return this.cost;
    }

   String getReachingTime(){
       SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
       Calendar cal=Calendar.getInstance();
       cal.setTime(ReachingTime);     
       return sdf.format(cal.getTime());
      
   }
   public String getDepartNoon(){
       return this.DepartingNoon;
   }
   
   String getReachingNoon(){
       String Tmp;
       SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
      
        try {
            if(ReachingTime.compareTo(sdf.parse("12:00"))==1){
                if(DepartingNoon.equals("am"))
                    Tmp="pm";
                else
                    Tmp="am";
                return  Tmp;
            }} catch (ParseException ex) {
            Logger.getLogger(Tour.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       return DepartingNoon;
   }
    String getStartingTime(){
       SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
       Calendar cal=Calendar.getInstance();
       cal.setTime(DepartingTime); 
       return sdf.format(cal.getTime());
   }

    public String getIntialStaion() {
        return IntialStaion;
    }

    public String getFinalStaion() {
        return FinalStaion;
    }
    public Taxi freeSlot(){
        
        return null;
    }
    
}
