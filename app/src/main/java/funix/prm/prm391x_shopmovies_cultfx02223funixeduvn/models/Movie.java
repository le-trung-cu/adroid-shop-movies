package funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.models;

import java.io.Serializable;

import javax.json.JsonObject;
// We want to movie able to pass object movie from the list to this detail view
// so make our movie class serializable
public class Movie implements Serializable {
    private String imgUrl;
    private String title;
    private String price;

    public Movie(String imgUrl, String title, String price) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public static Movie from(JsonObject jsonObject) {
        String imgUrl = jsonObject.getString("image");
        String title = jsonObject.getString("title");
        String price = jsonObject.getString("price");
        Movie movie = new Movie(imgUrl, title, price);
        return movie;
    }
}
