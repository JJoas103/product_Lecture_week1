package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.ClassDTO;
import vo.MemberDTO;
import vo.StudentDTO;

public class StudentDAO {

    String url = "jdbc:mysql://localhost:3306/jdbc";
    String user = "root";
    String pass = "sukyum1003.";

    //DB 연결
    public Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }
    //회원가입
    public void insertStudent(StudentDTO joinStudent) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement
                 = connection.prepareStatement("insert into student(std_id, std_pass, std_name) values(?, ?, ?)")){
            
                    preparedStatement.setString(1, joinStudent.getStd_id());
                    preparedStatement.setString(2, joinStudent.getStd_pass());
                    preparedStatement.setString(3, joinStudent.getStd_name());
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
                connection.prepareStatement("select * from student where std_id = ?");){
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

    //로그인
    public StudentDTO loginStudent(String id, String pass) {
        StudentDTO loginStudent = null;

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement 
                = connection.prepareStatement("select * from student where std_id = ? and std_pass = ?");){
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, pass);
                try (ResultSet rs = preparedStatement.executeQuery();){
                    if(rs.next()) {
                        loginStudent = new StudentDTO();
                        loginStudent.setStd_idx(rs.getInt("std_idx"));
                        loginStudent.setStd_id(rs.getString("std_id"));
                        loginStudent.setStd_pass(rs.getString("std_pass"));
                        loginStudent.setStd_name(rs.getString("std_name"));
                        return loginStudent;//이 변수는 쿼리결과를 의미
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginStudent;//이 변수는 null을 의미
    }

    //강의등록
    public void enrollLecture(StudentDTO loginStudent, ClassDTO classD) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement 
            = connection.prepareStatement("insert into enroll (std_idx, class_idx) values (?, ?)");){
                preparedStatement.setInt(1, loginStudent.getStd_idx());
                preparedStatement.setInt(2, classD.getClass_idx());
                preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int class_count = classD.getClass_count() - 1;
        classD.setClass_count(class_count);
        minusClassCount(classD);
    }

    public void minusClassCount(ClassDTO classD) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update class set class_count = class_count - 1 where class_idx = ?");){
                preparedStatement.setInt(1, classD.getClass_idx());
                preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
