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

    public SearchBar(AlbumManager manager, ApiProcessor musicBrainz) {
        this.manager = manager;
        this.musicBrainz = musicBrainz;
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
        tableModel = new DefaultTableModel(searchResults.size(), 0);

        JButton addButton = new JButton("add");
        addButton.addActionListener(e -> {

        });

        resultsTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(resultsTable);

        JPanel botPanel = new JPanel();
        botPanel.add(addButton);

        JPanel midPanel = new JPanel(new BorderLayout(0, 10));
        midPanel.add(botPanel, BorderLayout.SOUTH);
        add(midPanel, BorderLayout.CENTER);
    }


}
