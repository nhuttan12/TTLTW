package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Promotion;

public class DBPromotion {
	public List<Promotion> getAllByPromotionIdList(List<Integer> promotionId){
		List<Promotion> result=new ArrayList<Promotion>();
		try(Connection connection =connectionDB.connect()) {
			String sql ="SELECT * FROM PROMOTION WHERE";
			for(int i=0;i<promotionId.size();i++) {
				if(i>0) {
					sql+=" OR ";
				}
				sql+= " ID=?";
			}
			PreparedStatement preparedStatement =connection.prepareStatement(sql);
			for(int i=0;i<promotionId.size();i++) {
				preparedStatement.setInt(i+1, promotionId.get(i));
			}
			ResultSet resultSet =preparedStatement.executeQuery();
			while(resultSet.next()) {
				Promotion promotion = new Promotion();
				promotion.setId(resultSet.getInt("ID"));
				promotion.setName(resultSet.getString("NAME"));
				promotion.setDescription(resultSet.getString("DESCRIPTION"));
				promotion.setQuantity(resultSet.getInt("QUANTITY"));
				promotion.setDiscount(resultSet.getInt("DISCOUNT"));
				result.add(promotion);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	public Promotion getByProName(String proName) {
		Promotion promotion = null;
		try(Connection connection =connectionDB.connect()) {
			String sql="SELECT * FROM PROMOTION WHERE NAME =?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, proName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				promotion=new Promotion();
				promotion.setId(resultSet.getInt("ID"));
				promotion.setName(resultSet.getString("NAME"));
				promotion.setDescription(resultSet.getString("DESCRIPTION"));
				promotion.setQuantity(resultSet.getInt("QUANTITY"));
				promotion.setDiscount(resultSet.getInt("DISCOUNT"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return promotion;
	}
	public static void main(String[] args) {
		DBPromotion dbPromotion  = new DBPromotion();
		List<Integer> integers = new ArrayList<Integer>();
		integers.add(1);
		integers.add(1);
		integers.add(1);
		integers.add(2);
//		List<Promotion> result=new ArrayList<Promotion>();
//		result=dbPromotion.getAllByPromotionIdList(integers);
//		for(Promotion promotion:result) {
//			System.out.println(promotion.toString());
//		}
		Promotion promotion = new Promotion();
		promotion=dbPromotion.getByProName("giamgiane");
		System.out.println(promotion);
				
				
	}

}
