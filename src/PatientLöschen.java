
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Die Klasse PatientLöschen bietet Funktionalität zum Löschen von Patientendaten aus einer MySQL-Datenbank.
 * <p>
 * Die Klasse enthält Methoden zur Anzeige einer grafischen Benutzeroberfläche (GUI),
 * mit der der Benutzer die ID eines Patienten eingeben kann, sowie eine Methode zum
 * tatsächlichen Löschen des Datensatzes in der Datenbank.
 */
public class PatientLöschen {

        // JDBC URL, Benutzername und Passwort für die MySQL-Datenbank
        public static final String DB_URL = "jdbc:mysql://localhost:3306/projektarbeit";
        public static final String DB_USER = "root";
        public static final String DB_PASSWORD = "Jan_hesch501";

    /**
     * Zeigt eine grafische Benutzeroberfläche (GUI) an, mit der ein Patient durch Eingabe seiner ID gelöscht werden kann.
     * <p>
     * Die Methode erstellt ein Fenster, in dem der Benutzer die ID eines Patienten eingeben und die Löschung bestätigen kann.
     * Es wird überprüft, ob die ID gültig ist, und die Methode {@link #löschePatientMitId(int)} wird aufgerufen, um die Löschung durchzuführen.
     */
    public static void patientLöschen() {

            // Erstellen des Fensters
            JFrame frame = new JFrame("Patient löschen");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(300, 200);

            // Layout festlegen
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 1));

            // GUI-Komponenten
            JLabel idLabel = new JLabel("ID des Patienten eingeben:");
            JTextField idField = new JTextField(10);
            JButton deleteButton = new JButton("Löschen");
            JButton exitButton = new JButton("Abbrechen");

            // Komponenten zum Panel hinzufügen
            panel.add(idLabel);
            panel.add(idField);
            panel.add(deleteButton);
            panel.add(exitButton);

            // Button ActionListener für "Abbrechen"
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });

            // Button ActionListener für "Löschen"
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String idText = idField.getText();


                    if (idText.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Bitte geben Sie eine gültige ID ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        int id = Integer.parseInt(idText);

                        // Aufruf der Löschfunktion
                        boolean success = löschePatientMitId(id);

                        if (success) {
                            JOptionPane.showMessageDialog(frame, "Der Patient wurde erfolgreich gelöscht.", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
                            idField.setText(""); // Eingabefeld zurücksetzen

                        } else {
                            JOptionPane.showMessageDialog(frame, "Es wurde kein Patient mit dieser ID gefunden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Bitte geben Sie eine gültige Zahl für die ID ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            // Panel zum Frame hinzufügen
            frame.add(panel);

            // Frame sichtbar machen
            frame.setVisible(true);
        }


    /**
     * Löscht einen Patienten aus der Datenbank basierend auf der angegebenen ID.
     * <p>
     * Diese Methode führt eine SQL-DELETE-Anweisung aus, um den Datensatz mit der angegebenen ID aus der Tabelle "patients" zu löschen.
     * <p>
     * @param id Die eindeutige ID des zu löschenden Patienten.
     * @return {@code true}, wenn der Patient erfolgreich gelöscht wurde, andernfalls {@code false}.
     */
        public static boolean löschePatientMitId(int id) {
            String sql = "DELETE FROM patients WHERE `ID Patient` = ?";

            //Connection aufbauen
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // ID einfügen
                pstmt.setInt(1, id);

                // SQL Abfrage ausführen
                int rowsDeleted = pstmt.executeUpdate();

                // Prüfen, ob ein Datensatz gelöscht wurde
                return rowsDeleted > 0;

                // Exception werfen, falls die Daten nicht gelöscht werden konnten
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Daten konnten nicht gelöscht werden. Fehler: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }


