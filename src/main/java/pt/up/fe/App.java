package pt.up.fe;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

  private static Scene scene;
  private static Stage stage;


  public static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }


  @Override
  public void start(Stage stage) throws Exception
  {
    stage.initStyle(StageStyle.UNDECORATED);
    setPrimaryStage(stage);
    setPrimaryScene(scene);
    Parent root = loadFXML("fxml/alwaysdisplayed/main");

    scene = new Scene(root);

    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args)
  {
    launch(args);
  }

  private void setPrimaryStage(Stage stage)
  {
    App.stage = stage;
  }

  public static Stage getMainStage()
  {
    return App.stage;
  }

  private void setPrimaryScene(Scene scene)
  {
    App.scene = scene;
  }

  public static Scene getMainScene()
  {
    return App.scene;
  }



}
