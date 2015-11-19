/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extempfile;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFrame;

/**
 *
 * @author Brad
 */
public class ExtempFile {

    /**
     * @param args the command line arguments
     */
    static JFrame mainWindow;

    public static void main(String[] args) {
    if (args.length < 1) {
                        startProgram();
    } else if (args[0].equals("-nowin")) {
        startProgramWindowless();
    } else {
        System.err.println("Invalid argument!");
    }


    }
 
    private static void startProgram() {
      
        mainWindow = new MainScreen();
        if (SystemTray.isSupported()) {

            SystemTray tray = SystemTray.getSystemTray();
            final TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("cornericon.gif"));
            final PopupMenu popUp;
            popUp = new PopupMenu();
            mainWindow.setVisible(true);
            mainWindow.setState(JFrame.NORMAL);

            MenuItem exitItem = new MenuItem("Exit");
            MenuItem showItem = new MenuItem("Show Window");
            exitItem.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    exitItemActionPerformed(evt);
                }
            });
            showItem.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showItemActionPerformed(evt);
                }
            });
            popUp.add(showItem);
            popUp.add(exitItem);

            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    trayIconActionPerformed(evt);
                }

                private void trayIconActionPerformed(ActionEvent evt) {
                    throw new UnsupportedOperationException("Not yet implemented");
                }
            });
            try {
                tray.add(trayIcon);
                trayIcon.setPopupMenu(popUp);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }
        }
    }
        private static void startProgramWindowless() {
        mainWindow = new MainScreen();
        if (SystemTray.isSupported()) {

            SystemTray tray = SystemTray.getSystemTray();
            final TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("cornericon.gif"));
            final PopupMenu popUp;
            popUp = new PopupMenu();


            MenuItem exitItem = new MenuItem("Exit");
            MenuItem showItem = new MenuItem("Show Window");
            exitItem.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    exitItemActionPerformed(evt);
                }
            });
            showItem.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showItemActionPerformed(evt);
                }
            });
            popUp.add(showItem);
            popUp.add(exitItem);

            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    trayIconActionPerformed(evt);
                }

                private void trayIconActionPerformed(ActionEvent evt) {
                    throw new UnsupportedOperationException("Not yet implemented");
                }
            });
            try {
                tray.add(trayIcon);
                trayIcon.setPopupMenu(popUp);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }
        }
    }

    private static void exitItemActionPerformed(ActionEvent evt) {
       
        System.exit(0);
    }

    private static void showItemActionPerformed(ActionEvent evt) {

        mainWindow.setVisible(true);
        mainWindow.setState(JFrame.NORMAL);
    }
}