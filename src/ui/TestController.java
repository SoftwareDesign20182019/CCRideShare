package ui;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TestController {

    @FXML
    private Button btn_submit;

    @FXML
    private Label message;

    @FXML
    void Display(ActionEvent event) {
    	 message.setText("Kobi is LIFE");
    }

}
