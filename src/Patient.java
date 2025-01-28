import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Patient extends JFrame {
    private PatientHinzufügen patientHinzufügen;
    private PatientBearbeitung patientBearbeitung;
    private PatientLöschen patientLöschen;
    private PatientdatenAnzeigen patientdatenAnzeigen;
    private PatientenSuchen patientenSuchen;
    private Patient patient;

    private int IDPatient;
    private int SVNNummer;
    private Date Geburtsdatum;
    private String Wohnadresse;
    private String Vorname;
    private String Nachname;
    private char Geschlecht;
    private JPanel JPanel1;


    /**
     * Konstruktor für die Patient-Klasse.
     * Initialisiert das Hauptfenster der Anwendung, setzt den Titel und fügt die Menüleiste hinzu.
     */
    Patient() {
        setTitle("Patient");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(JPanel1);
        Menubar();
        pack();
        setSize(800, 600);
        setVisible(true);
    }

    /**
     * Erstellt und konfiguriert die Menüleiste der Anwendung.
     * Die Menüleiste enthält folgende Optionen:
     * <ul>
     *   <li>Hinzufügen von neuen Patienten</li>
     *   <li>Bearbeiten von Patientendaten</li>
     *   <li>Löschen von Patienten</li>
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

        // "Hinzufügen" an der Hauptmenüleiste hinzufügen
        JMenuItem hinzufügenButtonItem = new JMenuItem("Hinzufügen");
        menubar.add(hinzufügenButtonItem);

        // Bearbeiten an der Menüleiste hinzufügen
        JMenuItem bearbeitenButtonItem = new JMenuItem("Bearbeiten");
        menubar.add(bearbeitenButtonItem);

        // Löschen an der Menüleiste hinzufügen
        JMenuItem löschenButtonItem = new JMenuItem("Löschen");
        menubar.add(löschenButtonItem);

        // PatientendatenAnzeigen an der Menüleiste hinzufügen
        JMenuItem PatientendatenAnzeigenButtonItem = new JMenuItem("PatientendatenAnzeigen");
        menubar.add(PatientendatenAnzeigenButtonItem);

        // PatientenSuchen an der Menüleiste hinzufügen
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

        // PatientendatenAnzeigen hinzufügen mit Aufruf der GUI und der Methoden zum Anzeigen von Patientendaten
        PatientendatenAnzeigenButtonItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientdatenAnzeigen = new PatientdatenAnzeigen();
                PatientdatenAnzeigen.patientDatenAnzeigen();
            }
        });

        //Patientsuchen hinzufügen
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
