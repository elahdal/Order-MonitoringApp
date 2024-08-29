package Monitoring.Export;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;

/**
 * Cette classe est une classe abstraite qui implémente la logique du design pattern "Template Method",
 * ce qui va nous permettre de réutiliser notre code et aussi respecter les principes de "seule responsabilité" et "d'Open/Close" .
 * Les avantages de cette classe c'est que si on veut par la suite exporter les données sous PDF ou Word une grande partie de notre code va etre utilisé.
 */
public abstract class ExportToFile {

    /**
     * Notre fonction template qui va appeller nos fonction de composition et qui gére le fonctionnement de base de cette classe.
     * @param table la table dont on veut exraire les données .
     */
    public final void exportData(DefaultTableModel table) {
        // C'est la boite de dialogue que l'utilisateur va utiliser pour choisir ou placer son fichier.
        JFileChooser fileChooser = DialogBoxFileChooser();

        // on affiche cette boite en passant null en paramètre pour dire que la boite de dialogue n'est pas attaché a une fenetre specifique
        fileChooser.showSaveDialog(null);

        // cette ligne : fileChooser.getSelectedFile().getPath() nous retourne l'adresse de l'emplacement spécifié par l'utilisateur
        String filePath = fileChooser.getSelectedFile().getPath();

        //Appel de notre fonction spéciale pour chaque extention
        saveToFile(filePath, table);

        //On oublie pas de vider le tableau
        table.setRowCount(0);
    }

    /**
     * methode abstraite qu'on va redefinir dans la classe ExportCSV
     * @return rien
     */
    protected abstract JFileChooser DialogBoxFileChooser();

    /**
     * methode abstraite qu'on va redefinir dans la classe ExportCSV et qu'on peut redefinir dans de nouvelles classes.
     * @param filePath l'emplacement où enrigistrer le fichier
     * @param table table de données
     */
    protected abstract void saveToFile(String filePath, DefaultTableModel table);
}