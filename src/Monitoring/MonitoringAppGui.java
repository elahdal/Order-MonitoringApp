package Monitoring;

import Monitoring.Export.CsvExporter;
import Monitoring.Export.ExportToFile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Data.Order;


/**
 * la classe Monitoring.MonitoringAppGui est la partie View du design pattern MVC elle représente l'interface de de nottre applicaton monitoring qui contient une Jtable qui affiche les commandes.
 * j'ai essayé dans cette derniere d'avoir un design moderne en appliquant un theme noir a toute cette interface, ce theme peut etre changer en blanc facilement grace a une methode si on le souhaite.
 *
 */
public class MonitoringAppGui {

    private JPanel panel;
    private static JTable tableWithOrders;
    private JButton resetButton;
    private JScrollPane jScrollPane;
    private DefaultTableModel table;

    /**
     * Ce constructeur initialise l'interface graphique de l'application de monitoring.
     * Il configure le panneau principal, l'icône de l'application, initialise la table des commandes,
     * et ajoute un bouton et sa logique pour la réinitialisation.
     */
    public MonitoringAppGui() {

        //On initialise notre panel principal en lui appliquant le layout (Border)
        panel = new JPanel();
        panel.setLayout(new BorderLayout());


        //Par la suite on cree une Default Table ou on specifie directement nos titre de colognes pour gérer nos donnes dans la jtable
        table = new DefaultTableModel(
                new Object[]{"ID", "Product", "Unit price (€)", "Quantity", "Total price (€)", "Date"}, 0
        );

        //On cree notre jtable
        tableWithOrders = new JTable(table);

        //On ajoute un scrollpane
        jScrollPane = new JScrollPane(tableWithOrders);
        panel.add(jScrollPane, BorderLayout.CENTER);

        //L'ajout du boutton reset
        resetButton = new JButton("Reset table");
        panel.add(resetButton, BorderLayout.SOUTH);

        //On applique notre theme noir pour moderniser l'interface
        BlackDesign();

        resetButton.addActionListener(e -> {
            ExportToFile exporter = new CsvExporter();
            exporter.exportData(table);

        });
    }


    /**
     * Cette methode va transformer les couleurs de tout nos composant en noir ou gris.
     * Le fait d'avoir la méthode BlackDesign va nous permetre si on le souhaite d'ajouter par la suite une option a l'utilisateur pour qu'il choisit le theme qu il veut noir / blanc
     * ceci ce fait en appelant ou pas cette méthode.
     *
     */
    private void BlackDesign() {
        panel.setBackground(Color.BLACK);
        tableWithOrders.getTableHeader().setBackground(Color.DARK_GRAY);
        tableWithOrders.getTableHeader().setForeground(Color.WHITE);
        tableWithOrders.setBackground(Color.BLACK);
        tableWithOrders.setForeground(Color.WHITE);
        tableWithOrders.setGridColor(Color.GRAY);
        tableWithOrders.setSelectionBackground(Color.DARK_GRAY);
        tableWithOrders.setSelectionForeground(Color.WHITE);
        jScrollPane.getViewport().setBackground(Color.BLACK);
        resetButton.setBackground(Color.DARK_GRAY);
        resetButton.setForeground(Color.WHITE);
    }

    /**
     * Cette méthode ajoutes les composant de nos commandes manuellement au tableau monitoring
     * @param id
     * @param product
     * @param unitPrice
     * @param quantity
     * @param totalPrice
     * @param date
     */
    public void addData(int id, String product, double unitPrice, int quantity, double totalPrice, String date) {
        table.addRow(new Object[]{id, product, unitPrice, quantity, totalPrice, date});
    }

    /**
     * Cette methode ajoute directement les composant de notre commande a partir de l'objet "OrderApp.Data.Order" qu'on a passe en parametre
     * @param order
     */
    public void addData(Order order) {
        table.addRow(new Object[]{ order.getIdOrder(),order.getProductName(), order.getUnitPrice(), order.getQuantity(), order.getTotalPrice(), order.NormalDate()});
    }


    /**
     *Cette methode demarre note View en choisissant la taille de la fenetre et son emplacement
     */
    public void startViewMonitoring() {
        JFrame frame = new JFrame("Casse auto chez JP : monitoring app");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(this.panel);
        frame.pack();
        frame.setSize(600, 400);
        frame.setLocation(800,200);
        frame.setVisible(true);
    }
}
