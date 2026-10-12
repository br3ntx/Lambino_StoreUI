import javax.swing.*;
import java.awt.*;

public class PokeMart {

    int balance = 3000;   
    int inBag = 0;         
    Item selected;        

    public PokeMart() {


        JFrame frame = new JFrame("Poke Mart");
        frame.setSize(480, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Color menuBg   = new Color(248, 248, 240);
        Color menuBlue = new Color(72, 96, 168);
        Color menuText = new Color(40, 40, 40);
        Font  menuFont = new Font("Monospaced", Font.BOLD, 14);
        
        Item[] items = {
            new Item("Great Ball", 600, "A good, high-performance Ball..."),
            new Item("Ultra Ball", 800, "An ultra-high-performance Ball..."),
            new Item("Potion", 200, "Restores 20 HP to a Pokémon."),
            new Item("Super Potion", 700, "Restores 60 HP to a single Pokémon."),
            new Item("Hyper Potion", 1500, "Restores 200 HP to a single Pokémon."),
            new Item("Max Potion", 2500, "Fully restores the HP of a single Pokémon."),
            new Item("Full Restore", 3000, "Fully restores the HP and heals any status problems of a single Pokémon."),
        };


        JLabel moneyLabel = new JLabel("Money: ₽" + balance);
        JLabel bagLabel = new JLabel("In Bag: " + inBag);
        JPanel moneyPanel = new JPanel();
        moneyPanel.add(moneyLabel);
        moneyPanel.add(bagLabel);
        moneyPanel.setBackground(menuBg);
        moneyPanel.setBorder(BorderFactory.createLineBorder(menuBlue, 3));
        moneyLabel.setFont(menuFont);
        moneyLabel.setForeground(menuText);
        bagLabel.setFont(menuFont);
        bagLabel.setForeground(menuText);
        frame.add(moneyPanel, BorderLayout.NORTH);


        JTextArea description = new JTextArea(" ", 3, 30);
        description.setEditable(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setBackground(menuBg);
        description.setForeground(menuText);
        description.setFont(menuFont);

        
        JPanel itemPanel = new JPanel(new GridLayout(0, 1));
        for (Item item : items) {
            JButton button = new JButton(item.name + " ₽" + item.price);
            button.setFont(menuFont);        
            button.addActionListener(e -> {
                description.setText(item.description);
                selected = item;
                bagLabel.setText("In Bag: " + item.owned);
            });
            itemPanel.add(button);
        }
        JScrollPane scrollPane = new JScrollPane(itemPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane, BorderLayout.CENTER);

        
        JButton buyButton = new JButton("Buy");
        buyButton.setFont(menuFont);
        buyButton.setBackground(menuBlue);
        buyButton.setForeground(Color.WHITE);
        buyButton.addActionListener(e -> {
            if (selected == null) {
                description.setText("Choose an item first!");
            } else if (balance >= selected.price) {
                balance -= selected.price;
                moneyLabel.setText("Money: ₽" + balance);
                selected.owned++;
                bagLabel.setText("In Bag: " + selected.owned);
                description.setText("Bought " + selected.name + "!");
            } else {
                description.setText("Not enough money for " + selected.name + ".");
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(new JScrollPane(description), BorderLayout.CENTER);
        bottomPanel.add(buyButton, BorderLayout.EAST);
        bottomPanel.setBorder(BorderFactory.createLineBorder(menuBlue, 3));
        frame.add(bottomPanel, BorderLayout.SOUTH);


        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new PokeMart();
    }
}