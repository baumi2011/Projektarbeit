import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

/**
 * Die Klasse {@code Patient} stellt das Hauptfenster einer Patientendatenbank-Anwendung dar.
 * Sie ermöglicht die Verwaltung von Patientendaten, einschließlich Hinzufügen, Bearbeiten, Löschen und Suchen.
 * Die Klasse verwendet eine grafische Benutzeroberfläche basierend auf {@link JFrame} und Swing-Komponenten.
 * <p>
 * Funktionen der Klasse:
 *     <li>Anzeigen von Patientendaten aus einer MySQL-Datenbank in einer Tabelle</li>
 *     <li>Hinzufügen, Bearbeiten, Löschen und Suchen von Patientendaten über Menüs</li>
 *     <li>Aktualisieren der Datenanzeige durch Benutzerinteraktion</li>
 */
public class Patient extends JFrame {
    private PatientHinzufügen patientHinzufügen;
    private PatientBearbeitung patientBearbeitung;
    private PatientLöschen patientLöschen;
    private PatientenSuchen patientenSuchen;
    private JPanel JPanel1;

    /**
     * Konstruktor für die Patient-Klasse.
     * <p>
     * Initialisiert das Hauptfenster der Anwendung, setzt den Titel und fügt die Menüleiste hinzu.
     * <p>
     * Die Patientendaten werden sofort unter der Menüleiste angezeigt.
     */
    Patient() {
        setTitle("Patient");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(JPanel1);
        Menubar();
        pack();
        setSize(800, 600);
        setVisible(true);

        // Erstelle das Panel, das die Patientendaten anzeigt und füge es zu JPanel1 hinzu
        JPanel patientPanel = patientDatenAnzeigenPanel();
        JPanel1.add(patientPanel, BorderLayout.CENTER);
    }

    /**
     * Erzeugt ein Panel, das die Patientendaten anzeigt.
     */
    public JPanel patientDatenAnzeigenPanel() {
        // Erstelle ein neues Panel für die Patientendaten
        JPanel panel = new JPanel(new BorderLayout());

        // Tabelle zur Anzeige der Patienten erstellen
        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        table.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        tableModel.addColumn("ID");
        tableModel.addColumn("Vorname");
        tableModel.addColumn("Nachname");
        tableModel.addColumn("Geburtsdatum");
        tableModel.addColumn("Geschlecht");
        tableModel.addColumn("Alter");
        tableModel.addColumn("Telefonnummer");
        tableModel.addColumn("Adresse");
        tableModel.addColumn("SVN Nummer");


        // Patienten aus der Datenbank laden
        ladePatientenAusDatenbank(tableModel);

        // Die Zellen der Tabelle nicht editierbar machen
        table.setDefaultEditor(Object.class, null);

        // Füge die Tabelle zum Panel hinzu
        panel.add(scrollPane, BorderLayout.CENTER);

        // Erstelle den Aktualisieren-Button
        JButton aktualisierenButton = new JButton("Aktualisieren");
        aktualisierenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Tabelle löschen und erneut laden
                tableModel.setRowCount(0);  // Entfernt alle bestehenden Zeilen
                ladePatientenAusDatenbank(tableModel);  // Lädt die neuen Daten aus der DB
            }
        });

        // Den Button zum Panel hinzufügen
        panel.add(aktualisierenButton, BorderLayout.SOUTH);

        // Vergrößere das Panel für die Patientendaten
        panel.setPreferredSize(new Dimension(810, 540)); // Panel wird auf volle Fensterhöhe gesetzt
        panel.setMinimumSize(new Dimension(810, 540)); // Verhindere, dass das Panel zu klein wird

        return panel;
    }

    /**
     * Lädt alle Patienten aus der Tabelle `patients` in der MySQL-Datenbank und fügt sie in das angegebene Tabellenmodell ein.
     */
    private static void ladePatientenAusDatenbank(DefaultTableModel tableModel) {
        // SQL-Abfrage, um alle relevanten Patientendaten abzurufen
        String sql = "SELECT `ID Patient`, `Vorname`, `Nachname`, `Geburtsdatum`, `Geschlecht`, `Alter`, `Telefonnummer`, `Adresse`, `SVN Nummer` FROM patients";


        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projektarbeit", "root", "Jan_hesch501");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Zeilen aus dem ResultSet zur Tabelle hinzufügen
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("ID Patient"));
                row.add(rs.getString("Vorname"));
                row.add(rs.getString("Nachname"));
                row.add(rs.getDate("Geburtsdatum"));
                row.add(rs.getString("Geschlecht"));
                row.add(rs.getString("Alter"));
                row.add(rs.getString("Telefonnummer"));
                row.add(rs.getString("Adresse"));
                row.add(rs.getString("SVN Nummer"));




                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            // Fehlerbehandlung bei der Datenbankabfrage
            JOptionPane.showMessageDialog(null, "Fehler beim Laden der Patientendaten: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Erstellt und konfiguriert die Menüleiste der Anwendung.
     * Die Menüleiste enthält folgende Optionen:
     * <ul>
     *   <li>Hinzufügen von neuen Patienten</li>
     *   <li>Bearbeiten von Patientendaten</li>
     *   <li>Löschen von Patienten</li>
     *   <li>Suchen von Patienten</li>
     *   <li>Beenden der Anwendung</li>
     * </ul>
     */
    private void Menubar() {
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu menu = new JMenu("Datei");
        menubar.add(menu);

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);

        // "Hinzufügen" an der Menüleiste hinzufügen
        JMenuItem hinzufügenButtonItem = new JMenuItem("Hinzufügen");
        menubar.add(hinzufügenButtonItem);

        // "Bearbeiten" an der Menüleiste hinzufügen
        JMenuItem bearbeitenButtonItem = new JMenuItem("Bearbeiten");
        menubar.add(bearbeitenButtonItem);

        // "Löschen" an der Menüleiste hinzufügen
        JMenuItem löschenButtonItem = new JMenuItem("Löschen");
        menubar.add(löschenButtonItem);

        // "PatientenSuchen" an der Menüleiste hinzufügen
        JMenuItem PatientSuchenButtonItem = new JMenuItem("PatientSuchen");
        menubar.add(PatientSuchenButtonItem);

        // LöschenButton Listener hinzufügen mit Aufruf der GUI und der Methoden zum Löschen
        löschenButtonItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientLöschen = new PatientLöschen();
                PatientLöschen.patientLöschen();
            }
        });

        // BearbeitenButton hinzufügen mit Aufruf der GUI und der Methoden zur Bearbeitung
        bearbeitenButtonItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientBearbeitung = new PatientBearbeitung();
                PatientBearbeitung.patientenBearbeitenGUI();
            }
        });

        // HinzufügenButton hinzufügen mit Aufruf der GUI und der Methoden zum Hinzufügen eines neuen Patienten
        hinzufügenButtonItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientHinzufügen = new PatientHinzufügen();
                PatientHinzufügen.patientenDaten();
            }
        });

        // Patientsuchen hinzufügen mit Aufruf der GUI und der Methode zum Anzeigen der Patienten
        PatientSuchenButtonItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientenSuchen = new PatientenSuchen();
                PatientenSuchen.patientDatenAnzeigenMitSuche();


            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}