import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Die Klasse PatientBearbeitung bietet Funktionalitäten zur Bearbeitung von Patientendaten
 * in einer MySQL-Datenbank über eine grafische Benutzeroberfläche (GUI).
 * <p>
 * Sie ermöglicht das Abrufen und Aktualisieren von Patientendaten basierend auf der eingegebenen Patienten-ID.
 */
public class PatientBearbeitung {

    // JDBC-Verbindungsdetails
    public static final String DB_URL = "jdbc:mysql://localhost:3306/projektarbeit";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "Jan_hesch501";

/**
 * Zeigt eine grafische Benutzeroberfläche (GUI) zur Bearbeitung von Patientendaten an.
 * <p>
 * Funktionen:
 * <ul>
 * <li>Benutzer kann die ID eines Patienten eingeben und dessen Daten abrufen.</li>
 * <li>Benutzer kann die Patientendaten bearbeiten und die Änderungen in der Datenbank speichern.</li>
 * <li>Ein "Abbrechen"-Button schließt die Anwendung.</li>
 * </ul>
 * <p>
 * Die GUI überprüft die Eingaben auf Fehler und zeigt entsprechende Fehlermeldungen an.
 */

    public static void patientenBearbeitenGUI() {
        // Erstellen des Hauptfensters
        JFrame frame = new JFrame("Patientendaten bearbeiten");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Layout und Komponenten
        JPanel panel = new JPanel(new GridLayout(11, 2, 11, 11));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField nachnameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField geschlechtField = new JTextField();
        JTextField svnField = new JTextField();
        JTextField gebField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField phoneField = new JTextField();

        JButton searchButton = new JButton("Suchen");
        JButton updateButton = new JButton("Aktualisieren");
        JButton exitButton = new JButton("Abbrechen");

        panel.add(new JLabel("ID des Patienten:"));
        panel.add(idField);
        panel.add(new JLabel("Vorname:"));
        panel.add(nameField);
        panel.add(new JLabel("Nachname:"));
        panel.add(nachnameField);
        panel.add(new JLabel("Alter:"));
        panel.add(ageField);
        panel.add(new JLabel("Geschlecht:"));
        panel.add(geschlechtField);
        panel.add(new JLabel("SVN Nummer:"));
        panel.add(svnField);
        panel.add(new JLabel("Geburtsdatum (dd.MM.yyyy):"));
        panel.add(gebField);
        panel.add(new JLabel("Adresse:"));
        panel.add(addressField);
        panel.add(new JLabel("Telefonnummer:"));
        panel.add(phoneField);

        panel.add(searchButton);
        panel.add(updateButton);
        panel.add(exitButton);

        // ActionListener für den "Suchen"-Button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(idField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Bitte geben Sie eine gültige ID ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Patientendaten abrufen
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    String sql = "SELECT * FROM patients WHERE `ID Patient` = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setInt(1, id);
                        ResultSet rs = pstmt.executeQuery();

                        if (rs.next()) {
                            // Felder mit den Patientendaten füllen
                            nameField.setText(rs.getString("Vorname"));
                            nachnameField.setText(rs.getString("Nachname"));
                            ageField.setText(String.valueOf(rs.getInt("Alter")));
                            geschlechtField.setText(rs.getString("Geschlecht"));
                            svnField.setText(rs.getString("SVN Nummer"));
                            gebField.setText(rs.getString("Geburtsdatum"));
                            addressField.setText(rs.getString("Adresse"));
                            phoneField.setText(rs.getString("Telefonnummer"));
                        } else {
                            JOptionPane.showMessageDialog(frame, "Kein Patient mit dieser ID gefunden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Fehler beim Abrufen der Daten: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
    frame.dispose();
}
        });


        // ActionListener für den "Aktualisieren"-Button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(idField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Bitte geben Sie eine gültige ID ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Daten aus den Feldern abrufen
                String vorname = nameField.getText();
                String nachname = nachnameField.getText();
                String alterStr = ageField.getText();
                String geschlecht = geschlechtField.getText();
                String svnNummer = svnField.getText();
                String geburtsdatum = gebField.getText();
                String adresse = addressField.getText();
                String telefonnummer = phoneField.getText();

                try {
                    int alter = Integer.parseInt(alterStr);

                    // SQL-Update durchführen
                    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                        String sql = "UPDATE patients SET Vorname = ?, Nachname = ?, `Alter` = ?, Geschlecht = ?, `SVN Nummer` = ?, Geburtsdatum = ?, Adresse = ?, Telefonnummer = ? WHERE `ID Patient` = ?";
                        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                            pstmt.setString(1, vorname);
                            pstmt.setString(2, nachname);
                            pstmt.setInt(3, alter);
                            pstmt.setString(4, geschlecht);
                            pstmt.setString(5, svnNummer);
                            pstmt.setString(6, geburtsdatum);
                            pstmt.setString(7, adresse);
                            pstmt.setString(8, telefonnummer);
                            pstmt.setInt(9, id);

                            int rowsUpdated = pstmt.executeUpdate();
                            if (rowsUpdated > 0) {
                                JOptionPane.showMessageDialog(frame, "Patientendaten erfolgreich aktualisiert.", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Fehler: Kein Patient mit dieser ID gefunden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Bitte geben Sie ein gültiges Alter ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Fehler beim Aktualisieren der Daten: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}

