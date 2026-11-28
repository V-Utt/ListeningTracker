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

    }

    public void showSearchResults() {

    }


}
