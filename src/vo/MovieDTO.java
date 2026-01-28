package vo;

public class MovieDTO {
    
    int movie_id;
    String movie_title;
    String movie_story;
    String movie_img;
    int movie_price;



    public int getMovie_id() {
        return this.movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_title() {
        return this.movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getMovie_story() {
        return this.movie_story;
    }

    public void setMovie_story(String movie_story) {
        this.movie_story = movie_story;
    }

    public String getMovie_img() {
        return this.movie_img;
    }

    public void setMovie_img(String movie_img) {
        this.movie_img = movie_img;
    }

    public int getMovie_price() {
        return this.movie_price;
    }

    public void setMovie_price(int movie_price) {
        this.movie_price = movie_price;
    }
    
}
