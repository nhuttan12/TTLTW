package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Promotion;
import model.PromotionAll;

public class DBPromotionAll {
	//khong hiển thị hidden ==0(ẩn)
	//dung làm bảng chọn
	public List<PromotionAll> getAllHidden(){
		List<PromotionAll> result=new ArrayList<PromotionAll>();
		try(Connection connection =connectionDB.connect()) {
			String sql="SELECT * FROM PROMOTION_ALL WHERE HIDDEN=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 1);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				PromotionAll all = new PromotionAll();
				all.setId(resultSet.getInt("ID"));
				all.setPromotion(new Promotion(resultSet.getInt("PROMOTION_ID")));
				all.setHidden(resultSet.getInt("HIDDEN"));
				result.add(all);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	//hiển thị hidden ==0(ẩn); hiển thị tất cả
		//dung làm nhập code
		public List<PromotionAll> getAll(){
			List<PromotionAll> result=new ArrayList<PromotionAll>();
			try(Connection connection =connectionDB.connect()) {
				String sql="SELECT * FROM PROMOTION_ALL ";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					PromotionAll all = new PromotionAll();
					all.setId(resultSet.getInt("ID"));
					all.setPromotion(new Promotion(resultSet.getInt("PROMOTION_ID")));
					all.setHidden(resultSet.getInt("HIDDEN"));
					result.add(all);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return result;
		}
		public static void main(String[] args) {
			DBPromotionAll all = new DBPromotionAll();
			List<PromotionAll> result=new ArrayList<PromotionAll>();
			result=all.getAllHidden();
			for(PromotionAll all2 :result) {
				System.out.println(all2.toString());
			}
			
		}
		

}
