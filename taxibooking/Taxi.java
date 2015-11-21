package RefactoredTBS.taxibooking;

import RefactoredTBS.TBSInterface.AvailableCabesInfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Taxi implements Comparable<Taxi>, AvailableCabesInfo {

    private final int TaxiNum;
    private final String CurrentStaion;
    private static int TaxiCount;
    private final LinkedList<Tour> tours;
    private double Earning;
    private final SlotsInfo SlotsInf;
    {
        SlotsInf=new slots();
    }

    public int getTaxiNum() {
        return TaxiNum;
    }

    public double getEarning() {
        return Earning;
    }

    public void setEarning(double Earning) {
        this.Earning = this.Earning + Earning;
    }

    public Taxi() {
        TaxiNum = TaxiCount++;
        CurrentStaion = "A";
        tours = new LinkedList<>();
    }

    @Override
    public Taxi bookFreeSlot(Tour Newtour, String StartingTime) {
        if (SlotsInf.isFreeSlotAvailable(Newtour, StartingTime)) {
            return this;
        }
        return null;
    }

    @Override
    public int compareTo(Taxi o) {
        return (int) (this.Earning - o.Earning);
    }

    public void addTour(Tour Newtour) {
        int Nodescount = 0;
        boolean Added = false;

        if (tours.isEmpty()) {
            tours.add(Newtour);
            return;
        }

        Tour OldTour = tours.get(Nodescount);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        try {
            Date NewTourReachingTime = sdf.parse(Newtour.getReachingTime());
            Date NewTourStartingTime = sdf.parse(Newtour.getStartingTime());

            Date PrevOldTourStartingTime = sdf.parse(OldTour.getStartingTime());
            Date PrevOldTourReachingTime = sdf.parse(OldTour.getReachingTime());

            if (NewTourReachingTime.compareTo(PrevOldTourStartingTime) != 1) {
                tours.addFirst(Newtour);
                Added = true;
            }
            if (!Added) {
                for (Nodescount = 1; Nodescount < tours.size(); ++Nodescount) {

                    Tour CurrentOldTour = tours.get(Nodescount);

                    Date CurrentOldTourStartingTime = sdf.parse(CurrentOldTour.getStartingTime());

                    if (NewTourStartingTime.compareTo(PrevOldTourReachingTime) != -1
                            && CurrentOldTourStartingTime.compareTo(NewTourReachingTime) != -1) {
                        tours.add(Nodescount, Newtour);
                        Added = true;
                        break;
                    }
                    PrevOldTourReachingTime = sdf.parse(CurrentOldTour.getReachingTime());
                }
            }
            if (!Added) {
                tours.addLast(Newtour);
            }
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private interface SlotsInfo {

        boolean isFreeSlotAvailable(Tour Newtour, String StartingTime);
    }

    private class slots implements SlotsInfo {

        @Override
        public boolean isFreeSlotAvailable(Tour Newtour, String StartingTime) {
            int Nodescount = 0;
            Date date;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                //System.out.println("coming here" + tours.size());
                if (tours.isEmpty()) {
                    return true;
                } else {
                    Tour OldTour = tours.get(Nodescount);

                    Date NewTourReachingTime = sdf.parse(Newtour.getReachingTime());
                    Date NewTourStartingTime = sdf.parse(Newtour.getStartingTime());

                    Date PrevOldTourStartingTime = sdf.parse(OldTour.getStartingTime());
                    Date PrevOldTourReachingTime = sdf.parse(OldTour.getReachingTime());

                    if (NewTourReachingTime.compareTo(PrevOldTourStartingTime) != 1) {
                        return true;
                    }

                    for (Nodescount = 1; Nodescount < tours.size(); ++Nodescount) {
                        Tour CurrentOldTour = tours.get(Nodescount);
                        Date CurrentOldTourStartingTime = sdf.parse(CurrentOldTour.getStartingTime());
                        if (NewTourStartingTime.compareTo(PrevOldTourReachingTime) != -1
                                && CurrentOldTourStartingTime.compareTo(NewTourReachingTime) != -1) {
                            return true;
                        }
                        PrevOldTourReachingTime = sdf.parse(CurrentOldTour.getReachingTime());
                    }

                    OldTour = tours.getLast();
                    PrevOldTourReachingTime = sdf.parse(OldTour.getReachingTime());
                    if (NewTourStartingTime.compareTo(PrevOldTourReachingTime) != -1) {
                        return true;
                    }
                }
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
            return false;
        }
    }
}
