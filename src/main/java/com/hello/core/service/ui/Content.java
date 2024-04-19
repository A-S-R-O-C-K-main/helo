package com.hello.core.service.ui;

import java.io.IOException;

import com.hello.App;
import com.hello.layout.MainController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Content {
    public static FXMLLoader load(String uri) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("layout/" + uri + ".fxml"));
        return loader;
    }

    public static void load_MAIN(App app) throws IOException {
        FXMLLoader loader = load("MAIN");
        Parent container = loader.load();
        Scene scene = new Scene(container);
        app.getApplicationStage().setScene(scene);
        app.getApplicationStage().show();
        MainController controller = loader.getController();
        controller.load(app);
    }
}
