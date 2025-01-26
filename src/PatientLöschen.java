
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PatientLöschen {



        // JDBC URL, Benutzername und Passwort für die MySQL-Datenbank
        public static final String DB_URL = "jdbc:mysql://localhost:3306/projektarbeit";
        public static final String DB_USER = "root";
        public static final String DB_PASSWORD = "Jan_hesch501";

        public static void patientLöschen() {
            // Erstellen des Fensters
            JFrame frame = new JFrame("Patient löschen");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);

            // Layout festlegen
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 1));

            // GUI-Komponenten erstellen
            JLabel idLabel = new JLabel("ID des Patienten eingeben:");
            JTextField idField = new JTextField(10);
            JButton deleteButton = new JButton("Löschen");
            JButton exitButton = new JButton("Abbrechen");

            // Komponenten zum Panel hinzufügen
            panel.add(idLabel);
            panel.add(idField);
            panel.add(deleteButton);
            panel.add(exitButton);

            // Button-ActionListener für "Abbrechen"
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });

            // Button-ActionListener für "Löschen"
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String idText = idField.getText();

                    // Überprüfen, ob die ID eingegeben wurde
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

        // Methode zum Löschen eines Patienten aus der Datenbank
        public static boolean löschePatientMitId(int id) {
            String sql = "DELETE FROM patients WHERE `ID Patient` = ?";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // ID in den PreparedStatement einfügen
                pstmt.setInt(1, id);

                // SQL-Abfrage ausführen
                int rowsDeleted = pstmt.executeUpdate();

                // Prüfen, ob ein Datensatz gelöscht wurde
                return rowsDeleted > 0;

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Daten konnten nicht gelöscht werden. Fehler: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }


