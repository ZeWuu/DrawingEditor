package com.zewu.drawingeditor.GUI;

import java.awt.Dimension;
import javax.swing.JPanel;

import com.zewu.drawingeditor.SpecialEffectButtons.BlurButton;
import com.zewu.drawingeditor.SpecialEffectButtons.ColorChangeButton;
import com.zewu.drawingeditor.SpecialEffectButtons.UploadImageButton;

public class Buttons extends JPanel {

    private DrawingPanel dp;

    public Buttons(DrawingPanel dp) {
        this.dp = dp;
        this.addbutton();
        this.setPreferredSize(new Dimension(600, 100));
    }
    public void addbutton(){
        UploadImageButton imageButton = new UploadImageButton(dp);
        this.add(imageButton);
        ColorChangeButton colorButton = new ColorChangeButton(dp);
        this.add(colorButton);
        BlurButton blurButton = new BlurButton(dp);
        this.add(blurButton);
    }

}
