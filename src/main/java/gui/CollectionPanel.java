package gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import models.Album;
import services.AlbumManager;



public class CollectionPanel extends JPanel{
    private JTable table;
    private DefaultTableModel tableModel;
    private AlbumManager manager;

    public CollectionPanel(AlbumManager manager) {
        this.manager = manager;
            setLayout(new BorderLayout(10, 10));
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel title = new JLabel("My Collection");

            add(title, BorderLayout.NORTH);

            createTable();

    }

    public void createTable() {
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


        table.getColumnModel().getColumn(4)
                .setCellEditor(new DefaultCellEditor(statusBox));

        tableModel.addTableModelListener(e -> {
            if (e.getType() != javax.swing.event.TableModelEvent.UPDATE) {
                return;
            }

            int row = e.getFirstRow();
            int column = e.getColumn();

            if (row < 0 || row >= manager.getAlbums().size()) {
                return;
            }

            Album album = manager.getAlbums().get(row);

            if (column == 3) {
                int rating = Integer.parseInt(
                        tableModel.getValueAt(row, 3).toString()
                );

                manager.updateRating(album.getId(), rating);

            } else if (column == 4) {
                String status = tableModel.getValueAt(row, 4).toString();
                manager.updateUserStat(album.getId(), status);
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton removeButton = new JButton("Remove Selected Album");
        removeButton.addActionListener(e -> removeSelectedAlbum());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(removeButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void removeSelectedAlbum() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        Album album = manager.getAlbums().get(selectedRow);

        manager.removeAlbum(album.getId());
    }

}
