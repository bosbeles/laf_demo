package com.bsbls.deneme.laf.util;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ResourceBundle;

public class GuiUtil {


    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("actions");

    public static String getText(String key) {
        if (resourceBundle.containsKey(key)) {
            return resourceBundle.getString(key);
        } else return key;
    }

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
