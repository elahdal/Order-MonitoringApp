package OrderApp;

import Data.Order;
import javax.swing.*;
import java.io.*;
import java.net.*;

/**
 * Classe controller qui gère l'envoie de l'Objet OrderApp.Data.Order vers l'application Monitoring
 */
public class OrderAppController {

    /**
     *Cette méthode envoie l'Objet OrderApp.Data.Order à travers un flux de communication "sendPipe" a l'app Monitoring
     * elle gère un cas d'erreur de communication de socket
     * @param order la commande a envoyer a l'app Monitoring
     */
    public void sendOrder(Order order) {
        try (Socket clientSocket = new Socket("localhost", 12345);

             //Création du flux de communication
             ObjectOutputStream sendPipe = new ObjectOutputStream(clientSocket.getOutputStream())) {

            //Envoie l'Objet au serveur
            sendPipe.writeObject(order);

            //Une Verification pour voir  si les données sont envoyées
            sendPipe.flush();

        } catch (IOException e) {

            //Exception qui gère le cas d'une erreur de connection du socket
            JOptionPane.showMessageDialog(null,
                    "Erreur de connection Socket: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
