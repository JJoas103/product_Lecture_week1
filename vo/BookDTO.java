package vo;

public class BookDTO {
    int bookid;
    String bookname;
    String publisher;
    int price;

    public BookDTO(){}
    public BookDTO(String bookname, String publisher, int price) {
        this.bookname = bookname;
        this.publisher = publisher;
        this.price = price;
    }
    
    public int getBookid() {
        return this.bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return this.bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "{" +
            " bookid='" + getBookid() + "'" +
            ", bookname='" + getBookname() + "'" +
            ", publisher='" + getPublisher() + "'" +
            ", price='" + getPrice() + "'" +
            "}";
    }



}


    
