package interface_adapter.GenrePreference;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GenreViewModel extends ViewModel {
    public final String TITLE_LABEL = "Genre Preference View";
    public final String GENRE_LABEL =
            "Enter your preferred genre, or the genre you want to delete from your preferences";

    public static final String ADD_BUTTON_LABEL = "Add";
    public static final String DELETE_BUTTON_LABEL = "Delete";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";


    private GenreState state = new GenreState();

    public GenreViewModel() {
        super("genre preference");
    }

    public void setState(GenreState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public GenreState getState() {
        return state;
    }
}