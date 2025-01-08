import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
boolean login = false;
        while(!login) {
            SwingUtilities.invokeLater(() -> {
                Login GUI = new Login();
                GUI.setVisible(true);
            });

        }


        SwingUtilities.invokeLater(() -> {
            Patient GUI = new Patient();
            GUI.setVisible(true);
        });



    }




    }