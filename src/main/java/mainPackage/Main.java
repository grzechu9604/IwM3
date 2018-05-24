package mainPackage;

import connection.HapiService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IwM3");
        primaryStage.show();

        HapiService hs = new HapiService();
        hs.getFirstResponse();
    }
}
