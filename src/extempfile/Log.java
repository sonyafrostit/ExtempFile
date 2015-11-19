/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extempfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Brad
 */
public class Log extends FileReaderWriter {

    StringBuilder currentLog;
    long startTime;

    public Log() {
        startTime = System.currentTimeMillis();
        currentLog = new StringBuilder();
        Date now = new Date();
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        enterLog("ExtempFile Run: " + dfm.format(now));
    }

    final void enterLog(Object o) {

        currentLog.append(o);
        currentLog.append("\n\n");
    }

    void saveLog() throws FileNotFoundException, IOException  {
        File logFile = new File("log.txt");
        String post = readFileAsString(logFile) + currentLog;
        saveTextFile(post, logFile);
    }

    String close(int totalBytes, int feeds, int items, boolean interrupted, boolean saveToDisk) throws FileNotFoundException, IOException {
        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime);
        String summary;
        double averageSpeed = totalBytes / totalTime / .128;
        double totalDownloadMB = (totalBytes / 1000000);
        if(interrupted) {
        summary = "Task Interrupted! \n"
                + "Number of Feeds: " + feeds + "\n"
                + "Number of Items: " + items + "\n"
                + "Total Download Size: " + totalDownloadMB + "mb\n"
                + "Average Download Speed: " + averageSpeed + "kbps \n"
                + "Time: " + (totalTime / 60000) + " minutes, " + (totalTime % 60000) / 1000 + " seconds";
        } else {
                summary = "Task Completed! \n"
                + "Number of Feeds: " + feeds + "\n"
                + "Number of Items: " + items + "\n"
                + "Total Download Size: " + (totalBytes / 1000000) + "mb) \n"
                + "Average Download Speed: " + averageSpeed + "kbps \n"
                + "Time: " + (totalTime / 60000) + " minutes, " + (totalTime % 60000) / 1000 + " seconds";
        }
        enterLog(summary);
        Date now = new Date();
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        enterLog("ExtempFile Finished Run: " + dfm.format(now));
        if (saveToDisk){
            saveLog();
        }
        return summary;
    }
}
