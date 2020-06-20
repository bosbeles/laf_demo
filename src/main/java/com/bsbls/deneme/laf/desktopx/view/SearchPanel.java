package com.bsbls.deneme.laf.desktopx.view;

import com.bsbls.deneme.laf.desktopx.action.model.ActionDictionary;
import com.bsbls.deneme.laf.desktopx.action.model.ActionWrapper;
import com.bsbls.deneme.laf.util.GuiUtil;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SearchPanel extends JPanel {


    private final ActionDictionary dictionary;
    private JTextField searchBox;
    private JTabbedPane tabbedPane;
    private Consumer<SearchPanel> onEsc;

    public SearchPanel(ActionDictionary dictionary) {
        this.dictionary = dictionary;

        initPanel();
    }

    public JTextField getSearchBox() {
        return searchBox;
    }

    private void initPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(new Color(97,97,97)));
        setPreferredSize(new Dimension(500, 300));
        //searchBox = new JTextField("Search");
        createSearchBox();


        SearchPanelUI layerUI = new SearchPanelUI(searchBox);
        tabbedPane = new JTabbedPane();
        tabbedPane.addChangeListener(e -> {
            Component selectedComponent = tabbedPane.getSelectedComponent();
            if (selectedComponent instanceof TabContentPanel) {
                ((TabContentPanel) selectedComponent).updatePanel();
            }
        });
        JLayer<JComponent> layer = new JLayer<>(tabbedPane, layerUI);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(searchBox, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy++;
        gbc.weighty = 1.0;

        this.add(layer, gbc);


        searchBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchBox.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchBox.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchBox.getText());
            }
        });


        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "clear");
        getActionMap().put("clear", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchBox.getText().isEmpty()) {
                    onEsc.accept(SearchPanel.this);
                } else {
                    searchBox.setText("");
                }

            }
        });
    }

    public void onEsc(Consumer<SearchPanel> consumer) {
        this.onEsc = consumer;
    }

    private void createSearchBox() {
        searchBox = new JTextField() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                if (getText().length() == 0) {
                    int h = getHeight();
                    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    Insets ins = getInsets();
                    FontMetrics fm = g.getFontMetrics();
                    int c0 = getBackground().getRGB();
                    int c1 = getForeground().getRGB();
                    int m = 0xfefefefe;
                    int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
                    g.setColor(new Color(c2, true));
                    g.drawString(GuiUtil.getText("search.prompt"), ins.left, h / 2 + fm.getAscent() / 2 - 2);
                }
            }
        };

        searchBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        Border outer = searchBox.getBorder();
        Border search = new IconBorder(new ImageIcon(getClass().getClassLoader().getResource("search.png")));
        searchBox.setBorder(
                new CompoundBorder(
                        outer,
                        new CompoundBorder(BorderFactory.createEmptyBorder(6, 2, 6, 6), search))
        );
    }

    private void search(String text) {
        List<ActionWrapper> searchResult = dictionary.search(text);
        System.out.println(searchResult);
        Component selectedComponent = tabbedPane.getSelectedComponent();
        if (selectedComponent instanceof TabContentPanel) {
            TabContentPanel contentPanel = (TabContentPanel) selectedComponent;
            List<String> actions = searchResult.stream().map(a -> a.getName()).collect(Collectors.toList());
            contentPanel.getMenu().getSearchMenu().setActionList(actions);
            contentPanel.updatePanel();

        }


    }

    public void createTabPanel(String name, JComponent panel) {
        tabbedPane.addTab(name, panel);
    }


    private static class IconBorder extends AbstractBorder {

        private final Icon icon;

        IconBorder(Icon icon) {

            this.icon = icon;
        }

        private static final long serialVersionUID = 1;

        @Override
        public Insets getBorderInsets(Component c,
                                      Insets insets) {
            insets.left = icon.getIconWidth() + 4;
            insets.right = insets.top = insets.bottom = 0;
            return insets;
        }

        @Override
        public void paintBorder(Component c,
                                Graphics g,
                                int x,
                                int y,
                                int width,
                                int height) {
            icon.paintIcon(c, g,
                    x, y + (height - icon.getIconHeight()) / 2);
        }

    }
}
