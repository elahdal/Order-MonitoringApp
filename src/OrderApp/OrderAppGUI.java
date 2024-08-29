package OrderApp;

import Data.Order;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class OrderAppGUI extends JFrame {

    private final JLabel NameLabel;
    private final JTextField NameText;
    private final JLabel PriceLabel;
    private final JTextField PriceText;
    private final JLabel quantityLabel;
    private final JTextField quantity;
    private final JButton confirmButton;
    private final JButton cancelButton;
    private final JLabel ImagePlacement;
    private static boolean confirmButtonClicked = false;




    public OrderAppGUI() {
        setTitle("JP's JUNKYARD");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);


        // On charge et on redimensionne l'image en récuperant l'image brute pour la faire corespandre a notre interface utilisateur
        ImageIcon DefaultImage = new ImageIcon(OrderAppGUI.class.getResource("/images/LOGO_JD.png"));
        Image RawImage = DefaultImage.getImage();
        ImageIcon FinalImage = new ImageIcon(RawImage.getScaledInstance(426, 175, Image.SCALE_SMOOTH));

        //On charge l'icone de notre fenetre
        ImageIcon appIcon = new ImageIcon(OrderAppGUI.class.getResource("/images/A.png"));
        setIconImage(appIcon.getImage());

        //initialisation des labels et des texts
        NameLabel = new JLabel("Product Name:");
        NameText = new JTextField(15);
        PriceLabel = new JLabel("Unit Price (€) :");
        PriceText = new JTextField(15);
        quantityLabel = new JLabel("Quantity:");
        quantity = new JTextField(15);
        confirmButton = new JButton("Confirm ");
        cancelButton = new JButton("Cancel ");
        ImagePlacement = new JLabel(FinalImage);

        PriceText.setText("0");
        quantity.setText("1");


        // Réglage du positionnement des inputs et labels manuellements pour avoir une libérté totale dans le positionnement des composants
        ImagePlacement.setBounds(12, 12, 426, 175);

        NameLabel.setBounds(37, 210, 100, 25);
        NameText.setBounds(145, 210, 200, 25);

        PriceLabel.setBounds(37, 250, 100, 25);
        PriceText.setBounds(145, 250, 200, 25);

        quantityLabel.setBounds(37, 290, 100, 25);
        quantity.setBounds(145, 290, 200, 25);

        confirmButton.setBounds(47, 345, 150, 30);
        cancelButton.setBounds(215, 345, 150, 30);

        // On choisie une couleur qui facilite le choix pour l'utilisateur et modernise l'interface
        confirmButton.setBackground(new Color(189, 236, 182));
        cancelButton.setBackground(new Color(255, 99, 71));



        cancelButton.addActionListener(new ActionListener() {
            /**
             * ne fois qu'on clique sur le bouton Cancel la commande est ignoré et les parametre de cette commande sont remit a défaut.
             * @param CancelOrder the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent CancelOrder) {

                NameText.setText("");
                PriceText.setText("0");
                quantity.setText("1");
            }
        });


        confirmButton.addActionListener(new ActionListener() {
            /**
             * Une fois qu'on clique sur le bouton Confirmer:
             * Premièrement on récupère les inputs de l'utilisateur,
             * Ensuite On verifie a l'aide de plusieurs exeptions si les inputs de l'utilisateur sont correctes : (Nom :String ,Prix:double, quantité:int)
             * Dans le cas ou aucunne exception est levée on envoie l'Objet .
             * @param ConfirmButton the event to be processed
             */
            public void actionPerformed(ActionEvent ConfirmButton) {
                confirmButtonClicked=true;

                //Si une exception est levée notre boolean prend false et ainsi on peut pas envoyée l'Objet OrderApp.Data.Order
                boolean OrderCanBeSent = true;


                // Récuperer les inputs de l'utilisateur
                String productName = NameText.getText();
                String unitPriceText = PriceText.getText();
                String quantityText = quantity.getText();

                // On les convertit dans leurs propre type pour les stocker dans l'objet
                double unitPrice;
                int quantity;

                //On verifie si le non est pas vide
                if (productName.isEmpty()) {
                    OrderCanBeSent = false;
                    JOptionPane.showMessageDialog(null,
                            "Product name is Empty. Please enter a name.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                //Si le prix n'est pas un double ou un entier
                try {
                    unitPrice = Double.parseDouble(unitPriceText);
                } catch (Exception e) {
                    OrderCanBeSent = false;
                    JOptionPane.showMessageDialog(null,
                            "Your input is incorrect. Please enter a number." + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    unitPrice = 0; // Default value or handle error
                }

                //et si la quantité n'est pas un entier
                try {
                    quantity = Integer.parseInt(quantityText);
                } catch (Exception e) {
                    OrderCanBeSent = false;
                    JOptionPane.showMessageDialog(null,
                            "Your input is incorrect. Please enter a number." + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    quantity = 1; // Default value or handle error
                }

                if(OrderCanBeSent) {
                    //Creation de la date a l'instant de l'envoie de la commande
                    Date date = new Date();

                    //instentiation de l'objet
                    Order order = new Order(productName, unitPrice, quantity);

                    //Envoie de l'Objet
                    OrderAppController C = new OrderAppController();
                    C.sendOrder(order);
                }

            }
        });


        //Finalement on ajoute tous les composants a notre frame
        add(ImagePlacement);
        add(NameLabel);
        add(NameText);
        add(PriceLabel);
        add(PriceText);
        add(quantityLabel);
        add(quantity);
        add(confirmButton);
        add(cancelButton);
    }

    /**
     * ce boolean va nous permettre de savoir si on peut envoyer l'Objet ou pas
     * @return false or true selon le cas
     */
    public boolean isClicked() {
        return confirmButtonClicked;
    }

}
