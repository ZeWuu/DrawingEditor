package com.zewu.drawingeditor.GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


public class DrawingPanel extends JComponent {
    private BufferedImage image;
    private int radius;
    private int xSize = 600;
    private int ySize = 600;
    private Color brushColor;

    private int prevX, prevY;

    public DrawingPanel() {
        this.addComponents();
        this.setPreferredSize(new Dimension(xSize, ySize));
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        this.setBorder(border);
        radius = 3;
        brushColor = Color.BLACK;
        image = new BufferedImage(600, 600, BufferedImage.TYPE_3BYTE_BGR);
        this.clearImage();
    }

    public void setImage(BufferedImage img) {
        this.image = img;
        repaint();
    }
    public void setBrushSize(int size) {
        this.radius = size;
    }
    public void setColor(Color color) {
        this.brushColor = color;
    }

    public BufferedImage getImage() {
        return image;
    }

    private void brush(int x, int y) {
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(brushColor);
        g2d.setStroke(new BasicStroke(radius * 2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(prevX, prevY, x, y);
        g2d.dispose();
        prevX = x;
        prevY = y;
    }

    private void clearImage() {
        for (int w = 0; w < xSize; w++) {
            for (int h = 0; h < ySize; h++) {
                image.setRGB(h, w, 0xffffff);
            }
        }
    }

    private void addComponents() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    prevX = e.getX();
                    prevY = e.getY();
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    brush(e.getX(), e.getY());
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Double buffering
        BufferedImage bufferImage = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bufferGraphics = bufferImage.createGraphics();
        bufferGraphics.drawImage(image, 0, 0, this);
        g.drawImage(bufferImage, 0, 0, this);
        bufferGraphics.dispose();
    }
}