package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import java.awt.*;

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
    @FXML private ListView<String> lv_cardsList;
    @FXML private ObservableList<Card> items = FXCollections.observableArrayList();
    @FXML private ObservableList<String> cartas = FXCollections.observableArrayList();


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


        lv_cardsList.setItems(cartas);


        for (int i = 0; i < items.size() ; i++) {
              cartas.add(i, items.get(i).getName());}

        //lv_cardsList = new ListView<Card>();
        //lv_cardsList.setCellFactory(ComboBoxListCell.forListView(items));
        //System.out.println(lv_cardsList.getItems().get(1).getName());

        //MagicApi.getAllCards();

    }

    public void OnSelection(){
        System.out.println("ssssss");
        //System.out.println(e);

    }


    public void Filtrar() {

        String colores="";
        if (cb_White.isSelected()){

        }

        //System.out.println(cx_rarity.getValue());



    }

}
