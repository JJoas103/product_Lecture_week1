package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import vo.ItemDTO;
import vo.MemberDTO;

public class MemberDAO {
    
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
    public void insertMember(MemberDTO joinMember) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement
                 = connection.prepareStatement("insert into member(member_id, member_pass, member_address, member_phone, member_money) values(?, ?, ?, ?, ?)")){
            
                    preparedStatement.setString(1, joinMember.getMember_id());
                    preparedStatement.setString(2, joinMember.getMember_pass());
                    preparedStatement.setString(3, joinMember.getMember_address());
                    preparedStatement.setString(4, joinMember.getMember_phone());
                    preparedStatement.setInt(5, joinMember.getMember_money());
                    preparedStatement.executeUpdate();

                    System.out.println("회원가입 완료!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MemberDTO getMember(String id, String pass) {
        
        MemberDTO loginMember = null;

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement 
                = connection.prepareStatement("select * from member where member_id = ? and member_pass = ?");){
                    preparedStatement.setString(1, id);
                    preparedStatement.setString(2, pass);
                    try(ResultSet rs = preparedStatement.executeQuery();) {
                        if(rs.next()) {
                            loginMember = new MemberDTO();
                            loginMember.setMember_idx(rs.getInt("member_idx"));
                            loginMember.setMember_id(rs.getString("member_id"));
                            loginMember.setMember_pass(rs.getString("member_pass"));
                            loginMember.setMember_address(rs.getString("member_address"));
                            loginMember.setMember_phone(rs.getString("member_phone"));
                            loginMember.setMember_money(rs.getInt("member_money"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } 
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return loginMember;
    }
    // 충전하기
    public void updateMoney(int money, MemberDTO loginMember) {

        int chargeMoney = loginMember.getMember_money() + money;;

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement 
                = connection.prepareStatement("update member set member_money = ? where member_idx = ?");){
            preparedStatement.setInt(1, chargeMoney);
            preparedStatement.setInt(2, loginMember.getMember_idx());
            preparedStatement.executeUpdate();
            System.out.println("충전이 완료되었습니다!! 현재 잔액은 " + chargeMoney + "입니다!");
            loginMember.setMember_money(chargeMoney);// db에는 갱신이 됬지만, chargeMoney변수에는 업데이트가 되지 않아 setter에 다시 업데이트

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // 상품구매(store 테이블에 판매 정보 저장)
    public void buyItem(MemberDTO loginMember, ItemDTO item) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement 
                = connection.prepareStatement("insert into store(order_date, member_idx, item_idx) values (now(), ?, ?)");){
                    preparedStatement.setInt(1, loginMember.getMember_idx());
                    preparedStatement.setInt(2, item.getItemIdx());
                    preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
       }
       int money = loginMember.getMember_money() - item.getItemPrice();
       loginMember.setMember_money(money);
       buyMoney(loginMember);
    }

    //상품 구매 후 회원의 소지금 갱신
    public void buyMoney(MemberDTO loginMember) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement 
                = connection.prepareStatement("update member set member_money = ? where member_idx = ?");){
            preparedStatement.setInt(1, loginMember.getMember_money());
            preparedStatement.setInt(2, loginMember.getMember_idx());
            preparedStatement.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
    }
    }
}
