package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.MovieDTO;

public class MovieDAO {
    
    String url = "jdbc:mysql://localhost:3306/movie";
    String user = "root";
    String pass = "sukyum1003.";

    public Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }

    //영화선택 페이지로 가져갈 4개의 영화 리스트
    public List<MovieDTO> getMovieListByPage(int page) {

        int size = 4;
        int offset = (page - 1) * size;
        //1페이지면 offset은 0
        //2페이지면 offset은 4
        //3페이지면 offset은 8

        List<MovieDTO> list = new ArrayList<MovieDTO>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from movie limit ? offset ?");) {
                //limit: 가져올 행의 개수 제한
                //offset: 몇 번 가상번호부터 가져올 건지 선택
                preparedStatement.setInt(1, size);
                preparedStatement.setInt(2, offset);
                try (ResultSet rs = preparedStatement.executeQuery();){
                    while (rs.next()) {
                        MovieDTO movie = new MovieDTO();
                        movie.setMovie_id(rs.getInt("movie_id"));
                        movie.setMovie_img(rs.getString("movie_img"));
                        movie.setMovie_price(rs.getInt("movie_price"));
                        movie.setMovie_story(rs.getString("movie_story"));
                        movie.setMovie_title(rs.getString("movie_title"));
                        list.add(movie);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //전체 영화 개수 조회
    public int getTotalMoviePage() {
        int count = 0;
        double size = 4.0;//페이지당 출력 개수
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) as cnt from movie")){
            try (ResultSet rs = preparedStatement.executeQuery();){
                if(rs.next()){
                    count = rs.getInt("cnt");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int)Math.ceil(count / size);//Math.ceil: 반올림함수
    }
}
