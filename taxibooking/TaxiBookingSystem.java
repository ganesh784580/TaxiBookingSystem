package RefactoredTBS.taxibooking;

import RefactoredTBS.Database.BookingHistory;
import RefactoredTBS.TBSInterface.BokingEventOccured;
import RefactoredTBS.TBSInterface.BookingRecordHistory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaxiBookingSystem {

    private final BokingEventOccured bookinghelper;
    private Tour tour;
    private final BookingRecordHistory BookingHistory;
    private final Station stations[];
    private static final int NumofStation = 5;

    public TaxiBookingSystem(int TaxiCount) {
        BookingHistory = new TaxiBookingRecordManager(new BookingHistory(), TaxiCount);
        stations = new Station[NumofStation];
        for (int i = 0; i < NumofStation; ++i) {
            stations[i] = new Station();
        }
        initiation(TaxiCount);
        bookinghelper = new BookingHelper(TaxiCount, (TaxiBookingRecordManager) BookingHistory, stations);
    }

    private void initiation(int TaxiCount) {
        for (int i = 0; i < TaxiCount; ++i) {
            stations[0].addCabe(new Taxi());
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the Number of Taxies, Number should be greater than 0 and less than 99");
        int TaxiCount;
        String Temp = br.readLine();
        Pattern CountPattern = Pattern.compile("^(0?[1-9]|[1-9][0-9])$");
        Matcher match = CountPattern.matcher(Temp);

        while (!match.matches()) {
            System.out.println("Please enter number in the given range,Number should be greater than 0 and less than 99");
            Temp = br.readLine();
            match = CountPattern.matcher(Temp);
        }

        TaxiBookingSystem TBS = new TaxiBookingSystem(Integer.parseInt(Temp));

        boolean Exit = false;

        int Choice;
        while (!Exit) {

            System.out.println("Please select any option \n "
                    + "1.) Press one for Booking Taxi \n "
                    + "2.) Press two for boking history \n "
                    + "3.) Press three for Exit \n"
            );

            Choice = Integer.parseInt(br.readLine());

            switch (Choice) {
                case 1:
                    TBS.tour = new Tour();
                    TBS.tour.createTour(br);
                    Taxi taxi = TBS.bookinghelper.isTaxiAvailable(TBS.tour);
                    if (taxi != null) {
                        System.out.println("Your Departing time is : " + TBS.tour.getStartingTime()
                                + "\nand Your Reaching time is " + TBS.tour.getReachingTime()
                                + "\nThe cost for the whole journy is : " + TBS.tour.getCost()
                                + "\n Your Taxi Number is " + taxi.getTaxiNum()
                                + "\nand Taxi earning is " + taxi.getEarning() + "\n\n\n\n\n\n");
                    } else {
                        System.out.println("\nSorry No taxi is available right now Try again Later" + "\n\n\n\n\n\n");
                    }
                    Thread.sleep(2000);

                    //TBS.bookinghelper.isAvailable(TmpVar_IniStation,TmpVar_FinalStation,TmpVAr_DepartingTime);
                    break;

                case 2:
                    System.out.println("Press two for boking history");
                    TBS.BookingHistory.showRecord();
                    break;

                case 3:
                    System.out.println("Thanks for Using Taxi Booking System");
                    Thread.sleep(1000);
                    Exit = true;
                    break;
                case 4:
                // TBS.bookinghelper.tourRecord();

                default:
                    System.out.println("Please Select a Valid option");
                    Thread.sleep(1000);
                    System.out.println("\n\n\n\n\n\n\n\n\n");
            }

        }

    }

}
