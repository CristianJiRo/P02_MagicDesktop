package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;


import java.awt.*;


//API : https://docs.magicthegathering.io/



public class Controller extends Component {

    @FXML private CheckBox cb_White;
    @FXML private CheckBox cb_Black;
    @FXML private CheckBox cb_Red;
    @FXML private CheckBox cb_Green;
    @FXML private CheckBox cb_Blue;
    //@FXML  choisB_list;



    public void initialize() {

        ChoiceBox choisB_list = new ChoiceBox(
                FXCollections.observableArrayList(
                        "A", "B", "C"));



    }


public void Filtrar() {

    String colores="";
    if (cb_White.isSelected()){

    }
    //ChoiceBox choisB_list = new ChoiceBox();
    //choisB_list.getItems().addAll("item1", "item2", "item3");


}

}
