package com.zewu.drawingeditor.WindowFrame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;

import com.zewu.drawingeditor.GUI.Buttons;
import com.zewu.drawingeditor.GUI.DrawingAccesories;
import com.zewu.drawingeditor.GUI.DrawingPanel;

public class Window extends JFrame {

    public void createWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Drawing Editor");
        this.setResizable(false);
        this.setPreferredSize(new Dimension(900, 800));

        LayoutManager layout = new FlowLayout();
        this.setLayout(layout);
        DrawingPanel dp = new DrawingPanel();
        Buttons buttons = new Buttons(dp);
        DrawingAccesories accesories = new DrawingAccesories(dp);
        this.add(dp);
        this.add(accesories);
        this.add(buttons);
        

        this.pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

}
