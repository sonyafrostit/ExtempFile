/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import extempfile.FileReaderWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Brad
 */
public class Download extends FileReaderWriter {

    private URL link;

    public Download(URL l) {
        link = l;

    }

    public Download(String l) throws MalformedURLException {
        this(new URL(l));
    }

    public String downloadString() throws IOException {


        URLConnection urlC = getLink().openConnection();
        InputStream is = getLink().openStream();
        String result = IOUtils.toString(is, "UTF-8");

        is.close();
        return result;
    }

    /**
     *
     * @param target
     * @return
     * @throws IOException
     */
    public long downloadFile(File path, String target) throws IOException {
        return downloadFile(new File(path, target + ".html"));
    }
    public long downloadFile(File target) throws IOException {
        if (target.exists()) {

            return 0;
        }
        URLConnection urlC;
        InputStream is;
        urlC = getLink().openConnection();
        is = getLink().openStream();
        return saveFile(is, target);
    }
    /**
     *
     * @param path
     * @param target
     * @return
     * @throws IOException
     * @throws Exception
     */
    public long downloadFileAsPDF(File path, String target) throws IOException, Exception {
        return downloadFileAsPDF(new File(path, target + ".pdf"));
    }
    public long downloadFileAsPDF(File target) throws IOException, Exception {
        System.out.println(link.toString());
        if (target.exists()) {

            return 0;
        }
        
        
        
        HTMLConverter converter = new HTMLConverter(link);
        converter.writeAsPdf(target);
        return target.length();
    }
    /**
     * @return the link
     */
    public URL getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(URL link) {
        this.link = link;
    }
}
