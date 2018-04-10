
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;

public class GetCurrentDateTime {
    
    public static void main(String[] args) {
        GetCurrTime();
        GetTimeElapsed(System.currentTimeMillis(), System.currentTimeMillis());
    }

    public static void GetCurrTime(){        
       DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
       //Get the current date time with Date()
       Date date = new Date();
       System.out.println("\n\nCurrent system time in 24 HRS (with ms): " + dateFormat.format(date) + "\n");
    }

    public static long GetTimeElapsed(long startTime, long currentTime ){        
        SimpleDateFormat dateFormat = new SimpleDateFormat("SSS");
        dateFormat.setTimeZone(SimpleTimeZone.getTimeZone("GMT"));
        long elapsed = currentTime - startTime;        
        return elapsed;
    }
    
}
