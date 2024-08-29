package Monitoring;

import javax.swing.*;

public class MainMonitoringApp {
    public static void main(String[] args) {
        try {
            MonitoringAppGui appM = new MonitoringAppGui();
            appM.startViewMonitoring();
            try{
                MonitoringController server = new MonitoringController(appM);
                server.startServer();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erreur d'instantiation du Controlleur : " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
                "Erreur d'instantiation de L'application Monitoring : " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
