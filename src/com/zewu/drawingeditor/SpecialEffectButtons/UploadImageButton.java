package com.zewu.drawingeditor.SpecialEffectButtons;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.zewu.drawingeditor.GUI.DrawingPanel;

public class UploadImageButton extends JButton {
    DrawingPanel dp;

    public UploadImageButton(DrawingPanel dp) {
        this.addbutton();
        this.dp = dp;
        this.setPreferredSize(new Dimension(125, 30));
        this.setText("Wczytaj obraz");
    }

    private void addbutton() {

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(UploadImageButton.this);

                if (result == JFileChooser.APPROVE_OPTION) {

                    File selectedFile = fileChooser.getSelectedFile();

                    Thread thread = new Thread(() -> {
                        synchronized (dp) {
                            try {

                                BufferedImage image = ImageIO.read(selectedFile);
                                BufferedImage newImg = new BufferedImage(600, 600, BufferedImage.TYPE_3BYTE_BGR);
                                Graphics2D g2d = newImg.createGraphics();
                                g2d.drawImage(image.getScaledInstance(600, 600, Image.SCALE_FAST), 0, 0, null);
                                g2d.dispose();

                                dp.setImage(newImg);

                            } catch (IOException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(UploadImageButton.this, "Blad podczas wczytywania obrazu",
                                        "Blad",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    thread.start();
                }
            }

        });
    }
}
