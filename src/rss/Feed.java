/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rss;

import extempfile.FileReaderWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import net.Download;

/**
 *
 * @author Brad
 */
public class Feed extends FileReaderWriter {

    private String title;
    private String description;
    private URL link;
    private String fullText;
    private ArrayList<FeedItem> feedItemList;
    File feedFolder;

    public int getNumberOfItems() {
        return feedItemList.size();
    }

    public Feed(URL l, String saveTo) throws IOException { //Download all the things!
        link = l;
        pull();
        title = parseFor(fullText, "title");
        description = parseFor(fullText, "description");
        feedFolder = new File(saveTo + System.getProperty("file.separator") + makeWinCompatible(title));
        feedFolder.mkdir();
    }

    private String cleanXml(String dirtyString) {
        String cleanString;
        if (dirtyString.contains("<![CDATA[")) {
            cleanString = dirtyString.substring((dirtyString.indexOf("<![CDATA[") + 9), dirtyString.indexOf("]]>"));
        } else {
            cleanString = dirtyString;
        }

        return cleanString;
    }



    public final void createFeedItemList() throws IOException {

        if (getFullText() == null) {
            pull();
        }
        String[] itemTexts = getFullText().split("<item");
        feedItemList = new ArrayList<FeedItem>();
        for (int i = 1; i < itemTexts.length; i++) {
            if (itemTexts[i].indexOf("</item>") < 0) {
                continue;
            }
            String itemTitle;
            URL itemLink = null;
            String itemDescription;
            itemTitle = parseFor(itemTexts[i], "title");
            itemLink = new URL(parseFor(itemTexts[i], "link"));
            itemDescription = parseFor(itemTexts[i], "description");
            FeedItem item = new FeedItem(itemTitle, itemLink, itemDescription, getTitle(), feedFolder);

            feedItemList.add(item);

        }

    }

    final String parseFor(String s, String element) {
        String startElement = "<" + element + ">";
        String endElement = "</" + element + ">";
        String parsedString;

        if (s.indexOf(endElement) > 0) {

            parsedString = cleanXml(s.substring((s.indexOf(startElement) + startElement.length()), s.indexOf(endElement)));

        } else if (s.indexOf(startElement) > 0) {
            parsedString = cleanXml(s.substring(s.indexOf(startElement) + startElement.length()));
        } else {
            parsedString = "Error parsing, element \"" + element + "\" could not be found";
        }

        return parsedString;

    }

    private void pull() throws IOException {
        fullText = new Download(getLink()).downloadString();
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

    /**
     * @return the fullText
     */
    public String getFullText() {
        return fullText;
    }

    /**
     * @return the feedItemList
     */
    public ArrayList<FeedItem> getFeedItemList() {
        return feedItemList;
    }
}
