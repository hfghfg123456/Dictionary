package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.imageio.IIOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Graphic/Dicfx.fxml"));
        primaryStage.setTitle("Dictionary");
        Scene scene = new Scene(root);
        Image image=new Image("/image/images-removebg-preview.png");
        primaryStage.getIcons().add(image);
        String css = Main.class.getResource("../Graphic/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws IIOException {
        launch(args);
    }
}
