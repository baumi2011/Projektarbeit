import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/projektarbeit";
    private static final String USER = "root";
    private static final String PASSWORD = "janhesch501";

    public static void main(String[] args) {
    Connection con = null;
    try {
        con = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connected to database");

    }catch (SQLException e) {
        throw new RuntimeException(e);
    }



    SwingUtilities.invokeLater(() -> {
        Patient GUI = new Patient();
        GUI.setVisible(true);
    });
    }




    }