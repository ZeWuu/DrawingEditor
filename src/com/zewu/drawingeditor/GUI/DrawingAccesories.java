package com.zewu.drawingeditor.GUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class DrawingAccesories extends JPanel {

    private DrawingPanel dp;
    private int xSize = 200;
    private int ySize = 600;

    public DrawingAccesories(DrawingPanel dp){
        this.setPreferredSize(new Dimension(xSize,ySize));
        this.setLayout(new GridLayout(6,2));
        this.dp = dp;
        this.addComponents();
   
    }
    private void addComponents(){
        addColorButton(Color.BLACK);
        addColorButton(Color.RED);
        addColorButton(Color.YELLOW);
        addColorButton(Color.GREEN);
        addColorButton(Color.BLUE);
        addColorButton(Color.WHITE);

        addSizeButton(3);
        addSizeButton(4);
        addSizeButton(6);
        addSizeButton(8);
        addSizeButton(10);
        addSizeButton(12);

    }

    private void addColorButton(Color color){
        Button button = new Button();
        button.setBackground(color);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dp.setColor(color);
            }
            
        });
        this.add(button);
    }

    private void addSizeButton(int size){
        Button button = new Button(""+size);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dp.setBrushSize(size);
            }
            
        });
        this.add(button);
    }
}
