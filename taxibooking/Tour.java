package RefactoredTBS.taxibooking;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tour {

    private static int TourCount = 0;
    private final int BookingId = TourCount++;
    private String IntialStaion;
    private String FinalStaion;
    private Date DepartingTime;
    private Date ReachingTime;
    private double cost;
    static private final SimpleDateFormat TimeFormat = new SimpleDateFormat("hh:mm a");

    //////////////////////can be improved////////////
    void createTour(BufferedReader br) throws Exception {
        System.out.println("Enter your current station should be among these a or A, b or B, c or C, d or D, e or E");
        IntialStaion = br.readLine();
        Pattern StationPattern = Pattern.compile("[a-eA-E]");
        Matcher match = StationPattern.matcher(IntialStaion);
        while (!match.matches()) {
            System.out.println("Please choose valid option avilable stations are a or A, b or B, c or C, d or D, e or E");
            IntialStaion = br.readLine();
            match = StationPattern.matcher(IntialStaion);
        }
        IntialStaion=IntialStaion.toUpperCase();
        
        System.out.println("Enter your Final station should be among these a or A, b or B, c or C, d or D, e or E");
        FinalStaion = br.readLine();
        match = StationPattern.matcher(FinalStaion);
        while (!match.matches()) {
            System.out.println("Please choose valid option avilable stations are a or A, b or B, c or C, d or D, e or E");
            FinalStaion = br.readLine();
            match = StationPattern.matcher(FinalStaion);
        }
      FinalStaion=FinalStaion.toUpperCase();
        System.out.println("Enter Your Departing Time in this format hh:mm am or pm ");
        String DeprtTime = br.readLine();
        Pattern TimePattern = Pattern.compile("[0-1][0-9]:[0-5][0-9] [aApP][mM]");
         match = TimePattern.matcher(DeprtTime);
        while (!match.matches()) {
            System.out.println("Please follow the format hh:mm am or pm example 12:00 am");
            DeprtTime = br.readLine();
            match = TimePattern.matcher(DeprtTime);
        }

        DepartingTime = TimeFormat.parse(DeprtTime);

        //System.out.println("time is" +cal.getTime());
        ReachingTime = reachingTime(IntialStaion, FinalStaion);
        //ReachingTime
        cost = CostCalci.tourCost(IntialStaion, FinalStaion);

    }

    private Date reachingTime(String IntialStaion, String FinalStaion) {
        float Temp_Var = (float) 00.30 * Math.abs(IntialStaion.charAt(0) - FinalStaion.charAt(0));
        float Count = 0.0f;

        while (Temp_Var >= 00.60f) {
            Temp_Var = (float) (Temp_Var - 00.60);
            ++Count;
        }
        int Temp = (int) (Temp_Var * 100);
        Calendar cal = Calendar.getInstance();
        cal.setTime(DepartingTime);
        cal.add(Calendar.HOUR, (int) Count);
        cal.add(Calendar.MINUTE, Temp);
        return cal.getTime();
    }

    double getCost() {
        return this.cost;
    }

    String getReachingTime() {
        return TimeFormat.format(ReachingTime);

    }

    String getStartingTime() {

        return TimeFormat.format(DepartingTime);
    }

    public String getIntialStaion() {
        return IntialStaion;
    }

    public String getFinalStaion() {
        return FinalStaion;
    }

    public Taxi freeSlot() {

        return null;
    }

}
