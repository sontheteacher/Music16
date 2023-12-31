package interface_adapter.homeScreen;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.logout.LogoutOutputBoundary;

import java.util.Objects;

class LogoutPresenterTest {

    LoginViewModel loginModel;
    HomeScreenViewModel homeScreenModel;
    ViewManagerModel managerModel;
    LogoutOutputBoundary logoutPresenter;

    @BeforeEach
    void setUp() {
        loginModel = new LoginViewModel();
        LoginState exampleLoginState = loginModel.getState();
        exampleLoginState.setUsername("Albert");
        exampleLoginState.setPassword("123456");
        loginModel.setState(exampleLoginState);
        homeScreenModel = new HomeScreenViewModel();
        HomeScreenState exampleHomeScreenState = homeScreenModel.getState();
        exampleHomeScreenState.setUsername("Albert");
        homeScreenModel.setState(exampleHomeScreenState);
        managerModel = new ViewManagerModel();
        logoutPresenter = new LogoutPresenter(loginModel, homeScreenModel, managerModel);
    }

    @Test
    void testSuccess() {
        logoutPresenter.prepareSuccessView();
        assert Objects.equals(managerModel.getActiveView(), "log in");
        assert homeScreenModel.getState().getUsername().isEmpty();
        assert Objects.equals(loginModel.getState().getUsername(), "Albert");
        assert loginModel.getState().getPassword().isEmpty();
    }
}
