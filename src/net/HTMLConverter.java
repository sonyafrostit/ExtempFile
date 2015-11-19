/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author brad
 */
public class HTMLConverter {

    private URL url;

    public HTMLConverter(URL url) {
        this.url = url;
    }

    public void writeAsPdf(File outputFile) throws Exception {
        String line;
        /*
        String[] spacedout = outputFile.toString().split(" ");
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < spacedout.length - 1; i++) {
            sb.append(spacedout[i]);
            sb.append("\\ ");
        }
        sb.append(spacedout[spacedout.length - 1]);
        String finalthing = sb.toString();
        *
        System.out.println(finalthing);*/
        String[] cmdArray = {"wkhtmltopdf", url.toString(), outputFile.toString()};
        Process p = Runtime.getRuntime().exec(cmdArray);
        BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        while ((line = bri.readLine()) != null) {
            System.out.println(line);
        }
        bri.close();
        while ((line = bre.readLine()) != null) {
            System.out.println(line);
        }
        bre.close();
        p.waitFor();
        System.out.println("Done.");
        /*convertToXHTML(new File("temp.html"));
         OutputStream os;
         os = new FileOutputStream(outputFile);
         ITextRenderer renderer = new ITextRenderer();
         renderer.setDocument("temp.html");
         renderer.layout();
         renderer.createPDF(os);
         os.close();*/
    }

    void convertToXHTML(File vanillaHTML) throws Exception {

        FileInputStream fis = new FileInputStream(vanillaHTML);
        FileOutputStream fos = new FileOutputStream(new File("tempx.html"));
        Tidy T = new Tidy();
        Document D = T.parseDOM(fis, fos);
        fis.close();
        fos.close();
    }
}
