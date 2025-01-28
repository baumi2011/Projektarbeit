import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;




/**
 * Die Klasse {PatientenSuchen} stellt eine grafische Benutzeroberfläche (GUI) bereit,
 * die es ermöglicht, Patientendaten basierend auf einem Suchtext zu durchsuchen und anzuzeigen.
 * <p>
 * Die Klasse verwendet JDBC zur Verbindung mit einer MySQL-Datenbank und Swing zur Implementierung der GUI.
 * </p>
 */
public class PatientenSuchen {

    /**
     * Die URL der MySQL-Datenbank.
     * Der Benutzername für die Verbindung zur MySQL-Datenbank.
     * Das Passwort für die Verbindung zur MySQL-Datenbank.
     */
    public static final String DB_URL = "jdbc:mysql://localhost:3306/projektarbeit";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "Jan_hesch501";

    /**
     * Zeigt eine GUI an, die es ermöglicht, Patienten basierend auf einer RegEx-Suche anzuzeigen.
     * <p>
     * Diese Methode erstellt ein JFrame mit einem Suchfeld, einer Tabelle zur Anzeige der Patientendaten
     * und Buttons zur Interaktion. Patientendaten können mithilfe eines Suchtextes dynamisch gefiltert werden.
     * </p>
     */
    public static void patientDatenAnzeigenMitSuche() {

        // Fenster erstellen
        JFrame frame = new JFrame("Patientendaten anzeigen mit Suche");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 500);

        // Hauptpanel erstellen
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Suchfeld und Suchbutton
        JPanel searchPanel = new JPanel(new FlowLayout());
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Suchen");
        searchPanel.add(new JLabel("Suchtext eingeben:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Tabelle zum Anzeigen der Patienten
        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        table.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Spalten für die Tabelle
        tableModel.addColumn("ID");
        tableModel.addColumn("Vorname");
        tableModel.addColumn("Nachname");
        tableModel.addColumn("Geburtsdatum");
        tableModel.addColumn("Adresse");
        tableModel.addColumn("SVN Nummer");

        // Patienten initial laden
        ladePatientenAusDatenbank(tableModel, "");

        // Die Zellen der Tabelle nicht editierbar machen
        table.setDefaultEditor(Object.class, null);

        // Buttons erstellen
        JButton exitButton = new JButton("Abbrechen");

        // Button Panel erstellen
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(exitButton);

        // AktionButton "Schließen"
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // AktionListener für Suchbutton
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().trim();
                if (!searchText.isEmpty()) {
                    ladePatientenAusDatenbank(tableModel, searchText);
                } else {
                    JOptionPane.showMessageDialog(frame, "Bitte geben Sie einen Suchtext ein.", "Hinweis", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Hauptpanel Layout festlegen
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Panel zum Frame hinzufügen
        frame.add(mainPanel);

        // Frame sichtbar machen
        frame.setVisible(true);
    }

    /**
     * Lädt Patienten basierend auf einem Suchtext aus der Tabelle {patients} und fügt sie in das Tabellenmodell ein.
     * <p>
     * Wenn der Suchtext leer ist, werden alle Patienten geladen. Die Methode stellt sicher, dass
     * die Ergebnisse in der Tabelle angezeigt werden.
     * </p>
     *
     * @param tableModel Das Tabellenmodell, in das die Patientendaten eingefügt werden.
     * @param searchText Der Suchtext für die Filterung (RegEx).
     */
    private static void ladePatientenAusDatenbank(DefaultTableModel tableModel, String searchText) {
        // Tabelle leeren
        tableModel.setRowCount(0);

        String sql = "SELECT `ID Patient`, `Vorname`, `Nachname`, `Geburtsdatum`, `Adresse`, `SVN Nummer` " +
                "FROM patients " +
                "WHERE `ID Patient` LIKE ? OR `Vorname` LIKE ? OR `Nachname` LIKE ? OR `Geburtsdatum` LIKE ? OR `Adresse` LIKE ? OR `SVN Nummer` LIKE ?";

        //Connection aufbauen
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String query = "%" + searchText + "%";
            pstmt.setString(1, query);
            pstmt.setString(2, query);
            pstmt.setString(3, query);
            pstmt.setString(4, query);
            pstmt.setString(5, query);
            pstmt.setString(6, query);

            ResultSet rs = pstmt.executeQuery();


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
         //Exception werfen, falls ein Fehler beim Laden auftritt
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Laden der Patientendaten: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }
}


