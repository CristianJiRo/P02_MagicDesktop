package sample;

/**
 * Aplicación de escritorio creada por Cristian Jimenez para M07.
 *
 * Hacemos peticiones a la API : https://docs.magicthegathering.io/ Con varios filtros y mostramos los resultados.
 * Tambien podemos mostrar los detalles de una carta seleccionada.
 *
 *
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Controller {


    //Elementos del filtro.
    @FXML private CheckBox cb_White;
    @FXML private CheckBox cb_Black;
    @FXML private CheckBox cb_Red;
    @FXML private CheckBox cb_Green;
    @FXML private CheckBox cb_Blue;
    @FXML private ComboBox cx_rarity;
    @FXML private CheckBox cb_1Color;

    //Elementos del detalle.
    @FXML private ImageView im_card;
    @FXML private Label la_details;

    //ListView
    @FXML private ListView<Card> lv_cardsList;
    @FXML private ObservableList<Card> items = FXCollections.observableArrayList();

    //Variables para los filtros.
    String rarity;
    String colorSearch;
    int cantCol=5;
    String conector;

    int checkCount=5;

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


        //Listeners en los checkbox para controlar cuantos hay seleccionados al mismo tiempo
        //y activar o desactivar la opción muticolor del filtro.

        cb_Black.selectedProperty().addListener((ov, old_val, new_val) -> DesCheck(cb_Black));

        cb_Blue.selectedProperty().addListener((ov, old_val, new_val) -> DesCheck(cb_Blue));

        cb_White.selectedProperty().addListener((ov, old_val, new_val) -> DesCheck(cb_White));

        cb_Red.selectedProperty().addListener((ov, old_val, new_val) -> DesCheck(cb_Red));

        cb_Green.selectedProperty().addListener((ov, old_val, new_val) -> DesCheck(cb_Green));
    }


    //Controlaremos cuantos colores hay seleccionados y desactivaremos la opcion de filtro muti color cuando hayan menos de 2.
    public void DesCheck (CheckBox ch){

        if (ch.selectedProperty().getValue()){

            checkCount++;
        }
        else {

            checkCount--;
        }

        if (checkCount<2){

            cb_1Color.setDisable(true);
        }
        else {
            cb_1Color.setDisable(false);
        }

    }


     class CardCell extends ListCell<Card> {
         @Override
         public void updateItem(Card item, boolean empty) {
             super.updateItem(item, empty);

             if (item != null) {

                 setText(item.getName());
             }
         }
     }

     //Mostramos los datos correspondientes en el ListView.
     public void LlenarVlist(ObservableList<Card> items){


         lv_cardsList.setCellFactory((ListView<Card> l) -> new CardCell());
         lv_cardsList.setItems(items);

         lv_cardsList.setOnMouseClicked(event -> {

             Card celda = lv_cardsList.getSelectionModel().getSelectedItem();

             //Lanzamos la funcion mostrar detalles.

             //System.out.println(celda.getName());
             Detalles(celda);


         });

         Image ima = new Image(getClass().getResourceAsStream("images/reverse.jpeg"));
         im_card.setImage(ima);
     }

     //Mostramos los detalles de la carta selesccionada.
     public void Detalles (Card carta){
         String details;
         Image im;
         if(carta.getUrlImage()!=null) {
             im = new Image(carta.getUrlImage());
             im_card.setImage(im);
         }
         else {
             im = new Image(getClass().getResourceAsStream("images/notFound.png"));
             im_card.setImage(im);

         }


         //Tratamos el campo texto para que haga saltos de linia y se vea su contenido.
         double width = la_details.getWidth();
         double x = width/8;
         String text="";

         for (int i = 0; i < carta.getText().length(); i++) {
             text= text+carta.getText().charAt(i);
             if (carta.getText().charAt(i)=='\n'){
                 x= x+i;
             }
            if ((i >x)&& (carta.getText().charAt(i)==' ')){
                 text= text+"\n";
                x+=width/8;

            }


         }


         details="Name: "+ carta.getName() + "\n" +"Type: " + carta.getType()+ "\n" +"Text: " + text;
         la_details.setText(details);

     }


    public void Filtrar() {

        //Iniciamos la variable conector con la coma para las busquedas con varios colores.
        //La variable cantCol es para saber cuantos colores hay seleccionados.
        conector = "%7c";
        cantCol=0;
        colorSearch="";




        //Guardamos el valor del filtro rarity en una variable.
        rarity= (String) cx_rarity.getValue();

        //Tratamos los dos casos que contienen un espacio en el nombre.
        if (rarity=="Mythic Rare") rarity="Mythic_Rare";
        if (rarity=="Basic Land") rarity="Basic_Land";

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

        //Imagen para mostrar que estamos cargando (Las busquedas suelen ser tan rapidas que no se muestra casi nunca)
        Image ima = new Image(getClass().getResourceAsStream("images/loading.gif"));
        im_card.setImage(ima);

        ApiCall();

        //Volvemos a poner el reverso de la carta
        ima = new Image(getClass().getResourceAsStream("images/reverse.jpeg"));
        im_card.setImage(ima);
    }

    private void ApiCall() {



        //Caso especial con la rareza Basic Land que no tiene nunca colores.
        if (rarity=="Basic_Land"){

            //Limpiamos el objeto items y hacemos la peticion solo por rareza.
            items.clear();
            items.addAll(MagicApi.getCardsFilterRarity(rarity));

            //Llenamos el Vlist.
            LlenarVlist(items);
        }

        //Filtro de cartas sin color.
        else if (cantCol==0){

            //Sin color ni rareza
            if ((rarity == null) || (rarity == "Any")){

                items.clear();
                items.addAll(MagicApi.getUncolors());
                LlenarVlist(items);


            }

            //Sin color pero con rareza.
            else {

                items.clear();
                items.addAll(MagicApi.getUncolorsRarity(rarity));
                LlenarVlist(items);
                LlenarVlist(items);
            }
        }
        else if ((cantCol==5) && ((rarity == null) || (rarity == "Any")) ){

            items.clear();
            items.addAll(MagicApi.getAllCards());

        }
        //Resto de convinaciones de colores.
        else{

            //Los colores seleccionados y todas las rarezas.
            if ((rarity == null) || (rarity == "Any")) {

                items.clear();
                items.addAll(MagicApi.getCardsFilterColor(colorSearch));
                LlenarVlist(items);

            }
            //Los colores seleccionados y la rareza seleccionada.
            else {

                items.clear();
                items.addAll(MagicApi.getCardsFilterRarityColors(rarity,colorSearch));
                LlenarVlist(items);
            }

        }


    }




}
