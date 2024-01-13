package com.zewu.drawingeditor.SpecialEffectButtons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import com.zewu.drawingeditor.GUI.DrawingPanel;

public class BlurButton extends JButton {
    DrawingPanel dp;

    public BlurButton(DrawingPanel dp) {
        this.setPreferredSize(new Dimension(125, 30));
        this.dp = dp;
        this.addButton();
        this.setText("Zabluruj obraz");

    }

    private void addButton() {
        this.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new Thread(() -> {
                    synchronized (dp) {
                        BufferedImage originalImage = dp.getImage();
                        BufferedImage newImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(),
                                BufferedImage.TYPE_3BYTE_BGR);
                        Color color[];
                        int sumR = 0;
                        int sumG = 0;
                        int sumB = 0;
                        int i = 0;
                        int max = 400;
                        int radius = 10;
                        color = new Color[max];

                        for (int x = radius; x < originalImage.getHeight() - radius; x++) {
                            for (int y = radius; y < originalImage.getWidth() - radius; y++) {
                                for (int x1 = x - radius; x1 < x + radius; x1++) {
                                    for (int y1 = y - radius; y1 < y + radius; y1++) {
                                        color[i++] = new Color(originalImage.getRGB(y1, x1));
                                    }
                                }
                                i = 0;

                                for (int z = 0; z < max; z++) {
                                    sumR = sumR + color[z].getRed();
                                }
                                sumR = sumR / (max);
                                for (int z = 0; z < max; z++) {
                                    sumG = sumG + color[z].getGreen();
                                }
                                sumG = sumG / (max);
                                for (int z = 0; z < max; z++) {
                                    sumB = sumB + color[z].getBlue();
                                }
                                sumB = sumB / (max);
                                int sum1 = (sumR << 16) + (sumG << 8) + sumB;
                                newImage.setRGB(y, x, (int) (sum1));
                            }
                        }
                        
                        dp.setImage(newImage);
                    }
                });

                thread.start();

            }

        });

    }
}
