package OrderApp;

import javax.swing.*;

public class MainOrderApp {

    public static void main(String[] args) {
        try {
            OrderAppGUI app = new OrderAppGUI();
            app.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erreur d'instantiation de L'application principale : " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}