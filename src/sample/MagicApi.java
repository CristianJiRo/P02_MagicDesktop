package sample;

import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.net.URI;
import java.lang.String;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by Kamelot on 19/11/2016.
 */
public class MagicApi {

    static final String Base_URL = "https://api.magicthegathering.io/v1/cards";

    static ArrayList<Card> getAllCards() {
        return doCall(Base_URL);
    }

    static ArrayList<Card> getCardsFilterColor(String color) {
        URI uri = URI.create(Base_URL+"?colors="+color);
        return doCall(uri.toASCIIString());
    }

    static ArrayList<Card> getCardsFilterRarity(String rarity) {
        URI uri = URI.create(Base_URL+"?rarity="+rarity);
        return doCall(uri.toASCIIString());
    }

    static ArrayList<Card> getCardsFilterRarityColors(String rarity, String color) {
        URI uri = URI.create(Base_URL+"?rarity="+rarity+"?colors="+color);
        return doCall(uri.toASCIIString());
    }

    @Nullable
    private static ArrayList<Card> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return proccesJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Card> proccesJson(String s) {
        ArrayList<Card> cards = new ArrayList<>();

        try {

            JSONObject data = new JSONObject(s);
            JSONArray jsonCards = data.getJSONArray("cards");

            for (int i = 0; i < jsonCards.length(); i++) {

                JSONObject jsonCard = jsonCards.getJSONObject(i);
                Card newCard = new Card();

                newCard.setName(jsonCard.getString("name"));
                newCard.setRarity(jsonCard.getString("rarity"));
                newCard.setType(jsonCard.getString("type"));

                if (jsonCard.has("text")) newCard.setText(jsonCard.getString("text"));
                if (jsonCard.has("imageUrl")) newCard.setUrlImage(jsonCard.getString("imageUrl"));
                if (jsonCard.has("colors")) {

                    String colors [] = new String[jsonCard.getJSONArray("colors").length()];

                    for (int j = 0; j < jsonCard.getJSONArray("colors").length(); j++) {
                        colors[j] = jsonCard.getJSONArray("colors").get(j).toString();
                    }
                    newCard.setColors(colors);
                }

                cards.add(newCard);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cards;
    }



}
