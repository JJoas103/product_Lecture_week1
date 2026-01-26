package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.ClassDTO;
import vo.ProfDTO;
import vo.StudentDTO;

public class ProfDAO {
    String url = "jdbc:mysql://localhost:3306/jdbc";
    String user = "root";
    String pass = "sukyum1003.";

    //DB 연결
    public Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }

    public void insertProf(ProfDTO joinProf) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement
                 = connection.prepareStatement("insert into professor(prof_id, prof_pass, prof_name) values(?, ?, ?)")){
            
                    preparedStatement.setString(1, joinProf.getProf_id());
                    preparedStatement.setString(2, joinProf.getProf_pass());
                    preparedStatement.setString(3, joinProf.getProf_name());
                    preparedStatement.executeUpdate();

                    System.out.println("회원가입 완료!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //중복확인
    public boolean confirmId(String id) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = 
                connection.prepareStatement("select * from professor where prof_id = ?");){
                preparedStatement.setString(1, id);
                try (ResultSet rs = preparedStatement.executeQuery();){
                    if(rs.next()){//쿼리문의 결과가 존재함
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;//쿼리문의 결과가 존재하지 않음
    }
    public ProfDTO loginProf(String id, String pass) {
        ProfDTO loginProf = null;

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement 
                = connection.prepareStatement("select * from professor where prof_id = ? and prof_pass = ?");){
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, pass);
                try (ResultSet rs = preparedStatement.executeQuery();){
                    if(rs.next()) {
                        loginProf = new ProfDTO();
                        loginProf.setProf_idx(rs.getInt("prof_idx"));
                        loginProf.setProf_id(rs.getString("prof_id"));
                        loginProf.setProf_pass(rs.getString("prof_pass"));
                        loginProf.setProf_name(rs.getString("prof_name"));
                        return loginProf; //결과값 리턴
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginProf;//null값 리턴
    }
    
    
}
