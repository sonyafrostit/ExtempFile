/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extempfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 *
 * @author Brad
 */
public class FileReaderWriter {

    public static String readFileAsString(String filePath) throws FileNotFoundException, IOException {
        return readFileAsString(new File(filePath));
    }

    public static String readFileAsString(File file) throws FileNotFoundException, IOException {
        StringBuilder fileData = new StringBuilder(1000);


        BufferedReader reader = new BufferedReader(
                new FileReader(file));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();

        return fileData.toString();
    }

    public String makeWinCompatible(String filename) {
        if (filename.contains(":")) {
            filename = filename.replace(':', ' ');
        }
        if (filename.contains("/")) {
            filename = filename.replace('/', ' ');
        }
        if (filename.contains("?")) {
            filename = filename.replace('?', ' ');
        }
        if (filename.contains("<")) {
            filename = filename.replace('<', ' ');
        }
        if (filename.contains(">")) {
            filename = filename.replace('>', ' ');
        }
        if (filename.contains("*")) {
            filename = filename.replace('*', ' ');
        }
        if (filename.contains("|")) {
            filename = filename.replace('|', ' ');
        }
        if (filename.contains("\"")) {
            filename = filename.replace('"', ' ');
        }
        if (filename.contains("^")) {
            filename = filename.replace('^', ' ');
        }
        if (filename.contains("&")) {
            filename = filename.replace('&', ' ');
        }
        if (filename.length() > 150) {

            filename = filename.substring(0, 150);

        }
        return filename;
    }

    public int saveFile(InputStream input, String fileName) throws IOException {
        return saveFile(input, fileName);

    }

    public int saveFile(InputStream input, File file) throws IOException {
        FileOutputStream out = new FileOutputStream(file);
        int nextChar;
        int bytes = 0;
        while ((nextChar = input.read()) != -1) {
            out.write((char) nextChar);
            bytes++;
        }
        out.write('\n');
        out.flush();
        input.close();
        out.close();
        return bytes;
    }

    public void saveTextFile(String input, String fileName) throws IOException {
        saveTextFile(input, new File(fileName));
    }

    public void saveTextFile(String input, File fileName) throws IOException {
        FileWriter outFile = new FileWriter(fileName);
        PrintWriter out = new PrintWriter(outFile);
        int nextChar;
        int bytes = 0;
        out.print(input);
        out.close();
    }
}
