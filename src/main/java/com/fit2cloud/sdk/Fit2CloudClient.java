package com.fit2cloud.sdk;


import com.google.gson.JsonObject;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class Fit2CloudClient {

	OAuthService service ;

	public Fit2CloudClient() {
	}

	public Object updatePassword(String restApiEndpoint ,String userName , String oldPassword , String newPassword) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET,
				restApiEndpoint + "/user/updatePassword?userName=" + userName + "&oldPassword=" + oldPassword +
		"&newPassword=" + newPassword);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			JsonObject result = new JsonObject();
			result.addProperty("resultCode",true);
			result.addProperty("message","修改成功！");
			return  result;
		} else {
			JsonObject result = new JsonObject();
			result.addProperty("resultCode",false);
			result.addProperty("message",responseString);
			return result;
		}
	}

}
