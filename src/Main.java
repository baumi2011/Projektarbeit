import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

    SwingUtilities.invokeLater(() -> {
        Patient GUI = new Patient();
        GUI.setVisible(true);
    });
    }




    }