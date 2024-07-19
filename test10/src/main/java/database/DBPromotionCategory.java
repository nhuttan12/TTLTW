package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Category;
import model.Item;
import model.Promotion;
import model.PromotionCategory;
import model.PromotionCategory;

public class DBPromotionCategory {
	//khong hiện hidden =0(ẩn)
		public List<PromotionCategory> getAll_NotHidden() {
			List<PromotionCategory> result = new ArrayList<PromotionCategory>();
			try (Connection connection = connectionDB.connect()) {
				String sql = "SELECT * FROM PROMOTION_CATEGORY;";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					if (resultSet.getInt("HIDDEN") == 1) {
						PromotionCategory item = new PromotionCategory();
						item.setId(resultSet.getInt("ID"));
						item.setCategory(new Category(resultSet.getInt("CATEGORY_ID")));
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
		public List<PromotionCategory> getByCategoryID_NotHidden(int category_id) {
			List<PromotionCategory> result = new ArrayList<PromotionCategory>();
			try (Connection connection =connectionDB.connect()){
				String sql="SELECT * FROM PROMOTION_CATEGORY WHERE CATEGORY_ID=?";
				PreparedStatement preparedStatement =connection.prepareStatement(sql);
				preparedStatement.setInt(1, category_id);
				ResultSet resultSet =preparedStatement.executeQuery();
				while(resultSet.next()) {
					if (resultSet.getInt("HIDDEN") == 1) {
						PromotionCategory item = new PromotionCategory();
						item.setId(resultSet.getInt("ID"));
						item.setCategory(new Category(resultSet.getInt("CATEGORY_ID")));
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
		public List<PromotionCategory> getByListCategoryID_NotHidden(List<Integer> category_id) {
		    List<PromotionCategory> result = new ArrayList<>();
		    try (Connection connection = connectionDB.connect()) {
		        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM PROMOTION_CATEGORY WHERE ");
		        
		        // OR từng item_id
		        for (int i = 0; i < category_id.size(); i++) {
		            if (i > 0) {
		                sqlBuilder.append(" OR ");
		            }
		            sqlBuilder.append("CATEGORY_ID = ?");
		        }
		        
		        PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString());
		        
		        // Thiết lập các tham số các item_id trong danh sách
		        for (int i = 0; i < category_id.size(); i++) {
		            preparedStatement.setInt(i + 1, category_id.get(i));
		        }
		        
		        ResultSet resultSet = preparedStatement.executeQuery();
		        
		        while (resultSet.next()) {
		            if (resultSet.getInt("HIDDEN") == 1) {
		                PromotionCategory item = new PromotionCategory();
		                item.setId(resultSet.getInt("ID"));
						item.setCategory(new Category(resultSet.getInt("CATEGORY_ID")));
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
		public List<PromotionCategory> getAllByListCategoryID(List<Integer> category_id) {
		    List<PromotionCategory> result = new ArrayList<>();
		    try (Connection connection = connectionDB.connect()) {
		        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM PROMOTION_CATEGORY WHERE ");
		        
		        // OR từng item_id
		        for (int i = 0; i < category_id.size(); i++) {
		            if (i > 0) {
		                sqlBuilder.append(" OR ");
		            }
		            sqlBuilder.append("CATEGORY_ID = ?");
		        }
		        
		        PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString());
		        
		        // Thiết lập các tham số các category_id trong danh sách
		        for (int i = 0; i < category_id.size(); i++) {
		            preparedStatement.setInt(i + 1, category_id.get(i));
		        }
		        
		        ResultSet resultSet = preparedStatement.executeQuery();
		        
		        while (resultSet.next()) {
		            
		                PromotionCategory item = new PromotionCategory();
		                item.setId(resultSet.getInt("ID"));
						item.setCategory(new Category(resultSet.getInt("CATEGORY_ID")));
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
			DBPromotionCategory category = new DBPromotionCategory();
			List<Integer> itemIds = Arrays.asList(1,  3, 4);
			List<PromotionCategory> categories = new ArrayList<PromotionCategory>();
			categories=category.getAllByListCategoryID(itemIds);
			for(PromotionCategory category2:categories) {
				System.out.println(category2.toString());
			}
		}

}
