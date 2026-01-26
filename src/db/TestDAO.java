package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.CustomerDTO;
import vo.MemberDTO;

public class TestDAO {
    
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
    // 회원가입
    public void regiserMember(MemberDTO mem) {
        try(
            Connection connection = getConnection();
            PreparedStatement pstmt
             = connection.prepareStatement("insert into member(member_id, member_pass, member_address, member_phone, member_money) values(?, ?, ?, ?, ?)");
        ) {// 자원 누수 예방
            pstmt.setString(1, mem.getMember_id());
            pstmt.setString(2, mem.getMember_pass());
            pstmt.setString(3, mem.getMember_address());
            pstmt.setString(4, mem.getMember_phone());
            pstmt.setInt(5, mem.getMember_money());

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("DB 연결 실패");
            e.printStackTrace();
        }
    }
    // 로그인
    public MemberDTO getMember(String signID1, String signPass1) {
        MemberDTO member = null;
        try (
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement("select member_id, member_pass from member where member_id = ? and member_pass = ?");
            ) {
                pstmt.setString(1, signID1);
                pstmt.setString(2, signPass1);
                member = new MemberDTO();
                try (ResultSet rs = pstmt.executeQuery();){
                    if(rs.next()){
                        member.setMember_id(rs.getString("member_id"));
                        member.setMember_pass(rs.getString("member_pass"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } catch (Exception e) {
            System.out.println("DB 연결 실패");
        }
        return member;
    }
}
