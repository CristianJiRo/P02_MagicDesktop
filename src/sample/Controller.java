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

import static sample.MagicApi.getCardsFilterColor;

//API : https://docs.magicthegathering.io/

public class Controller {


    //Elementos del filtro.
    @FXML private CheckBox cb_White;
    @FXML private CheckBox cb_Black;
    @FXML private CheckBox cb_Red;
    @FXML private CheckBox cb_Green;
    @FXML private CheckBox cb_Blue;
    @FXML private ComboBox cx_rarity;
    @FXML private CheckBox cb_1Color;

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

        //Llenamos el objeto items con las cartas por defecto y llamamos a la funcion que llena la Vlist.
        items.addAll(MagicApi.getAllCards());

        LlenarVlist(items);


    }



     class CardCell extends ListCell<Card> {
         @Override
         public void updateItem(Card item, boolean empty) {
             super.updateItem(item, empty);

             if (item != null) {
                 String[] color = item.getColors();
                 String colores = "";
                 if (item.getColors() != null) {
                     for (int i = 0; i < color.length; i++) {
                         colores = colores + " " + color[i];
                     }
                 }
                 setText(item.getName() + " \n" + colores);

                 //ImageView im = new ImageView(item.getUrlImage());
                 //setGraphic(im);


             }
         }
     }


     //Mostramos los datos correspondientes en el ListView.
     public void LlenarVlist(ObservableList<Card> items){

         items.addAll(MagicApi.getAllCards());

         //items.addAll(getCardsFilterColor("red"+pipe+"white"));

         lv_cardsList.setCellFactory((ListView<Card> l) -> new CardCell());

         lv_cardsList.setItems(items);
         lv_cardsList.setOnMouseClicked(event -> {

             Card celda = lv_cardsList.getSelectionModel().getSelectedItem();
             System.out.println(celda.getName());

         });


     }


    public void Filtrar() {

        //Tratamiento del caracter | para la URL.
        String pipe = "%7c";
        int cantCol=0;

        String colores="";

        //System.out.println(cx_rarity.getValue());

        //Comprobamos cuantos colores hay seleccionados.

        if (cb_White.isSelected()) {
            cantCol++;
        }
        if (cb_Black.isSelected()) {
            cantCol++;
        }
        if (cb_Red.isSelected()) {
            cantCol++;
        }
        if (cb_Green.isSelected()) {
            cantCol++;
        }
        if (cb_Blue.isSelected()) {
            cantCol++;
        }

        
    }

}
