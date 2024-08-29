package Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Cette classe représente une command effectué elle se compose de ces parametres suivant :
 * (nomProduit, counteur , idproduit, prixunitaire, quantité , date , prixtotal) cette classe est serializable pour nous permettre de la stocker ou de la trasmettre.
 * PS: j'ai ajouter la date et l'idproduit pour que si on veut rajouter une option par la suite de sélection par date ou par id
 * et ainsi faciliter la réutilisation du code sans modifications majeurs
 */
public class Order implements Serializable{

    private static int count;
    private String productName;
    private double unitPrice;
    private int quantity;
    private Date date;
    private int idOrder;
    private double totalPrice;

    /**
     * Ce Constructeur a chaque commande il va incrémenter le counteur et instancier une nouvelle date lié a la commande
     * @param productName
     * @param unitPrice
     * @param quantity
     */
    public Order(String productName, double unitPrice, int quantity) {
        Date dateNow = new Date();
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.date = dateNow ;
        this.idOrder = count++;
        this.totalPrice = calculateTotalPrice(unitPrice, quantity);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public static void setCount(int count) {
        Order.count = count;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * methode pour modifier le prix unitaire
     * cette méthode  calcule directement le prix total de la commande a partir du nouveau prix unitaire
     * cette dernière nous garantie que si on change le prix unitaire le prix total sera aussi change nous evitant les erreurs de calculs.
     * @param unitPrice nouveau prix
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        this.totalPrice = calculateTotalPrice(unitPrice, this.quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     *  methode pour modifier la quantité
     *  cette méthode  calcule directement le prix total de la commande a partir de la nouvelle quantité
     *  cette dernière nous garantie que si on change la  quantité, le prix total sera aussi change nous évitant les erreurs de calculs.
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = calculateTotalPrice(this.unitPrice, quantity);
    }

    public Date getDate() {
        return date;
    }

    /**
     * methode qui va nous permettre d'avoir la date sous cette forme "dd-MM-yyyy" plus compréhensible
     * @return une date sous cette forme jour-mois-année
     */
    public String NormalDate() {
        SimpleDateFormat S = new SimpleDateFormat("dd-MM-yyyy");
        return S.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * methode qui calcule le prix total
     * @param unitPrice le prix de la piece en question
     * @param quantity la quantité
     * @return le prix total de la commande
     */
    public double calculateTotalPrice(double unitPrice, int quantity) {
        return unitPrice * quantity;
    }


}
