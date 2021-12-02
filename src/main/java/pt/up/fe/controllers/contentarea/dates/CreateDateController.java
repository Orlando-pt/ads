package pt.up.fe.controllers.contentarea.dates;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import pt.up.fe.dates.IDate;
import pt.up.fe.facades.DateFacade;
import pt.up.fe.helpers.CustomSceneHelper;
import pt.up.fe.helpers.events.DateCustomEvent;
import pt.up.fe.helpers.events.PersonCustomEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateDateController implements Initializable {
    @FXML
    private TextField dayInput;

    @FXML
    private TextField dayInput1;

    @FXML
    private Text fromLabel;

    @FXML
    private TextField hourInput;

    @FXML
    private TextField hourInput1;

    @FXML
    private TextField minuteInput;

    @FXML
    private TextField minuteInput1;

    @FXML
    private TextField monthInput;

    @FXML
    private TextField monthInput1;

    @FXML
    private TextField secondsInput;

    @FXML
    private TextField secondsInput1;

    @FXML
    private Text toLabel;

    @FXML
    private ToggleGroup toogleGroupDate;

    @FXML
    private TextField yearInput;

    @FXML
    private TextField yearInput1;

    @FXML
    private Button createBtn;

    @FXML
    void handleCreate(ActionEvent event) {
        String selected = ((RadioButton) this.toogleGroupDate.getSelectedToggle()).getText();

        IDate date;
        if (selected.equals("Simple Date")) {
            date = this.createDate();
        } else {
            date = new DateFacade().createIntervalDate(
                    this.createDate(),
                    this.createDate1()
            );
        }

        CustomSceneHelper.getNodeById("createDatePage").fireEvent(new DateCustomEvent(DateCustomEvent.DATE, date));
        CustomSceneHelper.bringNodeToFront("BirthEvent", "Page");
    }

    @FXML
    void handleStartDate(ActionEvent event) {
        this.hideDates();
        String selected = ((RadioButton) this.toogleGroupDate.getSelectedToggle()).getText();

        if (selected.equals("Simple Date")) {
            this.setNormalDateVisibility(true);
        } else {
            this.setIntervalDateVisibility(true);
        }
    }

    // Add a public no-args constructor
    public CreateDateController() {
    }

    @FXML
    public void initialize(URL url, ResourceBundle resources) {
    }

    private void setNormalDateVisibility(Boolean visible) {
        this.yearInput.setVisible(visible);
        this.monthInput.setVisible(visible);
        this.dayInput.setVisible(visible);
        this.hourInput.setVisible(visible);
        this.minuteInput.setVisible(visible);
        this.secondsInput.setVisible(visible);

        this.createBtn.setVisible(visible);
    }

    private void setIntervalDateVisibility(Boolean visible) {
        this.setNormalDateVisibility(visible);
        this.fromLabel.setVisible(visible);
        this.toLabel.setVisible(visible);

        this.yearInput1.setVisible(visible);
        this.monthInput1.setVisible(visible);
        this.dayInput1.setVisible(visible);
        this.hourInput1.setVisible(visible);
        this.minuteInput1.setVisible(visible);
        this.secondsInput1.setVisible(visible);
    }

    private void hideDates() {
        this.setIntervalDateVisibility(false);
    }

    private IDate createDate() {
        return new DateFacade().createSimpleDate(
                this.yearInput.getText(),
                this.monthInput.getText(),
                this.dayInput.getText(),
                this.hourInput.getText(),
                this.minuteInput.getText(),
                this.secondsInput.getText()
        );
    }

    private IDate createDate1() {
        return new DateFacade().createSimpleDate(
                this.yearInput1.getText(),
                this.monthInput1.getText(),
                this.dayInput1.getText(),
                this.hourInput1.getText(),
                this.minuteInput1.getText(),
                this.secondsInput1.getText()
        );
    }
}
