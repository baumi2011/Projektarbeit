import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                String idText = idField.getText();

                // Verwenden von RegEx, um sicherzustellen, dass die ID nur Zahlen enthält
                String idPattern = "^[0-9]+$"; // Nur Zahlen
                Pattern pattern = Pattern.compile(idPattern);
                Matcher matcher = pattern.matcher(idText);

                if (!matcher.matches()) {
                    JOptionPane.showMessageDialog(frame, "Bitte geben Sie eine gültige numerische ID ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id = Integer.parseInt(idText);

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
                            idField.setEditable(false); // ID nicht mehr änderbar nach Suche
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

                // Die ID darf nicht verändert werden
                String vorname = nameField.getText();
                String nachname = nachnameField.getText();
                String alterStr = ageField.getText();
                String geschlecht = geschlechtField.getText();
                String svnNummer = svnField.getText();
                String geburtsdatum = gebField.getText();
                String adresse = addressField.getText();
                String telefonnummer = phoneField.getText();

                // Eingabeüberprüfungen
                if (vorname.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Das Feld 'Vorname' darf nicht leer sein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (nachname.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Das Feld 'Nachname' darf nicht leer sein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Alter überprüfen
                if (alterStr.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Das Feld 'Alter' darf nicht leer sein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int alter = Integer.parseInt(alterStr);
                    if (alter < 0 || alter > 120) {
                        JOptionPane.showMessageDialog(frame, "Das Alter muss eine Zahl zwischen 0 und 120 sein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Das Alter muss eine gültige Zahl sein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Geschlecht überprüfen
                if (!geschlecht.equalsIgnoreCase("männlich") && !geschlecht.equalsIgnoreCase("weiblich") && !geschlecht.equalsIgnoreCase("divers")) {
                    JOptionPane.showMessageDialog(frame, "Das Geschlecht muss 'männlich', 'weiblich' oder 'divers' sein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Geburtsdatum überprüfen
                try {
                    LocalDate.parse(geburtsdatum);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(frame, "Bitte geben Sie ein gültiges Geburtsdatum im Format 'yyyy-mm-dd' ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Telefonnummer überprüfen
                if (phoneField.getText().isEmpty()|| phoneField.getText().length() > 13 || phoneField.getText().length() <3) {
                    JOptionPane.showMessageDialog(frame, "Die Telefonnummer muss zwischen 0 und 13 Ziffern enthalten.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // SVN-Nummer überprüfen
                if (!svnField.getText().matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(frame, "Die SVN Nummer muss genau 10 Ziffern enthalten.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (addressField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Das Feld 'Adresse' darf nicht leer sein." , "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int alter = Integer.parseInt(alterStr);

                    // Überprüfung, ob SVN oder Telefonnummer bereits existiert
                    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                        String checkSQL = "SELECT * FROM patients WHERE (`SVN Nummer` = ? OR Telefonnummer = ?) AND `ID Patient` != ?";
                        try (PreparedStatement pstmt = conn.prepareStatement(checkSQL)) {
                            pstmt.setString(1, svnNummer);
                            pstmt.setString(2, telefonnummer);
                            pstmt.setInt(3, id);

                            ResultSet rs = pstmt.executeQuery();
                            if (rs.next()) {
                                if (rs.getString("SVN Nummer").equals(svnNummer)) {
                                    JOptionPane.showMessageDialog(frame, "Diese SVN-Nummer existiert bereits.", "Fehler", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                if (rs.getString("Telefonnummer").equals(telefonnummer)) {
                                    JOptionPane.showMessageDialog(frame, "Diese Telefonnummer existiert bereits.", "Fehler", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                            }
                        }

                        // SQL-Update durchführen
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
                            pstmt.executeUpdate();
                            JOptionPane.showMessageDialog(frame, "Patientendaten wurden erfolgreich aktualisiert.", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Fehler beim Überprüfen der Daten: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Das Alter muss eine Zahl sein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Fenster anzeigen
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}