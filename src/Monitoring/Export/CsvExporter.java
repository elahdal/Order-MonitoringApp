package Monitoring.Export;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.PrintWriter;

/**
 * La Classe ExportCsv est une classe qui spécialise la classe mere "Monitoring.Export.ExportToFile" et gere essetiellement la transformation des données depuis notre application monitoring
 * et les transformes en fichier Csv en donnant a l'utilisateur le choix où enregistrer le fichier.
 */
public class CsvExporter extends ExportToFile {
    /**
     * Cette methode Ouvre la boite de dialogue avec l'utilisateur qui va lui permettre de choisir l'emplacement où stocker ces données.
     * @return rien
     */
    @Override
    protected JFileChooser DialogBoxFileChooser() {
        return new JFileChooser();
    }

    /**
     * Enregistre les données de l'aplication monitoring dans un fichier csv ce dernier sera enregistré à l'emplacement voulu à l'aide d'un dialogue de sélection de fichier
     * @param Path L'emplacement ou on veut enregistrer notre fichier
     * @param table notre table ou on va lire les données
     */
    @Override
    protected void saveToFile(String Path, DefaultTableModel table) {

        // la classe printwritter va nous permettre d'ecrire dans le fichier csv a l'aide du chemin qu'on a passé en parametre.
        try (PrintWriter pw = new PrintWriter(Path + ".csv")) {
            // on parcours les colognes en recuperant leurs noms et par la suite en les ecrit dans la premiere cologne du fichier csv en les delimitant uniquementpar des ;
            for (int i = 0; i < table.getColumnCount(); i++) {
                pw.print(table.getColumnName(i));

                //on delimite le nom des colognes a l'aide de ","
                if (i < table.getColumnCount() - 1) {
                    pw.print(",");
                }
            }
            pw.println();

            //on passe maintenant au donnes du tableau
            //on parcours les lignes et les colognes de facon a recuperer les donnes de chaque ligne separement
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    pw.print(table.getValueAt(i, j));

                    //Puis on separe les contenues de cette ligne a l'aide des ","
                    if (j < table.getColumnCount() - 1) {
                        pw.print(",");
                    }
                }
                pw.println();
            }

        //On gére le cas de l'exception où il y'a eu un probléme dans l'ecriture du fichier
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erreur de l'écriture du fichier : " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
