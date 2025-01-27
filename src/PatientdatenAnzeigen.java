import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class PatientdatenAnzeigen {

    // JDBC URL, Benutzername und Passwort für die MySQL-Datenbank
    public static final String DB_URL = "jdbc:mysql://localhost:3306/projektarbeit";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "Jan_hesch501";

    /**
     * Zeigt eine GUI an, die alle Patienten aus der Datenbank lädt und anzeigt.
     * Ermöglicht dem Benutzer, einen Patienten auszuwählen.
     */
    public static void patientDatenAnzeigen() {
        // Fenster erstellen
        JFrame frame = new JFrame("Patientendaten anzeigen");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        // Hauptpanel erstellen
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Tabelle zur Anzeige der Patienten erstellen
        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        table.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Spalten für die Tabelle definieren (SVN Nummer hinzugefügt)
        tableModel.addColumn("ID");
        tableModel.addColumn("Vorname");
        tableModel.addColumn("Nachname");
        tableModel.addColumn("Geburtsdatum");
        tableModel.addColumn("Adresse");
        tableModel.addColumn("SVN Nummer");  // Neue Spalte für SVN Nummer

        // Patienten aus der Datenbank laden
        ladePatientenAusDatenbank(tableModel);

        // Die Zellen der Tabelle nicht editierbar machen
        table.setDefaultEditor(Object.class, null); // Setzt den Editor für alle Zellen auf null (nicht editierbar)

        // Buttons erstellen
        JButton exitButton = new JButton("Abbrechen");

        // Button-Panel erstellen
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(exitButton);

        // Aktion für "Schließen" Button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Hauptpanel zusammenbauen
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Panel zum Frame hinzufügen
        frame.add(mainPanel);

        // Frame sichtbar machen
        frame.setVisible(true);
    }

    /**
     * Lädt alle Patienten aus der Tabelle `patients` und fügt sie in das Tabellenmodell ein.
     *
     * @param tableModel Das Tabellenmodell, in das die Patientendaten eingefügt werden.
     */
    private static void ladePatientenAusDatenbank(DefaultTableModel tableModel) {
        String sql = "SELECT `ID Patient`, `Vorname`, `Nachname`, `Geburtsdatum`, `Adresse`, `SVN Nummer` FROM patients";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Zeilen aus dem ResultSet zur Tabelle hinzufügen
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("ID Patient"));
                row.add(rs.getString("Vorname"));
                row.add(rs.getString("Nachname"));
                row.add(rs.getDate("Geburtsdatum"));
                row.add(rs.getString("Adresse"));
                row.add(rs.getString("SVN Nummer"));
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Laden der Patientendaten: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }
}