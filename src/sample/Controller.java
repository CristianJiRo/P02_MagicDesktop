package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.ComboBoxListCell;


import javax.print.DocFlavor;
import java.awt.*;

import java.util.ResourceBundle;


//API : https://docs.magicthegathering.io/



public class Controller extends Component {


    //Elementos del filtro.
    @FXML private CheckBox cb_White;
    @FXML private CheckBox cb_Black;
    @FXML private CheckBox cb_Red;
    @FXML private CheckBox cb_Green;
    @FXML private CheckBox cb_Blue;
    @FXML private ComboBox cx_rarity;

    //ListView
    @FXML private ListView<Card> lv_cardsList;
    @FXML private ObservableList<Card> items = FXCollections.observableArrayList();


    public void initialize() {


        //Inicialización del ComboBox con los valores de las rarezas de las cartas.
        ObservableList<String> rarityList =FXCollections.observableArrayList(

                "Common",
                "Uncommon",
                "Rare",
                "Mythic Rare",
                "Special",
                "Basic Land",
                "Any"
        );

        cx_rarity.setItems(rarityList);


        //ListView
        items.addAll(MagicApi.getAllCards());
        lv_cardsList.setItems(items);



        //lv_cardsList = new ListView<Card>();
        //lv_cardsList.setCellFactory(ComboBoxListCell.forListView(items));
        //System.out.println(lv_cardsList.getItems().get(1).getName());

        //MagicApi.getAllCards();

    }

    public void CargarListView(){



    }




    public void Filtrar() {

        String colores="";
        if (cb_White.isSelected()){

        }

        //System.out.println(cx_rarity.getValue());



    }

}
