package gui;
import javax.swing.*;
import java.awt.*;


public class CollectionPanel {
    public CollectionPanel() {
        SwingUtilities.invokeLater(() -> {
            setLayout(new BorderLayout(10, 10));
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel title = new JLabel("My Collection");

            add(title, BorderLayout.NORTH);
        });
    }
}
