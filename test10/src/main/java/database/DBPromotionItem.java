package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Item;
import model.Promotion;
import model.PromotionItem;

public class DBPromotionItem {
	//khong hiện hidden =0(ẩn)
	public List<PromotionItem> getAllPromotionItem() {
		List<PromotionItem> result = new ArrayList<PromotionItem>();
		try (Connection connection = connectionDB.connect()) {
			String sql = "SELECT * FROM PROMOTION_ITEM;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt("HIDDEN") == 1) {
					PromotionItem item = new PromotionItem();
					item.setId(resultSet.getInt("ID"));
					item.setItem(new Item(resultSet.getInt("ITEM_ID")));
					item.setPromotion(new Promotion(resultSet.getInt("PROMOTION_ID")));
					item.setHidden(resultSet.getInt("HIDDEN"));
					result.add(item);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}
	//khong hiện hidden =0(ẩn)
	public List<PromotionItem> getPromotionItemBy_Item_ID(int item_id) {
		List<PromotionItem> result = new ArrayList<PromotionItem>();
		try (Connection connection =connectionDB.connect()){
			String sql="SELECT * FROM PROMOTION_ITEM WHERE ITEM_ID=?";
			PreparedStatement preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setInt(1, item_id);
			ResultSet resultSet =preparedStatement.executeQuery();
			while(resultSet.next()) {
				if (resultSet.getInt("HIDDEN") == 1) {
					PromotionItem item = new PromotionItem();
					item.setId(resultSet.getInt("ID"));
					item.setItem(new Item(resultSet.getInt("ITEM_ID")));
					item.setPromotion(new Promotion(resultSet.getInt("PROMOTION_ID")));
					item.setHidden(resultSet.getInt("HIDDEN"));
					result.add(item);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	//khong hiện hidden =0; 
	//dùng để làm bảng chọn
	public List<PromotionItem> getPromotionItemBy_List_Item_IDs(List<Integer> itemId) {
	    List<PromotionItem> result = new ArrayList<>();
	    try (Connection connection = connectionDB.connect()) {
	        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM PROMOTION_ITEM WHERE ");
	        
	        // OR từng item_id
	        for (int i = 0; i < itemId.size(); i++) {
	            if (i > 0) {
	                sqlBuilder.append(" OR ");
	            }
	            sqlBuilder.append("ITEM_ID = ?");
	        }
	        
	        PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString());
	        
	        // Thiết lập các tham số các item_id trong danh sách
	        for (int i = 0; i < itemId.size(); i++) {
	            preparedStatement.setInt(i + 1, itemId.get(i));
	        }
	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        while (resultSet.next()) {
	            if (resultSet.getInt("HIDDEN") == 1) {
	                PromotionItem item = new PromotionItem();
	                item.setId(resultSet.getInt("ID"));
	                item.setItem(new Item(resultSet.getInt("ITEM_ID")));
	                item.setPromotion(new Promotion(resultSet.getInt("PROMOTION_ID")));
	                item.setHidden(resultSet.getInt("HIDDEN"));
	                result.add(item);
	            }
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	// hiện hidden =0; hiển thị tất cả  
	//dùng để làm nhập mã code 
	public List<PromotionItem> getAllPromotionItemBy_List_Item_IDs(List<Integer> itemId) {
	    List<PromotionItem> result = new ArrayList<>();
	    try (Connection connection = connectionDB.connect()) {
	        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM PROMOTION_ITEM WHERE ");
	        
	        // OR từng item_id
	        for (int i = 0; i < itemId.size(); i++) {
	            if (i > 0) {
	                sqlBuilder.append(" OR ");
	            }
	            sqlBuilder.append("ITEM_ID = ?");
	        }
	        
	        PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString());
	        
	        // Thiết lập các tham số các item_id trong danh sách
	        for (int i = 0; i < itemId.size(); i++) {
	            preparedStatement.setInt(i + 1, itemId.get(i));
	        }
	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        while (resultSet.next()) {
	            
	                PromotionItem item = new PromotionItem();
	                item.setId(resultSet.getInt("ID"));
	                item.setItem(new Item(resultSet.getInt("ITEM_ID")));
	                item.setPromotion(new Promotion(resultSet.getInt("PROMOTION_ID")));
	                item.setHidden(resultSet.getInt("HIDDEN"));
	                result.add(item);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	public static void main(String[] args) {
		DBPromotionItem dbPromotionItem = new DBPromotionItem();
		List<Integer> itemIds = Arrays.asList(1, 2, 3, 4);
		List<PromotionItem> result = new ArrayList<>();
//		result=dbPromotionItem.getAllPromotionItemBy_Item_IDs(itemIds);
//		for(PromotionItem item:result) {
//			System.out.println(item.toString());
//		}
	}

}
