package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Item;
import model.Order;
import model.Promotion;
import model.PromotionAll;
import model.PromotionCategory;
import model.PromotionItem;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import database.DBCart;
import database.DBOrder;
import database.DBPromotion;
import database.DBPromotionAll;
import database.DBPromotionCategory;
import database.DBPromotionItem;

/**
 * Servlet implementation class ShoppingCart
 * phục vụ cho việc hiển thị giỏ hàng 
 * nhấn vào button giỏ hàng ở header
 */
@WebServlet("/shoppingcart")
public class ShoppingCart extends HttpServlet {
	static double tongtien_giohang=0;
	DBPromotion dbPromotion=new DBPromotion();
	List<Integer> listALl_promotion_id=new ArrayList<Integer>();//danh sách các promotion_id của tất cả promotion có thể áp dụng
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		tongtien_giohang=0;
		HttpSession session = req.getSession();
		User a = (User) session.getAttribute("user");
		int userID;
		Item item=new Item();
		List<Cart> listCart = new ArrayList<Cart>();//danh sách các cart của cả gio hang
		List<Item> listItem=new ArrayList<Item>();
		DBCart dbCart = new DBCart();
	
		DBPromotionCategory dbPromotionCategory=new DBPromotionCategory();
		DBPromotionItem dbPromotionItem = new DBPromotionItem();
		DBPromotionAll dbPromotionAll=new DBPromotionAll();
		List<Promotion> list_Promotion=new ArrayList<Promotion>();
		List<PromotionCategory> list_promotionCategorie=new ArrayList<PromotionCategory>();
		List<PromotionItem> list_promotionItem=new ArrayList<PromotionItem>();
		List<PromotionAll> list_promotionAll=new ArrayList<PromotionAll>();
		List<Integer> list_item_id=new ArrayList<Integer>();//danh sách các item_id của cả gio hang
		List<Integer> List_category_id=new ArrayList<Integer>();//danh sách các category của cả giỏ hàng 
		List<Integer> list_promotion_id=new ArrayList<Integer>();//danh sách các promotion_id của hiển thị chọn
		
		
		//total_price tổng ca gio hang
		int total_quantity=0;//so luong san pham cả gio hang
		
		if (a == null) {
			req.setAttribute("erro", "bạn phải đăng nhập!");

			RequestDispatcher dispatcher = req.getRequestDispatcher("login");
			dispatcher.forward(req, resp);
		} else {
			userID=a.getId();
			try {
				listCart = dbCart.getListCartByUserIDForCart(userID);
				for(Cart cart:listCart) {
					tongtien_giohang+=cart.getTotalPrice();
					total_quantity+=cart.getQuantity();
					listItem.add(dbCart.getItemByCartId(cart.getId()));
				}
//				listItem=dbCart.getListItemByUserID(userID);
				for(Item item2:listItem) {
					list_item_id.add(item2.getId());
					List_category_id.add(item2.getCategory().getId());
				}
				list_promotionItem=dbPromotionItem.getAllPromotionItemBy_List_Item_IDs(list_item_id);
				for(PromotionItem promotionItem:list_promotionItem) {
					if(promotionItem.getHidden()==1) {
						list_promotion_id.add(promotionItem.getPromotion().getId());
					}
					listALl_promotion_id.add(promotionItem.getPromotion().getId());
					
				}
				list_promotionCategorie=dbPromotionCategory.getAllByListCategoryID(List_category_id);
				for(PromotionCategory promotionCategory:list_promotionCategorie) {
					if(promotionCategory.getHidden()==1) {
						list_promotion_id.add(promotionCategory.getPromotion().getId());
					}
					listALl_promotion_id.add(promotionCategory.getPromotion().getId());
				}
				list_promotionAll=dbPromotionAll.getAll();
				for(PromotionAll promotionAll:list_promotionAll) {
					if(promotionAll.getHidden()==1) {
						list_promotion_id.add(promotionAll.getPromotion().getId());
					}
					listALl_promotion_id.add(promotionAll.getPromotion().getId());
				}
				list_Promotion=dbPromotion.getAllByPromotionIdList(list_promotion_id);
				
			} catch (Exception e) {
				e.getMessage();
				
			}
			System.out.println(listItem.size() + "aaaaaaaaaa");
			req.setAttribute("listCart", listCart);
			req.setAttribute("listItem", listItem);
			req.setAttribute("tongtien_giohang", tongtien_giohang);
			req.setAttribute("total_quantity", total_quantity);
			req.setAttribute("list_Promotion", list_Promotion);//tổng các voucher có trong giỏ hầng theo các [sp,category, theo mọi sp]
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("shoppingcart.jsp");
			dispatcher.forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setContentType("application/json");
		String code=req.getParameter("code");
		System.out.println("codeeeeeeee"+code+"1");
		PrintWriter printWriter=resp.getWriter();
		 JsonObject jsonResponse = new JsonObject();
		  try {
		        if (code.trim().isEmpty()) {
		            jsonResponse.addProperty("total", tongtien_giohang);
		            System.out.println("tongtien_giohang" + tongtien_giohang);
		        } else {
		            int discount = Integer.parseInt(code);
		            double total_ = tongtien_giohang - (tongtien_giohang / 100 * discount);
		            jsonResponse.addProperty("total", total_);
		        }

		    } catch (NumberFormatException e) {
		        Promotion promotion = dbPromotion.getByProName(code);
		        if (promotion == null) {
		            jsonResponse.addProperty("error", "Không tìm thấy mã");
		        } else {
		            if (promotion.getQuantity() < 1) {
		                jsonResponse.addProperty("error", "Mã đã hết");
		            } else {
		                int dis = promotion.getDiscount();
		                double total_ = tongtien_giohang - (tongtien_giohang / 100 * dis);
		                jsonResponse.addProperty("total", total_);
		                
		            }
		        }
		    } catch (Exception e) {
		        jsonResponse.addProperty("error", "Đã xảy ra lỗi");
		    }

		    printWriter.write(jsonResponse.toString());
		}

}
