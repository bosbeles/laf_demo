package com.bsbls.deneme.laf.desktopx.view;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SearchPanelUI extends LayerUI<JComponent> {

    private final JTextField textField;

    public SearchPanelUI(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        JLayer jlayer = (JLayer) c;
        jlayer.setLayerEventMask(
                AWTEvent.KEY_EVENT_MASK
        );
    }

    @Override
    public void uninstallUI(JComponent c) {
        JLayer jlayer = (JLayer) c;
        jlayer.setLayerEventMask(0);
        super.uninstallUI(c);
    }

    @Override
    protected void processKeyEvent(KeyEvent e, JLayer<? extends JComponent> l) {


        super.processKeyEvent(e, l);
        if((e.getID() == KeyEvent.KEY_TYPED && e.getKeyChar() == '\t' ) || e.getKeyCode() == KeyEvent.VK_TAB) {
            return;
        }



        try {

            int caretPosition = textField.getCaretPosition();
            if (e.getID() == KeyEvent.KEY_TYPED) {

                char ch = e.getKeyChar();
                if (isPrintableChar(ch)) {
                    System.out.println(ch + " " + ((int) ch) + " " + Character.getNumericValue(ch));
                    textField.getDocument().insertString(caretPosition, ch + "", null);
                }


            } else if (e.getID() == KeyEvent.KEY_PRESSED) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (caretPosition >= 1) {
                        textField.getDocument().remove(caretPosition - 1, 1);
                    }

                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    textField.setCaretPosition(Math.max(0, caretPosition - 1));
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    textField.setCaretPosition(Math.min(textField.getDocument().getLength(), caretPosition+1));
                }
            }
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }

        textField.grabFocus();

    }

    private boolean isPrintableChar( char c ) {
        Character.UnicodeBlock block = Character.UnicodeBlock.of( c );
        return (!Character.isISOControl(c)) &&
                c != KeyEvent.CHAR_UNDEFINED &&
                block != null &&
                block != Character.UnicodeBlock.SPECIALS;
    }
}
