package com.bsbls.deneme.laf.util;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GuiUtil {

    public static JPanel transparentPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        return panel;
    }

    public static BufferedImage getImage(String img) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(GuiUtil.class.getClassLoader().getResource(img));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bi;
    }

}
