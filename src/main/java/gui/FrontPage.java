package gui;

import services.AlbumManager;
import services.ApiProcessor;

import javax.swing.*;
import java.awt.*;

public class FrontPage extends JFrame {
    private final AlbumManager manager;
    private final CollectionPanel collectionPanel;

    public FrontPage() {
        this.manager = new AlbumManager();
        ApiProcessor musicBrainz = new ApiProcessor();

        setTitle("Listening Tracker");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.collectionPanel = new CollectionPanel(manager);

        SearchBar search = new SearchBar(manager, musicBrainz, this.collectionPanel);

        setLayout(new BorderLayout(10, 10));

        add(search, BorderLayout.NORTH);
        add(collectionPanel, BorderLayout.CENTER);

    }
}
