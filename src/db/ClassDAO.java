package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.ClassDTO;
import vo.StudentDTO;
public class ClassDAO {
    
    String url = "jdbc:mysql://localhost:3306/jdbc";
    String user = "root";
    String pass = "sukyum1003.";

    //DB 연결
    public Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }

    public List<ClassDTO> getAllClasses() {
        List<ClassDTO> list = new ArrayList<ClassDTO>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement 
                = connection.prepareStatement("select * from class");){
            try(ResultSet rs = preparedStatement.executeQuery();) {
                while(rs.next()) {
                    ClassDTO classD = new ClassDTO();
                    classD.setClass_idx(rs.getInt("class_idx"));
                    classD.setClass_name(rs.getString("class_name"));
                    classD.setClass_time(rs.getInt("class_time"));
                    classD.setClass_count(rs.getInt("class_count"));
                    classD.setProf_idx(rs.getInt("prof_idx"));
                    list.add(classD);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //강의개설
    public void insertLecture(ClassDTO createLect) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement
                 = connection.prepareStatement("insert into class(class_name, class_time, class_count, prof_idx) values(?, ?, ?, ?)");){
            
                    preparedStatement.setString(1, createLect.getClass_name());
                    preparedStatement.setInt(2, createLect.getClass_time());
                    preparedStatement.setInt(3, createLect.getClass_count());
                    preparedStatement.setInt(4, createLect.getProf_idx());
                    preparedStatement.executeUpdate();

                    System.out.println("강의 개설 완료!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //강의정보조회

    //강의 취소
    public void deleteLecture(String className) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = 
                connection.prepareStatement("delete e from enroll e join class c on c.class_idx = e.class_idx where c.class_name = ?");){
                    preparedStatement.setString(1, className);
                    preparedStatement.executeUpdate();
                    System.out.println(className + "을(를) 수강취소합니다");
                    
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void plusClassCount(ClassDTO classD) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update class set class_count = class_count + 1 where class_idx = ?");){
                preparedStatement.setInt(1, classD.getClass_idx());
                preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
