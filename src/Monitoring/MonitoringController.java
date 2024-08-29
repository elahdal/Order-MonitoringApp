package Monitoring;

import Data.Order;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Cette classe represente le controller du design pattern MVC elle gère la reception de l'objet OrderApp.Data.Order via socket tcp
 * et le stocke ainsi dans une arraylist rassemblant tous les orders.
 *
 */
public class MonitoringController {

    /**
     * une liste contenant touts les orders d'une session
     */
    private ArrayList<Order> orders = new ArrayList<>();

    /**
     * on mets en attribut l'application monitoring
     */
    private MonitoringAppGui app;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Dans le constructeur on oublie pas de lui retourner l'instance unique de notre Jtable au moment de sa création
     * pour garantir ainsi que notre controleur a une seule et unique instance de notre monitoring app.
     * @param app instance de notre application Monitoring
     */
    public MonitoringController(MonitoringAppGui app) {
        this.app = app;
    }


    /**
     * Cette methode gère l'allumage du serveur et la réception de l'object OrderApp.Data.Order
     */
    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server ON");

            //Cette boucle sert a laisser le serveur toujours en ligne jusqu'a nouvel ordre.
            while (true) {

                //on verifie si la connexion a bien été établie avec le client
                try (Socket clientSocket = serverSocket.accept()) {

                    //Si c'est le cas on ouvre un flux de communication qui recoit l'objet OrderApp.Data.Order
                    try (ObjectInputStream receiveOrder = new ObjectInputStream(clientSocket.getInputStream())) {

                        // On lit l'Objet depuis le controlleur de l application OrderApp.Data.Order
                        Order order = (Order) receiveOrder.readObject();

                        //On ajoute cet objet a notre table
                        app.addData(order);

                    //On ajoute des exception pour gérer le cas ou y aura un problème dans la reception de l'objet
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,
                                "OrderApp.Data.Order Not Received: " + e.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                //De meme ici en gérant le cas ou il y a un problème de connection entre client/serveur
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,
                            "Connection Not Completed : " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        // et finalement on gère le cas de problème dans le socket.
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Socket problem : error happend: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
