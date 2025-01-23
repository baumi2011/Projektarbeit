import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);
        LoginFunktion();

    }

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
