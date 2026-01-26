package db;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.BookDTO;

public class BookDAO {

    String url = "jdbc:mysql://localhost:3306/jdbc";
    String user = "root";
    String pass = "sukyum1003.";

    //DB 연결
    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, pass);
        System.out.println("DB 연결 성공");
        return connection;
    }

    //책 테이블 데이터 전체 조회
    public List<BookDTO> getAllBooks() {
        
        List<BookDTO> list = new ArrayList<BookDTO>();
        try (
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement("select * from book");
        ){
            try (ResultSet rs = pstmt.executeQuery();){
                while(rs.next()){
                    BookDTO book = new BookDTO();
                    book.setBookid(rs.getInt("bookid"));
                    book.setBookname(rs.getString("bookname"));
                    book.setPublisher(rs.getString("publisher"));
                    book.setPrice(rs.getInt("price"));
                }//while
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("DB 연결 실패");
            e.printStackTrace();
        }
        return list;

    }
    // 특정 도서 조회
    public BookDTO getBook(String bookname) {
        BookDTO book = null;
        try (
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement("select * from book where bookname = ?");
            ) {
                pstmt.setString(1, bookname);
                book = new BookDTO();
                try (ResultSet rs = pstmt.executeQuery();){
                    if(rs.next()){
                        /*
                        System.out.println("책ID: " + (rs.getInt("bookid")));
                        System.out.println("책 이름: " + (rs.getString("bookname")));
                        System.out.println("출판사: " + rs.getString("publisher"));
                        System.out.println("가격: " + (rs.getInt("price")));
                        */
                        book.setBookid(rs.getInt("bookid"));
                        book.setBookname(rs.getString("bookname"));
                        book.setPublisher(rs.getString("publiser"));
                        book.setPrice(rs.getInt("price"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } catch (Exception e) {
            System.out.println("DB 연결 실패");
        }
        return book;
    }

    public void addBook(BookDTO book) {
        try(
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement("insert into book(bookname, publisher, price) values(?, ?, ?)");
        ) {// 자원 누수 예방
            pstmt.setString(1, book.getBookname());
            pstmt.setString(2, book.getPublisher());
            pstmt.setInt(3, book.getPrice());

            pstmt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("DB 연결 실패");
            e.printStackTrace();
        }
    }
    // 책의 가격 수정
    public void updateBook(String bookname, int price){
        try(
            Connection connection = getConnection();
            PreparedStatement pstmt = 
                connection.prepareStatement("update book set price = ? where bookname = ?")) {
                    pstmt.setInt(1, price);
                    pstmt.setString(2, bookname);
                    pstmt.executeUpdate();
                    System.out.println("수정 성공!!");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    // 책 삭제
    public void deleteBook(String bookname){
        try (
            Connection connection = getConnection();
            PreparedStatement pstmt =
                connection.prepareStatement("delete from book where bookname = ?")
        ){
            pstmt.setString(1, bookname);
            pstmt.executeUpdate();
            System.out.println("삭제 성공!!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // # JDBC03 특정 회원이 구매한 도서 목록
    public List<BookDTO> getOrderInfo(String name) {
        List list = new ArrayList<BookDTO>();
        try(Connection connection = getConnection();
            PreparedStatement pstmt 
                = connection.prepareStatement("select b.bookname " + //
                                        "from orders od " + //
                                        "join book b on b.bookid = od.bookid " + //
                                        "join customer c on c.custid = od.custid " + //
                                        "where c.custname = ?;")) {
                pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery();){
                while(rs.next()){
                    BookDTO book = new BookDTO();
                    book.setBookname(rs.getString("bookname"));
                    list.add(book);
                }
            } catch (Exception e) {
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
