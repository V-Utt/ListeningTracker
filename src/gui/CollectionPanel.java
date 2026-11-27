package gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class CollectionPanel {
    public CollectionPanel() {
        SwingUtilities.invokeLater(() -> {
            setLayout(new BorderLayout(10, 10));
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel title = new JLabel("My Collection");

            add(title, BorderLayout.NORTH);

            createTable();
        });
    }

    private void createTable() {
        tableModel = new DefaultTableModel(
                new String[]{"Album", "Artist", "Year", "Rating", "Status"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3 || column == 4;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(30);

        JComboBox<Integer> ratingBox = new JComboBox<>();
        for (int i = 0; i <= 10; i++) {
            ratingBox.addItem(i);
        }

        table.getColumnModel().getColumn(3)
                .setCellEditor(new DefaultCellEditor(ratingBox));

        JComboBox<String> statusBox = new JComboBox<>(
                new String[]{
                        "Plan to listen",
                        "Listening",
                        "Listened"
                }
        );
    }
}
