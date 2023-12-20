package io.github.kh4f;

import java.awt.*;

public class App {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new GUI().setVisible(true);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });
    }

}
