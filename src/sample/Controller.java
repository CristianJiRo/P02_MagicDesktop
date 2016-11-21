package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.awt.*;
import java.util.Observable;

//API : https://docs.magicthegathering.io/

public class Controller {


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
        items.addAll(MagicApi.getAllCards());

        for (int i = 0; i < items.size() ; i++) {
            cartas.add(i, items.get(i).getName());}

        lv_cardsList.setCellFactory((ListView<Card> l) -> new CardCell());

        lv_cardsList.setItems(items);
        lv_cardsList.setOnMouseClicked(event -> {

            Card celda = lv_cardsList.getSelectionModel().getSelectedItem();
            System.out.println(celda.getName());

        });

    }



     class CardCell extends ListCell<Card> {
        @Override
        public void updateItem(Card item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                String [] color = item.getColors();
                String colores="";
                if(item.getColors()!=null) {
                    for (int i = 0; i < color.length; i++) {
                        colores=colores+ " " + color[i];
                    }
                }
                setText(item.getName()+" \n" + colores);

                //ImageView im = new ImageView(item.getUrlImage());
                //setGraphic(im);



            }                }
            }



    public void Filtrar() {

        String colores="";
        if (cb_White.isSelected()){

        }

        //System.out.println(cx_rarity.getValue());



    }

}
