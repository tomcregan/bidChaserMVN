package bidchaserlogiclayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author tommy
 */
public class AuctionTimer {
    
    private static long runningTime = 0;
    
    /**
     * Calculate the total running time for the auction, using the start and
     * end times provided by the member. The times are passed in as Strings
     * and converted to Date objects using the java.text.SimpleDateFormat
     * Class. The times are then extracted from the date object and the
     * total running time is calculated.
     * <p>
     * @param startTimeString the String representation of the user defined
     *                        start time.
     * @param endTimeString   the String representation of the user defined
     *                        end time.
     * <p>
     * @return the total running time of the auction in seconds.
     * <p>
     * @throws ParseException
     * @see SimpleDateFormat, Date
     */
    public static long calcRunningTime(String startTimeString, String endTimeString) throws ParseException {
        SimpleDateFormat sdf       = new SimpleDateFormat("hh:mm");
        Date             startTime = sdf.parse(startTimeString);
        Date             endTime   = sdf.parse(endTimeString);
        long             diffMs    = endTime.getTime() - startTime.getTime();
        long             diffSecs  = diffMs / 1000;

        return diffSecs;
    }

    /**
     * Start the auction running, once the total running time has been
     * calculated.  The countdown timer is created using the
     * java.util.Timer Class, which will create its own thread on which the
     * timer is run.
     * <p>
     *  @param startTime    the String representation of the user defined
     *                      start time.
     * @param endTime               the String representation of the user defined
     *                      end time.
     *
     * @see java.util.Timer, java.util.TimerTask
     */
    public void startTimer(String startTime, String endTime) {
        try {
            runningTime = calcRunningTime(startTime, endTime);
            System.out.println("Total Running Time is: " + runningTime + " seconds.");
        } catch (ParseException pe) {
            System.err.print("ParseException:" + pe.getMessage());
            System.exit(1);
        }

        final Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(runningTime--);

                if (runningTime < 0) {
                    System.out.println("BidChaser Auction is closed!!!");
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }
    
}
