import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PokeMart {

    int balance = 3000;
    Item selected;
    JButton selectedButton;

    public PokeMart() {

        Color bgBlue     = new Color(216, 232, 240);
        Color boxWhite   = new Color(248, 248, 248);
        Color borderBlue = new Color(48, 96, 168);
        Color descGreen  = new Color(200, 224, 160);
        Color descBorder = new Color(96, 152, 72);
        Color textDark   = new Color(40, 48, 72);
        Color selectRed  = new Color(216, 48, 48);
        Font  titleFont  = new Font("SansSerif", Font.BOLD, 12);
        Font  bodyFont   = new Font("SansSerif", Font.BOLD, 14);

        Border rowNormal   = BorderFactory.createEmptyBorder(7, 12, 7, 12);
        Border rowSelected = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(selectRed, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JFrame frame = new JFrame("Poké Mart");
        frame.setSize(560, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("images/POKEMON.png");
        frame.setIconImage(icon.getImage());

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        root.setBackground(bgBlue);
        frame.setContentPane(root);

        
        Item[] items = {
            new Item("Poké Ball", 200, "A ball thrown to catch a wild Pokémon.", "pokeball.png"),
            new Item("Great Ball", 600, "A good, high-performance Poké Ball.", "greatball.png"),
            new Item("Ultra Ball", 800, "An ultra-high-performance Poké Ball.", "ultraball.png"),
            new Item("Potion", 200, "Restores 20 HP to a single Pokémon.", "potion.jpeg"),
            new Item("Super Potion", 700, "A spray-type medicine. Restores 60 HP to a single Pokémon.", "super potion.png"),
            new Item("Hyper Potion", 1500, "Restores 200 HP to a single Pokémon.", "hyper potion.png"),
            new Item("Max Potion", 2500, "Fully restores the HP of a single Pokémon.", "max potion.png"),
            new Item("Full Restore", 3000, "Fully restores the HP and heals any status problem of a single Pokémon.", "full restore.png"),
        };

        
        JLabel moneyValue = new JLabel("₽" + String.format("%,d", balance), SwingConstants.RIGHT);
        JPanel moneyBox = titledBox("MONEY", moneyValue, boxWhite, borderBlue, textDark, titleFont, bodyFont);

        JLabel bagValue = new JLabel("0", SwingConstants.RIGHT);
        JPanel bagBox = titledBox("IN BAG", bagValue, boxWhite, borderBlue, textDark, titleFont, bodyFont);

        JPanel leftColumn = new JPanel(new BorderLayout(0, 10));
        leftColumn.setOpaque(false);
        leftColumn.setPreferredSize(new Dimension(160, 0));
        leftColumn.add(moneyBox, BorderLayout.NORTH);
        leftColumn.add(bagBox, BorderLayout.SOUTH);

        
        JTextArea description = new JTextArea(
                "Welcome! Select an item to see what it does.", 3, 20);
        description.setEditable(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setBackground(descGreen);
        description.setForeground(textDark);
        description.setFont(bodyFont);
        description.setMargin(new Insets(8, 10, 8, 10));

        JLabel pic = new JLabel();
        pic.setPreferredSize(new Dimension(84, 84));
        pic.setHorizontalAlignment(SwingConstants.CENTER);
        pic.setBackground(boxWhite);
        pic.setOpaque(true);
        pic.setBorder(BorderFactory.createLineBorder(descBorder, 2));

        JPanel itemPanel = new JPanel(new GridLayout(0, 1, 0, 2));
        itemPanel.setBackground(boxWhite);
        for (Item item : items) {
            JButton button = new JButton(String.format(
                "<html><table width='260' cellpadding='0'>"
                + "<tr><td align='left'>%s</td>"
                + "<td align='right'>₽%,d</td></tr></table></html>",
                item.name, item.price));
            button.setFont(bodyFont);
            button.setForeground(textDark);
            button.setBackground(boxWhite);
            button.setFocusPainted(false);
            button.setOpaque(true);
            button.setContentAreaFilled(false);
            button.setBorder(rowNormal);

            button.addActionListener(e -> {
                selected = item;
                description.setText(item.description);
                bagValue.setText(String.valueOf(item.owned));
                pic.setIcon(loadIcon(item.image, 72));   
                if (selectedButton != null) selectedButton.setBorder(rowNormal);
                selectedButton = button;
                selectedButton.setBorder(rowSelected);
            });
            itemPanel.add(button);
        }

        JScrollPane scrollPane = new JScrollPane(itemPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(borderBlue, 3));
        scrollPane.getViewport().setBackground(boxWhite);

        JPanel midPanel = new JPanel(new BorderLayout(10, 0));
        midPanel.setOpaque(false);
        midPanel.add(leftColumn, BorderLayout.WEST);
        midPanel.add(scrollPane, BorderLayout.CENTER);
        root.add(midPanel, BorderLayout.CENTER);

    
        JButton buyButton = new JButton("BUY");
        buyButton.setFont(bodyFont);
        buyButton.setForeground(Color.WHITE);
        buyButton.setBackground(borderBlue);
        buyButton.setFocusPainted(false);
        buyButton.setBorder(BorderFactory.createEmptyBorder(8, 22, 8, 22));

        buyButton.addActionListener(e -> {
            if (selected == null) {
                description.setText("Choose an item first!");
            } else if (balance >= selected.price) {
                balance -= selected.price;
                selected.owned++;
                moneyValue.setText("₽" + String.format("%,d", balance));
                bagValue.setText(String.valueOf(selected.owned));
                description.setText("Bought " + selected.name + "!  (×" + selected.owned + ")");
            } else {
                description.setText("You don't have enough money for " + selected.name + ".");
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout(8, 0));
        bottomPanel.setOpaque(false);
        bottomPanel.add(pic, BorderLayout.WEST);
        bottomPanel.add(description, BorderLayout.CENTER);
        bottomPanel.add(buyButton, BorderLayout.EAST);
        bottomPanel.setBorder(BorderFactory.createLineBorder(descBorder, 3));
        root.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    
    private ImageIcon loadIcon(String file, int size) {
        if (file == null) return null;
        java.io.File f = new java.io.File("images/" + file);
        if (!f.exists()) return null;
        Image img = new ImageIcon(f.getPath()).getImage()
                        .getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    private JPanel titledBox(String title, JLabel value, Color bg, Color border,
                             Color text, Font titleFont, Font valueFont) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(border);

        value.setFont(valueFont);
        value.setForeground(text);

        JPanel box = new JPanel(new BorderLayout());
        box.setBackground(bg);
        box.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(border, 3),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        box.add(titleLabel, BorderLayout.NORTH);
        box.add(value, BorderLayout.SOUTH);
        return box;
    }

    public static void main(String[] args) {
        new PokeMart();
    }
}