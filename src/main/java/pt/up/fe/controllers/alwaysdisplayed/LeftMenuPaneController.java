package pt.up.fe.controllers.alwaysdisplayed;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import pt.up.fe.helpers.CustomSceneHelper;


public class LeftMenuPaneController implements Initializable {

    
    @FXML
    private Button homeButton, applicationButton, personsbutton, _generalButton, logsButton;

    @FXML
    private void highlightButton(MouseEvent event)
    {
        String nodeID = CustomSceneHelper.getSourceID(event.getSource());
        Node eventNode = CustomSceneHelper.getNodeById(nodeID);
        eventNode.setStyle("-fx-background-color: #555764;");
    }
    
    @FXML
    private void unHighlightButton(MouseEvent event)
    {
        String nodeId = CustomSceneHelper.getSourceID(event.getSource());
        Node eventNode = CustomSceneHelper.getNodeById(nodeId);
        eventNode.setStyle("-fx-background-color: #3A3C43;");
    }
    
    @FXML
    private void unHighlightButtonIfNotActive(MouseEvent event)
    {
        Node activePage = CustomSceneHelper.getNodeById("pageNameLabel");
        String activePageName = CustomSceneHelper.getSourceName(activePage);
        String hoveredButton = CustomSceneHelper.getSourceName(event.getSource());
        if (!activePageName.equals(hoveredButton))
        {
            unHighlightButton(event);
        }
     }
    
    @FXML
    private void unHighlightByID(String nodeId)
    {
        Node previousPageButton = CustomSceneHelper.getNodeById(nodeId);
        previousPageButton.setStyle("-fx-background-color: #3A3C43;");
    }
    
    @FXML
    private void menuButtonClicked(MouseEvent event) throws FileNotFoundException, InterruptedException
    {
        //Unhighlights the previous button that was clicked. 
        Node pageNameLabel = CustomSceneHelper.getNodeById("pageNameLabel");
        String previousPageName = CustomSceneHelper.getSourceName(pageNameLabel);
        String previousPageNameID = CustomSceneHelper.convertNameToID(previousPageName, "Button");
        unHighlightByID(previousPageNameID);
        
        //Changes name of the label in top left of top menu bar (id of pageNameLabel) to the name of the button clicked. 
        String buttonName = CustomSceneHelper.getSourceName(event.getSource());
        CustomSceneHelper.changeLabelName("pageNameLabel", buttonName);

        //Brings the page clicked to the front. 
        CustomSceneHelper.bringNodeToFront(buttonName, "Page");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }    
    
}
