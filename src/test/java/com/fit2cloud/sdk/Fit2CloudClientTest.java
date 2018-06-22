package com.fit2cloud.sdk;

import org.junit.Test;

import javax.annotation.Resource;

public class Fit2CloudClientTest {

	private Fit2CloudClient client = new Fit2CloudClient();


	@Test
	public void testUpdatePassword() throws Exception {
		String restApiEndpoint = "http://localhost:6608";
//		String restApiEndpoint = "http://10.254.0.127:8080/rest/user/updatePassword";
		String username = "xujian@fit2cloud.com";
		String oldPassword = "5b2f13d1b260f80a9dae48e94d9f6486";
		String newPassword = "test";
		Object o = client.updatePassword(restApiEndpoint, username, oldPassword, newPassword);
		System.out.println(o);

//		new Fit2cloudC
	}

}
