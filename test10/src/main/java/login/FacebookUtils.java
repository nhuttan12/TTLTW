package login;

import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import login.FacebookPojo;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

public class FacebookUtils {
	public static String getToken(String code) throws ClientProtocolException, IOException {
		String response = Request.Post(Facebook.FACEBOOK_LINK_GET_TOKEN)
				.bodyForm(Form.form().add("client_id", Facebook.FACEBOOK_CLIENT_ID)
						.add("client_secret", Facebook.FACEBOOK_CLIENT_SECRET)
						.add("redirect_uri", Facebook.FACEBOOK_REDIRECT_URI).add("code", code).build())
				.execute().returnContent().asString();
		JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
		String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
		return accessToken;
	}

	public static FacebookPojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
		String link = Facebook.FACEBOOK_LINK_GET_USER_INFO + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();
		FacebookPojo facebookAcc = new Gson().fromJson(response, FacebookPojo.class);
		return facebookAcc;
	}

}
