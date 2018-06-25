package com.fit2cloud.sdk;


import com.domain.Result;
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
			Result result = new Result();
			result.setResultCode(true);
			result.setMessage("修改成功！");
			return  result;
		} else {
			Result result = new Result();
			result.setResultCode(false);
			result.setMessage(responseString);
			return result;
		}
	}

}
