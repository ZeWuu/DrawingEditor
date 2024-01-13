package com.zewu.drawingeditor.SpecialEffectButtons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import com.zewu.drawingeditor.GUI.DrawingPanel;

public class ColorChangeButton extends JButton {
    private DrawingPanel dp;

    public ColorChangeButton(DrawingPanel dp) {
        this.setPreferredSize(new Dimension(125, 30));
        this.dp = dp;
        this.addButton();
        this.setText("Odwroc kolory");

    }

    private void addButton() {
        this.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new Thread(() -> {
                    synchronized (dp) {
                        BufferedImage originalImage = dp.getImage();
                        for (int x = 0; x < originalImage.getWidth(); x++)
                            for (int y = 0; y < originalImage.getHeight(); y++) {
                                int rgb = originalImage.getRGB(x, y);
                                Color c = new Color(rgb);
                                Color inverted = new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
                                originalImage.setRGB(x, y, inverted.getRGB());
                            }
                        dp.setImage(originalImage);
                    }
                });

                thread.start();

            }

        });

    }

}
