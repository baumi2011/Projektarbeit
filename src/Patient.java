import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;




public class Patient extends JFrame {

    private int IDPatient;
    private int SVNNummer;
    private Date Geburtsdatum;
    private String Wohnadresse;
    private String Vorname;
    private String Nachname;
    private char Geschlecht;
    private JPanel JPanel1;

    Patient(){
        setTitle("Patient");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(JPanel1);
        Menubar();
        pack();

    }

    private void Menubar(){
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu menu = new JMenu("Datei");
        menubar.add(menu);

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem druckItem = new JMenuItem("Drucken");
        menu.add(druckItem);

        druckItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Erstelle einen PrinterJob
                PrinterJob printerJob = PrinterJob.getPrinterJob();

                // Setze den Druckinhalt (Printable-Objekt), das das zu druckende Layout definiert
                printerJob.setPrintable(new Printable() {
                    @Override
                    public int print(java.awt.Graphics graphics, java.awt.print.PageFormat pageFormat, int pageIndex) throws PrinterException {
                        if (pageIndex > 0) {
                            return NO_SUCH_PAGE; // Keine weiteren Seiten
                        }

                        // Erstelle den zu druckenden Text
                        String textToPrint = "Dies ist der Text, der gedruckt wird.\nMehr Text für den Druck.";

                        // Zeichne den Text auf der Druckseite
                        graphics.drawString(textToPrint, 100, 100);

                        return PAGE_EXISTS; // Seite existiert
                    }
                });

                // Öffne den Druckdialog, um den Drucker auszuwählen
                if (printerJob.printDialog()) {
                    try {
                        printerJob.print(); // Führe den Druckauftrag aus
                        JOptionPane.showMessageDialog(druckItem, "Druckauftrag wurde erfolgreich ausgeführt.");
                    } catch (PrinterException ex) {
                        JOptionPane.showMessageDialog(druckItem, "Fehler beim Drucken: " + ex.getMessage(),
                                "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });



        JMenuItem exportItem = new JMenuItem("Exportieren");
        menu.add(exportItem);

        exportItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String textzumexportieren = "Text";
                JFileChooser auswaelen = new JFileChooser();
                auswaelen.setDialogTitle("Wählen Sie den Speicherort für den Export");
                int userSelection = auswaelen.showSaveDialog(exportItem);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToExport = auswaelen.getSelectedFile();

                    // Wenn die Datei keine Endung hat, füge ".txt" hinzu
                    if (!fileToExport.getName().endsWith(".txt")) {
                        fileToExport = new File(fileToExport.getAbsolutePath() + ".txt");
                    }

                    // Schreibe den Text in die Datei
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToExport))) {
                        writer.write(textzumexportieren);
                        JOptionPane.showMessageDialog(exportItem, "Datei wurde erfolgreich exportiert.");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(exportItem, "Fehler beim Exportieren der Datei: " + ex.getMessage(),
                                "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }




    public int getIDPatient() {
        return IDPatient;
    }

    public void setIDPatient(int IDPatient) {
        this.IDPatient = IDPatient;
    }

    public int getSVNNummer() {
        return SVNNummer;
    }

    public void setSVNNummer(int SVNNummer) {
        this.SVNNummer = SVNNummer;
    }

    public Date getGeburtsdatum() {
        return Geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        Geburtsdatum = geburtsdatum;
    }

    public String getWohnadresse() {
        return Wohnadresse;
    }

    public void setWohnadresse(String wohnadresse) {
        Wohnadresse = wohnadresse;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String vorname) {
        Vorname = vorname;
    }

    public String getNachname() {
        return Nachname;
    }

    public void setNachname(String nachname) {
        Nachname = nachname;
    }

    public char getGeschlecht() {
        return Geschlecht;
    }

    public void setGeschlecht(char geschlecht) {
        Geschlecht = geschlecht;
    }
}