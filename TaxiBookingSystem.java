
package taxibookingsystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TaxiBookingSystem {

    private BookingHelper bookinghelper;
    private Tour tour;
    
    public TaxiBookingSystem(int TaxiCount) {
        bookinghelper=new BookingHelper(TaxiCount);
    }

    
    public void bookTaxi(){
    
}
    public void bookingHistory(){
        
    }
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the Number of Taxies");
        int TaxiCount=Integer.parseInt(br.readLine());
        
        TaxiBookingSystem TBS=new TaxiBookingSystem(TaxiCount);
        
        boolean Exit=false;
        
        String TmpVar_IniStation,TmpVar_FinalStation;
        int Choice;
        float TmpVAr_DepartingTime;
        while(!Exit){
        
        System.out.println("Please select any option \n "
                +          "1.) Press one for Booking Taxi \n "
                +          "2.) Press two for boking history \n " 
                +          "3.) Press three for Exit \n");
        
        Choice=Integer.parseInt(br.readLine());
        
        
        switch(Choice){
            case 1:
            TBS.tour=new Tour();
            TBS.tour.createTour(br);
            Taxi taxi=TBS.bookinghelper.isAvailable(TBS.tour);
            System.out.println("Your Departing time is : " + TBS.tour.getStartingTime()+TBS.tour.getDepartNoon()+
                               "\nand Your Reaching time is " +TBS.tour.getReachingTime()+TBS.tour.getReachingNoon()+
                               "\nThe cost for the whole journy is : "+TBS.tour.getCost());
            if(taxi!=null)
                System.out.println("\n Your Taxi Number is " +taxi.getTaxiNum() +
                        "\nand Taxi earning is " + taxi.getEarning()+"\n\n\n\n\n\n");
            else
                System.out.println("\nSorry No taxi is available right now Try again Later" +"\n\n\n\n\n\n");
            Thread.sleep(2000); 
            
            //TBS.bookinghelper.isAvailable(TmpVar_IniStation,TmpVar_FinalStation,TmpVAr_DepartingTime);
            break;    
                
            case 2:
            System.out.println("Press two for boking history");
            TBS.bookinghelper.showRecord();
            break;
                
            case 3:
            System.out.println("Thanks for Using Taxi Booking System");
            Thread.sleep(1000);
            Exit=true;
            break;
                
            default:
            System.out.println("Please Select a Valid option");
            Thread.sleep(1000);
            System.out.println("\n\n\n\n\n\n\n\n\n");   
        }
            
        }
    
    }
    
}
