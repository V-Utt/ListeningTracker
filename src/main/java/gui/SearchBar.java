package gui;
import models.Album;
import services.AlbumManager;
import services.ApiProcessor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchBar extends JPanel{
    private final AlbumManager manager;
    private final ApiProcessor musicBrainz;
    private final Runnable refresh;

    private JTextField searchField;
    private JButton searchButton;
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JLabel messageLabel;
    private List<Album> searchResults;

    public SearchBar(AlbumManager manager, ApiProcessor musicBrainz, Runnable refresh) {
        this.manager = manager;
        this.musicBrainz = musicBrainz;
        this.refresh = refresh;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    }


}
