package com.zewu.drawingeditor.WindowFrame;

import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Window window = new Window();
                window.createWindow();
            }
        });
    }
    
}