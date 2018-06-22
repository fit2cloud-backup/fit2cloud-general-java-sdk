package com.fit2cloud.sdk;


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
			return  responseString;
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

}
