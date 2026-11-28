package gui;
import models.Album;
import services.AlbumManager;
import services.ApiProcessor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class SearchBar extends JPanel{
    private final AlbumManager manager;
    private final ApiProcessor musicBrainz;

    private JTextField searchField;
    private JButton searchButton;
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JLabel messageLabel;
    private ArrayList<Album> searchResults;
    private CollectionPanel collectionPanel;

    public SearchBar(AlbumManager manager, ApiProcessor musicBrainz, CollectionPanel collectionPanel) {
        this.manager = manager;
        this.musicBrainz = musicBrainz;
        this.collectionPanel = collectionPanel;
        setLayout(new BorderLayout(10, 10));

        makeSearchBar();
        showSearchResults();

    }

    public void makeSearchBar() {
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        JLabel title = new JLabel("search");
        searchField = new JTextField();
        searchButton = new JButton("search");

        searchButton.addActionListener(e -> {
            String req =  searchField.getText();

            if (req.isEmpty()) {
                messageLabel.setText("Enter something");
                return;
            }


            try {
                tableModel.setRowCount(0);
                searchResults = musicBrainz.searchFunc(req);
                for (int i = 0; i < searchResults.size(); i++) {
                    tableModel.addRow(new Object[]{searchResults.get(i).getName(), searchResults.get(i).getArtist(), searchResults.get(i).getYear()});

                }

                messageLabel.setText("Search results: " + searchResults.size());

            } catch (Exception a) {
                a.printStackTrace();
            }

        });

        searchField.addActionListener(e -> {
            String req =  searchField.getText();

            if (req.isEmpty()) {
                messageLabel.setText("Enter something");
                return;
            }


            try {
                tableModel.setRowCount(0);
                searchResults = musicBrainz.searchFunc(req);
                for (int i = 0; i < searchResults.size(); i++) {
                    tableModel.addRow(new Object[]{searchResults.get(i).getName(), searchResults.get(i).getArtist(), searchResults.get(i).getYear()});

                }

                messageLabel.setText("Search results: " + searchResults.size());

            } catch (Exception a) {
                a.printStackTrace();
            }

        });

        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        messageLabel = new JLabel("search");
        add(messageLabel, BorderLayout.SOUTH);

    }

    public void showSearchResults() {
        tableModel = new DefaultTableModel(new String[]{"Album", "Artist", "Year"}, 0);

        resultsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultsTable);

        JButton addButton = new JButton("add");
        addButton.addActionListener(e -> {
            int rowToMod = resultsTable.getSelectedRow();
            if (rowToMod == -1) {
                messageLabel.setText("Select an album first");
                return;
            }


            Album album = searchResults.get(rowToMod);
            boolean checks = manager.addAlbum(album);
            if (checks) {
                messageLabel.setText("Album added");
            } else {
                messageLabel.setText("Fail");
            }
            collectionPanel.refreshTable();
        });

        JPanel botPanel = new JPanel();
        botPanel.add(addButton);

        JPanel midPanel = new JPanel(new BorderLayout(0, 10));
        midPanel.add(botPanel, BorderLayout.SOUTH);
        midPanel.add(scrollPane, BorderLayout.CENTER);
        add(midPanel, BorderLayout.CENTER);
    }


}
