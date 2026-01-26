package main;

import java.util.List;

import db.BookDAO;
import vo.BookDTO;

public class JDBC01 {

    public static void main(String[] args) {
        
        BookDAO dao = new BookDAO();
        //책 전체 조회
        List<BookDTO> list = dao.getAllBooks();
        list.stream().forEach(book -> System.out.println(book));
        System.out.println("=========================================");
        //특정 도서 조회
        BookDTO book = dao.getBook("자바의 정석");
        System.out.println("책 ID: " + book.getBookid());
        System.out.println("책 제목: " + book.getBookname());
        System.out.println("출판사: " + book.getPublisher());
        System.out.println("가격: " + book.getPrice());
        System.out.println("_++++++++++===============================");
        //도서 추가
        //책 이름: 해리포터, 가격: 32000, 출판사: 솔데스크
        //dao.addBook(new BookDTO("반지의 제왕", "솔데스크", 38000));
        System.out.println("+=======================================+");
        //도서 정보 수정
        dao.updateBook("오라클의 정석", 45000);

        //책 삭제
        dao.deleteBook("해리포터");
    }
}
