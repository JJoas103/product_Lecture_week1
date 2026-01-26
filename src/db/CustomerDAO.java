package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.CustomerDTO;

public class CustomerDAO {
    
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
    // 고객 추가
    public void addCustomer(CustomerDTO cus) {
        try(
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement("insert into Customer(custname, address, phone) values(?, ?, ?)");
        ) {// 자원 누수 예방
            pstmt.setString(1, cus.getCustname());
            pstmt.setString(2, cus.getAddress());
            pstmt.setString(3, cus.getPhone());

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("DB 연결 실패");
            e.printStackTrace();
        }
    }
    // 특정 회원 조회
    public CustomerDTO getCustomer(String custname) {
        CustomerDTO customer = null;
        try (
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement("select * from customer where custname = ?");
            ) {
                pstmt.setString(1, custname);
                customer = new CustomerDTO();
                try (ResultSet rs = pstmt.executeQuery();){
                    if(rs.next()){
                        /*
                        System.out.println("책ID: " + (rs.getInt("bookid")));
                        System.out.println("책 이름: " + (rs.getString("bookname")));
                        System.out.println("출판사: " + rs.getString("publisher"));
                        System.out.println("가격: " + (rs.getInt("price")));
                        */
                        customer.setCustid(rs.getInt("custid"));
                        customer.setCustname(rs.getString("custname"));
                        customer.setAddress(rs.getString("address"));
                        customer.setPhone(rs.getString("phone"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } catch (Exception e) {
            System.out.println("DB 연결 실패");
        }
        return customer;
    }
    // 모든 회원 조회
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> list = new ArrayList<CustomerDTO>();
        try (
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement("select * from customer");
        ){
            try (ResultSet rs = pstmt.executeQuery();){
                while(rs.next()){
                    CustomerDTO customer = new CustomerDTO();
                    customer.setCustid(rs.getInt("custid"));
                    customer.setCustname(rs.getString("custname"));
                    customer.setAddress(rs.getString("address"));
                    customer.setPhone(rs.getString("phone"));
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
    //특정 회원 삭제 (기본키)
    public void deleteBook(int custid){
        try (
            Connection connection = getConnection();
            PreparedStatement pstmt =
                connection.prepareStatement("delete from customer where custid = ?")
        ){
            pstmt.setInt(1, custid);
            pstmt.executeUpdate();
            System.out.println("삭제 성공!!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //특정 회원 수정(기본키)
    public void updateCustomer(String address, int custid){
        try(
            Connection connection = getConnection();
            PreparedStatement pstmt = 
                connection.prepareStatement("update customer set address = ? where custid = ?")) {
                    pstmt.setString(1, address);
                    pstmt.setInt(2, custid);
                    pstmt.executeUpdate();
                    System.out.println("수정 성공!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
