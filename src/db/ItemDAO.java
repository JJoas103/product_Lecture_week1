package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.ItemDTO;

public class ItemDAO {

    String url = "jdbc:mysql://localhost:3306/jdbc";
    String user = "root";
    String pass = "sukyum1003.";

    //DB 연결
    public Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }
    // 구매가능한 상품 조회
    public List<ItemDTO> getItems(int money) {
        List<ItemDTO> list = new ArrayList<ItemDTO>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement 
                = connection.prepareStatement("select * from item where item_price <= ?");){
                preparedStatement.setInt(1,money);
            try (ResultSet rs = preparedStatement.executeQuery();){
                while(rs.next()) {
                    ItemDTO item = new ItemDTO();
                    item.setItemIdx(rs.getInt("item_idx"));
                    item.setItemName(rs.getString("item_name"));
                    item.setItemPrice(rs.getInt("item_price"));
                    item.setItemCount(rs.getInt("item_count"));
                    list.add(item);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ItemDTO> getPurchasedItemList(int member_idx) {
        List<ItemDTO> list = new ArrayList<ItemDTO>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement 
                = connection.prepareStatement("select i.item_name, s.order_date, m.member_id, i.item_price " + 
                                            "from store s " + 
                                            "join item i on i.item_idx = s.item_idx " + 
                                            "join member m on m.member_idx = s.member_idx " + 
                                            "where m.member_idx = ?");){
            preparedStatement.setInt(1, member_idx);
            try(ResultSet rs = preparedStatement.executeQuery();) {
                while(rs.next()) {
                    ItemDTO item = new ItemDTO();
                    item.setItemName(rs.getString("item_name"));
                    item.setOrderDate(rs.getString("order_date"));
                    item.setMemberId(rs.getString("member_id"));
                    item.setItemPrice(rs.getInt("item_price"));
                    list.add(item);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
