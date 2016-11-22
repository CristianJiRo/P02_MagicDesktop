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

    //Variables para los filtros.
    String rarity;
    String colorSearch;


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

         //items.addAll(getCardsFilterColor("red"+pipe+"white"));

         lv_cardsList.setCellFactory((ListView<Card> l) -> new CardCell());
         lv_cardsList.setItems(items);

         lv_cardsList.setOnMouseClicked(event -> {

             Card celda = lv_cardsList.getSelectionModel().getSelectedItem();

             //Lanzamos la funcion mostrar detalles.


             System.out.println(celda.getName());

         });
     }


    public void Filtrar() {

        //Iniciamos la variable conector con la coma para las busquedas con varios colores.
        //La variable cantCol es para saber cuantos colores hay seleccionados.
        String conector = "%7c";
        int cantCol=0;
        colorSearch="";

        //Guardamos el valor del filtro rarity en una variable.
        rarity= (String) cx_rarity.getValue();

        //En este array añadiremos los colores seleccionados para generar la variable nevecasria para la busqueda con filtro.
        String [] colores = new String []{null,null,null,null,null};

        //Si queremos hacer una busqueda de cartas multicolor, cambiamos el conector por el valor tratado del pipe para la URL.
        if (cb_1Color.isSelected()){
            conector = ",";
        }

        //Comprobamos cuantos colores hay seleccionados.

        if (cb_White.isSelected()) {
            colores[cantCol]="white";
            cantCol++;
        }
        if (cb_Black.isSelected()) {
            colores[cantCol]="black";
            cantCol++;
        }
        if (cb_Red.isSelected()) {
            colores[cantCol]="red";
            cantCol++;
        }
        if (cb_Green.isSelected()) {
            colores[cantCol]="green";
            cantCol++;
        }
        if (cb_Blue.isSelected()) {
            colores[cantCol]="blue";
            cantCol++;
        }
        for (int i = 0; i < cantCol ; i++) {
            if ((cantCol>1) && (i != cantCol-1)){
                colorSearch = colorSearch + colores[i] + conector;
            }
            else {
                colorSearch = colorSearch + colores[i];
            }
            
        }

        //Lanzamos el metodo donde seleccionamos la llamada apropiada a la API.

        ApiCall();
    }

    private void ApiCall() {

        //Las busquedas sin filtro por rareza.
        if ((rarity == null) || (rarity == "Basic Land") || (rarity == "Any")){

            System.out.println(colorSearch);
            //Hacemos la busqueda con el filtro. Pimero limpiamos el contenido de items.
            items.clear();
            items.addAll(getCardsFilterColor(colorSearch));

            System.out.println(items.get(1).getName());
            //Llenamos el Vlist.
            LlenarVlist(items);
        }

    }

}
