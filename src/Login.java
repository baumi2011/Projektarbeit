import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

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

    // Erstellen einer HashMap für Benutzername und Passwort
    private Map<String, String> benutzerMap = new HashMap<>();

    /**
     * Konstruktor für die `Login`-Klasse.
     * <p>
     * Dieser Konstruktor initialisiert das Login-Fenster, macht es sichtbar und
     * ruft die Methode {@link #LoginFunktion()} auf, um die Funktionalität des
     * Login-Buttons zu aktivieren. Außerdem werden Beispiel-Benutzernamen und
     * zugehörige Passwörter in einer HashMap gespeichert, um die Benutzeranmeldung
     * zu simulieren.
     * <p>
     * Folgende Beispiel-Benutzernamen und Passwörter werden der HashMap hinzugefügt:
     *   <li>"root" -> "1234"</li>
     *   <li>"Julian" -> "Baumgartner"</li>
     *   <li>"Jan" -> "Hesch"</li>
     */
    Login() {
        // Füge Beispiel-Benutzer zu der HashMap hinzu
        benutzerMap.put("root", "1234");
        benutzerMap.put("Julian", "Baumgartner");
        benutzerMap.put("Jan", "Hesch");

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        // Setze die gewünschte Fenstergröße
        setSize(600, 150);
        setVisible(true);
        AusgabeFeld.setEditable(false);  // Verhindere das Bearbeiten des AusgabeFelds
        LoginFunktion();
    }

    /**
     * Die Methode LoginFunktion implementiert die Funktionalität eines Login-Buttons.
     * <p>
     * Sie überprüft, ob die vom Benutzer eingegebenen Anmeldedaten korrekt sind.
     * <p>
     * Wenn Ja wird sie beendet und die PatientenGUI wird aufgerufen.
     * <p>
     * Wenn Nein, wird der Nutzer über die falsche Eingabe informiert.
     */
    public void LoginFunktion() {

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String benutzername = BenutzerFeld.getText();
                String passwort = new String(PasswortFeld.getPassword()); // Passwort-Feld gibt char[] zurück

                // Überprüfe, ob der Benutzername existiert und das Passwort übereinstimmt
                if (benutzerMap.containsKey(benutzername) && benutzerMap.get(benutzername).equals(passwort)) {
                    dispose(); // Schließe das Login-Fenster
                    new Patient(); // Öffne die Patienten-GUI
                } else {
                    AusgabeFeld.setText("Falsche Eingabe, Versuchen Sie es erneut!");
                }
            }
        });
    }
}