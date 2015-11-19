/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rss;

import extempfile.FileReaderWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import net.Download;

/**
 *
 * @author Brad
 */
public class FeedItem extends FileReaderWriter {

    private String title;
    private String description;
    private URL link;
    String feedTitle;
    File filepath;

    public FeedItem(String t, URL l, String d, String ft, File fp) {
        title = t;
        description = d;
        link = l;
        feedTitle = ft;
        filepath = fp;
    }

    public long download() throws FileNotFoundException, IOException, Exception, Exception {

        String target;

        long bytes;
        
        

        
        try {
            
            bytes = new Download(getLink()).downloadFile(filepath, makeWinCompatible(title + " - " + description));
        } catch (IOException e) {
            bytes = new Download(getLink()).downloadFile(filepath, makeWinCompatible(title));
        }


        return bytes;




    }
    public long downloadAsPDF() throws Exception {

        File target;

        long bytes;
        
        

        
        try {
            target = new File(filepath, makeWinCompatible(title + " - " + description) + ".pdf");
            bytes = new Download(getLink()).downloadFileAsPDF(target);
        } catch (IOException e) {
            target = new File(filepath, makeWinCompatible(title) + ".pdf");
            bytes = new Download(getLink()).downloadFileAsPDF(target);
        }


        return bytes;




    }
 
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the link
     */
    public URL getLink() {
        return link;
    }

    private File createPath(String saveTo) {

        String[] folders = saveTo.split(System.getProperty("file.separator"));
        File path = null;
        for (int i = 1; i < folders.length; i++) {
            path = new File(folders[i - 1], folders[i]);
        }
        return path;
    }

    /**
     * @param link the link to set
     */
    public void setLink(URL link) {
        this.link = link;
    }
}
