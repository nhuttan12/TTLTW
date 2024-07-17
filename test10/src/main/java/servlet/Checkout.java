package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Item;
import model.Order;
import model.OrderDetail;
import model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import database.DBCart;
import database.DBOderDetail;
import database.DBOrder;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final double STORE_LATITUDE = 10.870057; // Tọa độ của Trường Đại học Nông Lâm Thành phố Hồ Chí Minh
//	private static final double STORE_LONGITUDE = 106.803247; // Tọa độ của Trường Đại học Nông Lâm Thành phố Hồ Chí
//																// Minh
//	private static final double MAX_DISTANCE_KM = 5.0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Checkout() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();

		User user = (User) httpSession.getAttribute("user");
		String phone = req.getParameter("phone");
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		if (user == null) {
			req.setAttribute("erro", "bạn phải đăng nhập !");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} else {
			System.out.println("tới đây");
			if (checkName(name) == null && checkAddress(address) == null && checkPhone(phone) == null) {
				DBCart dbCart = new DBCart();
				DBOrder dbOrder = new DBOrder();
				DBOderDetail dbOrderDetail = new DBOderDetail();
				List<OrderDetail> listOrderDetail = new ArrayList<OrderDetail>();
				List<Cart> listCart;
				try {
//					dbOrder.addOrder(user.getId(), name, phone, address);
					listCart = dbCart.getListCartByUserIDForCart(user.getId());// danh sách các cart của cả gio hang
					if (listCart.size() > 0) {
						if (listCart.get(listCart.size() - 1).getStatus() == 0) {
							Order o = dbOrder.addOrder(user.getId(), name, phone, address);
							for (Cart c : listCart) {
								OrderDetail od = dbOrderDetail.addDetail(o.getOrderId(), c.getId());// thêm chi tiết đơn
																									// hàng cho từng đơn
																									// hàng
								listOrderDetail.add(od);
								dbCart.updateStatus(c.getId(), 1);// cart đã thêm vào đơn hàng thì chuyển status
							}
						}

					}
					req.setAttribute("listod", listOrderDetail);
					req.getRequestDispatcher("oderHistory").forward(req, resp);
				} catch (SQLException e) {
					System.out.println("ngoại lệ ở đây");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				req.setAttribute("er", true);
				if (checkName(name) != null) {
					req.setAttribute("nameer", checkName(name));
//					System.out.println("mmmmmmmmmmmmmmmmmmm" + checkUser(USER_NAME));
				}
				if (checkAddress(address) != null) {
					req.setAttribute("addresser", checkAddress(address));
				}
				if (checkPhone(phone) != null) {
					req.setAttribute("phoneer", checkPhone(phone));
				}

				req.setAttribute("name", name);
				req.setAttribute("phone", phone);
				req.setAttribute("address", address);
				req.getRequestDispatcher("shoppingcart").forward(req, resp);
			}

		}

	}

	public String checkName(String name) {
		String status = null;
		if (name == null) {
			status = "không được để trống tên người nhận";
		}
		return status;

	}

	public String checkPhone(String phone) {

		if (phone.matches("^0\\d{9}$")) {
			return null;
		}
		return "số điện thoại phải gồm 10 số từ 0-9 và bắt đầu bằng số 0";

	}

	public String checkAddress(String address) {
		String status = null;
//		try {
//			System.setProperty("http.proxyHost", "proxy.example.com");
//			System.setProperty("http.proxyPort", "8080");
//			System.setProperty("https.proxyHost", "proxy.example.com");
//			System.setProperty("https.proxyPort", "8080");
//			// Sử dụng Google Maps Geocoding API để chuyển đổi địa chỉ thành tọa độ
//			String apiKey = "AIzaSyBRWQZNz2atxl_xzdhmmfytBL3UQQaPL_E";
//			String geocodeUrl = "https://maps.googleapis.com/maps/api/geocode/json?address="
//					+ URLEncoder.encode(address, "UTF-8") + "&key=" + apiKey;
//			URL url = new URL(geocodeUrl);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//
//			int responseCode = conn.getResponseCode();
//			if (responseCode != HttpURLConnection.HTTP_OK) {
//				status = "Không thể kết nối đến Google Maps API. Mã lỗi: " + responseCode;
//				return status;
//			}
//
//			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			String inputLine;
//			StringBuilder content = new StringBuilder();
//			while ((inputLine = in.readLine()) != null) {
//				content.append(inputLine);
//			}
//			in.close();
//			conn.disconnect();
//
//			// In ra nội dung JSON để kiểm tra
//			System.out.println("Response from Google Maps API: " + content.toString());
//
//			// Sử dụng Gson để phân tích JSON
//			Gson gson = new Gson();
//			JsonObject jsonObject = gson.fromJson(content.toString(), JsonObject.class);
//			String apiStatus = jsonObject.get("status").getAsString();
//
//			if ("ZERO_RESULTS".equals(apiStatus)) {
//				status = "Không tìm thấy địa chỉ.";
//				return status;
//			} else if ("OVER_QUERY_LIMIT".equals(apiStatus)) {
//				status = "Vượt quá giới hạn số lượng yêu cầu.";
//				return status;
//			} else if ("REQUEST_DENIED".equals(apiStatus)) {
//				status = "Yêu cầu bị từ chối. Kiểm tra API key và quyền truy cập.";
//				return status;
//			} else if ("INVALID_REQUEST".equals(apiStatus)) {
//				status = "Yêu cầu không hợp lệ.";
//				return status;
//			} else if ("UNKNOWN_ERROR".equals(apiStatus)) {
//				status = "Lỗi không xác định từ API.";
//				return status;
//			}
//
//			if (!"OK".equals(apiStatus)) {
//				status = "Địa chỉ không hợp lệ.";
//				return status;
//			}

			// Kiểm tra địa chỉ trống
			if (address == null || address.trim().isEmpty()) {
				status = "Địa chỉ không được để trống.";
				return status;
			}

//			// Nếu địa chỉ hợp lệ, kiểm tra khoảng cách
//			JsonObject location = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject()
//					.getAsJsonObject("geometry").getAsJsonObject("location");
//			double latitude = location.get("lat").getAsDouble();
//			double longitude = location.get("lng").getAsDouble();
//
//			// Tính khoảng cách bằng công thức Haversine
//			double distance = haversine(STORE_LATITUDE, STORE_LONGITUDE, latitude, longitude);
//			if (distance > MAX_DISTANCE_KM) {
//				status = "Địa chỉ vượt quá khoảng cách cho phép.";
//			} else {
//				status = "Địa chỉ hợp lệ và nằm trong khoảng cách cho phép.";
//			}
//
//		} catch (IOException e) {
//			status = "Lỗi kết nối với Google Maps API: " + e.getMessage();
//		} catch (Exception e) {
//			status = "Đã xảy ra lỗi khi kiểm tra địa chỉ: " + e.getMessage();
//		} finally {
//			// Xóa cấu hình Proxy nếu không còn cần thiết
//			System.clearProperty("http.proxyHost");
//			System.clearProperty("http.proxyPort");
//			System.clearProperty("https.proxyHost");
//			System.clearProperty("https.proxyPort");
//		}

		return status;
	}

//	private double haversine(double lat1, double lon1, double lat2, double lon2) {
//		final int R = 6371; // Bán kính Trái Đất tính bằng km
//		double latDistance = Math.toRadians(lat2 - lat1);
//		double lonDistance = Math.toRadians(lon2 - lon1);
//		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
//				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
//		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//		return R * c;
//	}

	public static void main(String[] args) {

		Checkout checker = new Checkout();
		String address = "1600 Amphitheatre Parkway, Mountain View, CA";
		String result = checker.checkAddress(address);
		System.out.println(result);
	}

}
