import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PatientDatabase {


    // JDBC URL, Benutzername und Passwort für die MySQL-Datenbank
    public static final String DB_URL = "jdbc:mysql://localhost:3306/projektarbeit";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "Jan_hesch501";

    public static void patientenDaten(){

        // Erstellen des Fensters
        JFrame frame = new JFrame("Patientendatenbank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Layout festlegen
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // GUI-Komponenten erstellen
        JLabel idLabel = new JLabel("ID");
        JTextField idField = new JTextField(10);

        JLabel nameLabel = new JLabel("Vorname:");
        JTextField nameField = new JTextField();

        JLabel nachnameLabel = new JLabel("Nachname:");
        JTextField nachnameField = new JTextField();

        JLabel ageLabel = new JLabel("Alter:");
        JTextField ageField = new JTextField();

        JLabel geschlechtLabel = new JLabel("Geschlecht:");
        JTextField geschlechtField = new JTextField();

        JLabel svnLabel = new JLabel("SVN Nummer:");
        JTextField svnField = new JTextField(10);

        JLabel gebLabel = new JLabel("Geburtsdatum:");
        JTextField gebField = new JTextField();

        JLabel addressLabel = new JLabel("Adresse:");
        JTextField addressField = new JTextField();

        JLabel phoneLabel = new JLabel("Telefonnummer:");
        JTextField phoneField = new JTextField();

        JButton submitButton = new JButton("Hinzufügen");

        JButton exitButton = new JButton("Abbrechen");

        // Komponenten zum Panel hinzufügen
        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(nachnameLabel);
        panel.add(nachnameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(geschlechtLabel);
        panel.add(geschlechtField);
        panel.add(svnLabel);
        panel.add(svnField);
        panel.add(gebLabel);
        panel.add(gebField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(new JLabel()); // Platzhalter
        panel.add(submitButton);
        panel.add(exitButton);

        // Button-ActionListener exit
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Button-ActionListener hinzufügen
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String Vorname = nameField.getText();
                String Nachname = nachnameField.getText();
                int Alter;
                String Geschlecht = geschlechtField.getText();
                String SvnNummer = svnField.getText();
                String Geburtsdatum = gebField.getText();
                String Adresse = addressField.getText();
                String Telefonnummer = phoneField.getText();



                if (Vorname.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Das Feld 'Vorname' darf nicht leer sein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (Nachname.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Das Feld 'Nachname' darf nicht leer sein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Alter = Integer.parseInt(ageField.getText());
                    if (Alter <= 0 || Alter > 120) {
                        JOptionPane.showMessageDialog(frame, "Bitte geben Sie ein realistisches Alter ein (zwischen 1 und 120).", "Fehler", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Bitte geben Sie ein gültiges Alter ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!Geschlecht.equalsIgnoreCase("männlich") && !Geschlecht.equalsIgnoreCase("weiblich") && !Geschlecht.equalsIgnoreCase("divers")) {
                    JOptionPane.showMessageDialog(frame, "Bitte geben Sie ein gültiges Geschlecht ein (männlich, weiblich, divers).", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (SvnNummer.isEmpty() || !SvnNummer.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(frame, "Bitte geben Sie eine gültige Sozialversicherungsnummer ein (10 Ziffern).", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    LocalDate.parse(Geburtsdatum);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(frame, "Bitte geben Sie ein gültiges Geburtsdatum im Format 'yy-mm-dd' ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (Adresse.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Das Feld 'Adresse' darf nicht leer sein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!Telefonnummer.matches("\\+?\\d+")) {
                    JOptionPane.showMessageDialog(frame, "Bitte geben Sie eine gültige Telefonnummer ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }



                // Patientendaten in die Datenbank einfügen
                insertPatientData(id, Vorname, Nachname, Alter, Geschlecht, SvnNummer, Geburtsdatum, Adresse, Telefonnummer);
            }
        });

        // Panel zum Frame hinzufügen
        frame.add(panel);

        // Frame sichtbar machen
        frame.setVisible(true);
    }

    static void insertPatientData(int id, String Vorname, String Nachname, int Alter, String Geschlecht, String svnNummer, String geburtsdatum, String adresse, String telefonnummer) {
        // SQL-Statement zum Einfügen von Daten
        String sql = "INSERT INTO patients (`ID Patient`, Vorname, Nachname,`Alter`, Geschlecht, `SVN Nummer`, Geburtsdatum, Adresse, Telefonnummer) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Platzhalter mit den tatsächlichen Werten füllen
            pstmt.setInt(1, id);
            pstmt.setString(2, Vorname);
            pstmt.setString(3, Nachname);
            pstmt.setInt(4, Alter);
            pstmt.setString(5, Geschlecht);
            pstmt.setString(6, svnNummer);
            pstmt.setString(7, geburtsdatum);
            pstmt.setString(8, adresse);
            pstmt.setString(9, telefonnummer);



            // Ausführen des SQL-Statements
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Die Patientendaten wurden erfolgreich hinzugefügt.", "Erfolg", JOptionPane.INFORMATION_MESSAGE);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Daten konnten nicht hinzugefügt werden. Fehler: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }





}


