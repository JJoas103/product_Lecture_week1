package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.MemberDTO;

public class MemberDAO {
    
    String url = "jdbc:mysql://localhost:3306/movie";
    String user = "root";
    String pass = "sukyum1003.";

    public Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }

    //중복확인
    public boolean checkId(String inputId) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement
                = connection.prepareStatement("select * from member where member_id = ?")){
                preparedStatement.setString(1, inputId);
            try (ResultSet rs = preparedStatement.executeQuery();){
                if(rs.next()){
                    return true;
                } //
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //회원가입
    public void insertMember(MemberDTO joinMember){

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into member(member_id, member_pass, member_name) values (?, ?, ?)");){
                preparedStatement.setString(1, joinMember.getMemberId());
                preparedStatement.setString(2, joinMember.getMemberPass());
                preparedStatement.setString(3, joinMember.getMemberName());
                preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MemberDTO loginMember(String inputId, String inputPass) {
        MemberDTO loginMember = null;

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement
                = connection.prepareStatement("select * from member where member_id = ? and member_pass = ? ");) {
                    preparedStatement.setString(1, inputId);
                    preparedStatement.setString(2, inputPass);
                    try (ResultSet rs = preparedStatement.executeQuery();){
                        if(rs.next()) {
                            loginMember = new MemberDTO();
                            loginMember.setMemberIdx(rs.getInt("member_idx"));
                            loginMember.setMemberId(rs.getString("member_id"));
                            loginMember.setMemberPass(rs.getString("member_pass"));
                            loginMember.setMemberName(rs.getString("member_name"));
                            return loginMember;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginMember;
    }
}
