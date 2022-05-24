package com.company;

import javax.swing.*;
import java.awt.*;

import static com.company.DrawGraph.createAndShowGui;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }

}
