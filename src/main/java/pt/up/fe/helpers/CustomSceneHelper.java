package pt.up.fe.helpers;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import pt.up.fe.Main;
import pt.up.fe.controllers.alwaysdisplayed.ContentAreaPaneController;


public class CustomSceneHelper {

  private static Scene scene;

  public static ContentAreaPaneController contentAreaPaneController;

  public static void setMainScene() {
    scene = Main.getMainScene();
  }

  //The source argument uses event.getSource(), which returns in the following format: Button[id=homeButton, styleClass=button leftPaneButton]'Home'
  //This method splits the string twice to get the id value. Can look into using regex instead, but might not be as readable.
  public static String getSourceID(Object source) {
    return source.toString().split(",")[0].split("=")[1];
  }

  public static Node getNodeById(String nodeId) {
    setMainScene();
    if (nodeId.split("")[0].equals("#")) {
      return scene.lookup(nodeId);
    } else {
      return scene.lookup("#" + nodeId);
    }
  }

  public static Label getLabelById(String labelName) {
    setMainScene();
    return (Label) scene.lookup("#" + labelName);
  }

  public static String getSourceName(Object source) {
    //Output of source is Button[id=logsButton, styleClass=button leftPaneButton]'Logs' so splitting by single quote gets the name of the object.
    return source.toString().split("'")[1];
  }

  //Changes the name displayed in the top menu bar (first label on the left).
  public static void changeLabelName(String labelName, String newText) {
    setMainScene();
    Label label = getLabelById(labelName);
    label.setText(newText);
  }

  /**
   * Example use case for this function, when a button is clicked, you can do this: String
   * buttonName = sceneHelper.getSourceName(event.getSource()); //buttonName could be "Home" (the
   * value it shows to use in application). sceneHelper.bringNodeToFront(buttonName, "Page"); This
   * will bring up the page with a name that has the same prefixed name as the button clicked.
   **/
  public static void bringNodeToFront(String nodeName,
      String appendingText)//Appending text is the suffix of node name, such as "Page" or "Button" (first letter capiatlized).
  {
    nodeName = convertNameToID(nodeName, appendingText);
    setMainScene();
    getNodeById(nodeName).toFront();
  }

  public static String convertNameToID(String text, String appendingText) {
    //Lowers first letter of word and removes special characters. Page Ids should always be the button's name (first letter) lowered plus "Page" in camel case. So the "Api/Database" button would return "apiDatabase" and the page Id would be "apiDatabasePage".
    //appendingText in the above example would be "Page", while a tab's content area would be "TextArea".
    char c[] = text.toCharArray();
    c[0] = Character.toLowerCase(c[0]);
    text = (new String(c) + appendingText).replaceAll("[^a-zA-Z0-9]", "");
    return text;
  }

}
