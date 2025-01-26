import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Die Klasse Login implementiert ein GUI-basiertes Login-Fenster für die Benutzeranmeldung.
 * <p>
 * - Initialisierung des Login-Fensters.
 * <p>
 * - Überprüfung der Benutzerdaten (Benutzername und Passwort).
 * <p>
 * - Ausgabe von Fehlermeldungen bei falschen Eingaben.
 * <p>
 * - Weiterleitung zu einer neuen Instanz der Klasse {@link Patient}, wenn die Anmeldedaten korrekt sind.
 */
public class Login extends JFrame {

    private JPanel panel1;
    private JPanel Benutzer;
    private JTextField BenutzerFeld;
    private JButton loginButton;
    private JTextArea AusgabeFeld;
    private JPasswordField PasswortFeld;
    private JTextField Benutzername;
    private final String BenutzerName = "root";
    private final String Passwort = "1234";

    /**
     * Konstuktor für die Login Klasse
     * <p>
     *  Initialisiert das Login-Fenster, macht es sichtbar und ruft die LoginFunktion Methode auf.
     */
    Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);
        LoginFunktion();

    }

    /**
     * Die Methode LoginFunktion implementiert die Funktionalität eines Login-Buttons.
     * <p>
     * Sie überprüft, ob die vom Benutzer eingegebenen Anmeldedaten korrekt sind.
     * <p>
     * Wenn Ja wird sie beendet und Die PatientenGUI wird aufgerufen.
     * <p>
     * Wenn Nein, wird der Nutzer über die Falsche Eingabe informiert.
     */
    public void LoginFunktion() {

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (BenutzerFeld.getText().equals(BenutzerName) && PasswortFeld.getText().equals(Passwort)) {
                    dispose();
                    new Patient();

                } else {
                    AusgabeFeld.setText("Falsche Eingabe, Versuchen Sie es erneut!");
                }

            }

        });


    }
    public void datenEinfügen(){



    }


}
