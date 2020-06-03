package funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.models;

public class Movie {
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

}
