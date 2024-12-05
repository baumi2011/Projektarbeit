import javax.swing.*;
import java.util.Date;

public class Patient extends JFrame {

    private int IDPatient;
    private int SVNNummer;
    private Date Geburtsdatum;
    private String Wohnadresse;
    private String Vorname;
    private String Nachname;
    private char Geschlecht;




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