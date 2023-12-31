package interface_adapter.homeScreen;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomeScreenViewModel extends ViewModel {
    public final String TITLE_LABEL = "Home ScreenView";

    private HomeScreenState state = new HomeScreenState();

    public static final String LOGOUT_BUTTON_LABEL = "Log out";

    public static final String GENRE_BUTTON_LABEL = "Genre Preference";

    public static final String SEARCH_BUTTON_LABEL = "Search";

    private String loggedInUser;

    public HomeScreenViewModel() {
        super("Home Screen");
    }

    public void setState(HomeScreenState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Login Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public HomeScreenState getState() {
        return state;
    }


    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
