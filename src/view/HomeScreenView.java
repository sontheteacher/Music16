package view;

import interface_adapter.GenrePreference.GenreController;
import interface_adapter.SearchArtist.SearchArtistController;
import interface_adapter.homeScreen.HomeScreenState;
import interface_adapter.homeScreen.HomeScreenViewModel;
import interface_adapter.SearchTrack.SearchTrackController;
import interface_adapter.homeScreen.LogoutController;
//import interface_adapter.SearchArtist.SearchArtistController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Objects;

public class HomeScreenView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Home Screen";
    private final HomeScreenViewModel homeScreenViewModel;

    // All the controllers
    private final SearchTrackController searchTrackController;

    private final SearchArtistController searchArtistController;

    private final LogoutController logoutController;

    private final GenreController genreController;
    private final JLabel username;

    final JButton logOutButton;
    private JTextField searchInputField;
    private JComboBox<String> searchTypeDropdown;
    private JTextArea outputArea;
    private JButton searchButton;
    private JButton genrePreferenceButton;

    /**
     * A window with a title and a JButton.
     */

    public HomeScreenView(HomeScreenViewModel homeScreenViewModel, SearchTrackController searchTrackController,
                          SearchArtistController searchArtistController, LogoutController logoutController,
                          GenreController genreController) {
        this.homeScreenViewModel = homeScreenViewModel;
        this.genreController = genreController;
        this.homeScreenViewModel.addPropertyChangeListener(this);
        this.searchTrackController = searchTrackController;
        this.searchArtistController = searchArtistController;
        this.logoutController = logoutController;

        JLabel title = new JLabel("Home Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();
        logOutButton = new JButton(homeScreenViewModel.LOGOUT_BUTTON_LABEL);
        genrePreferenceButton = new JButton("Genre Preference");

        // Initialize components
        searchInputField = new JTextField(20);
        searchTypeDropdown = new JComboBox<>(new String[]{"Search Tracks", "Search Artists"});
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        searchButton = new JButton("Search");
       

        // LOGOUT BUTTON
        logOutButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (Objects.equals(evt.getActionCommand(), "Log out")) {
                            logoutController.execute();
                        }
                    }
                }
        );

        genrePreferenceButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (Objects.equals(evt.getActionCommand(), "Genre Preference")) {
                            genreController.switchView();
                        }
                    }
                }
        );

        // Layout
        this.setLayout(new FlowLayout());
        this.add(searchInputField);
        this.add(searchTypeDropdown);
        this.add(searchButton);
        this.add(new JScrollPane(outputArea));
        this.add(logOutButton);
        this.add(genrePreferenceButton);


        // Action listeners
        searchButton.addActionListener(e -> {
            try {
                performSearch();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        this.setVisible(true);
    }
        // GENRE PREFERENCE BUTTON
        //genrePreferenceButton.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (Objects.equals(evt.getActionCommand(), "Genre Preference")) {
//                            HomeScreenView.this.genreController.homeButton();
//                        }
//                    }
//                }
//        );
//    }

    private void performSearch() throws IOException {
        String query = searchInputField.getText();
        String searchType = (String) searchTypeDropdown.getSelectedItem();
        if (searchType.equals("Search Tracks")){
            searchTrackController.execute(query);
        }
        if (searchType.equals("Search Artists")){
            searchArtistController.execute(query);
        }
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HomeScreenState state = (HomeScreenState) evt.getNewValue();
        username.setText(state.getUsername());
        if ("searchTracks".equals(evt.getPropertyName())) {
//            outputArea.setText("");
            outputArea.setText(state.getOutput());
        }

        if ("searchArtists".equals(evt.getPropertyName())) {
            outputArea.setText(state.getOutput());
        }
    }
}