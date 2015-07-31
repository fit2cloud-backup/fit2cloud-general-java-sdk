package com.fit2cloud.sdk;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.fit2cloud.sdk.model.Cluster;
import com.fit2cloud.sdk.model.ClusterParam;
import com.fit2cloud.sdk.model.ClusterRole;
import com.fit2cloud.sdk.model.Event;
import com.fit2cloud.sdk.model.Logging;
import com.fit2cloud.sdk.model.Server;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Fit2CloudClient {
	
	OAuthService service;
	private String restApiEndpoint;
	private String executeScriptInServerUrl;
	private String getLoggingUrl;
	
	public Fit2CloudClient(String consumerKey, String secret, String restApiUrl){
		if(restApiUrl != null && restApiUrl.endsWith("/")) {
			restApiUrl = restApiUrl.substring(0, restApiUrl.length()-1);
		}
		restApiEndpoint = restApiUrl;
		executeScriptInServerUrl = String.format("%s/executescript/server", restApiEndpoint);
		getLoggingUrl = String.format("%s/event/loggings/", restApiEndpoint);
		service = new ServiceBuilder().provider(Fit2CloudApi.class).apiKey(consumerKey).apiSecret(secret).build();
	}
	
	public List<Cluster> getClusters() throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/clusters");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			Type listType = new TypeToken<ArrayList<Cluster>>() {}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		}else{
			throw new Fit2CloudException(responseString);
		}
	}
	
	public Cluster getCluster(long clusterId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/cluster/" + clusterId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			return new GsonBuilder().create().fromJson(responseString, Cluster.class);
		}else{
			throw new Fit2CloudException(responseString);
		}
	}
	
	public List<ClusterRole> getClusterRoles(long clusterId) throws Fit2CloudException{
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint +  "/cluster/" + clusterId+ "/roles");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			Type listType = new TypeToken<ArrayList<ClusterRole>>() {}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		}else{
			throw new Fit2CloudException(responseString);
		}
	}
	
	/**
	 * 所有参数均非必须, 若所有参数均为null, 则返回当前用户拥有的所有虚机
	 * 
	 * @param clusterId
	 * @param clusterRoleId
	 * @param sort	排序字段
	 * @param order	排序方式
	 * @param pageSize	分页大小
	 * @param pageNum	分页编号
	 * @param showTerminated	是否显示已关闭虚机
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<Server> getServers(Long clusterId, Long clusterRoleId, String sort, String order, Integer pageSize, Integer pageNum, boolean showTerminated) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint
				+ "/servers?clusterId=" + clusterId + "&clusterRoleId="
				+ clusterRoleId + "&sort=" + sort + "&order=" + order
				+ "&pageSize=" + pageSize + "&pageNum=" + pageNum
				+ "&showTerminated=" + showTerminated);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			Type listType = new TypeToken<ArrayList<Server>>() {}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		}else{
			throw new Fit2CloudException(responseString);
		}
	}
	
	public Server getServer(long serverId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/server/" + serverId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, Server.class);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}
	
	public long executeScript(long serverId, String scriptContent)
			throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, executeScriptInServerUrl);
		request.addBodyParameter("serverId", String.valueOf(serverId));
		request.addBodyParameter("scriptContent", scriptContent);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			return Long.parseLong(responseString);
		}else{
			throw new Fit2CloudException(responseString);
		}
	}
	
	public List<Logging> getLoggingsByEventId(long eventId)
			throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, getLoggingUrl + eventId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			Type listType = new TypeToken<ArrayList<Logging>>() {}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		}else{
			throw new Fit2CloudException(responseString);
		}
	}
	
	public Event getEvent(long eventId)
			throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/event/"+eventId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			return new GsonBuilder().create().fromJson(responseString, Event.class);
		}else{
			throw new Fit2CloudException(responseString);
		}
	}
	
	public Server launchServer(long clusterId, long clusterRoleId, long launchConfigurationId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/launchserver/cluster/"+clusterId+"/clusterrole/"+clusterRoleId+"?launchConfigurationId="+launchConfigurationId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			return new GsonBuilder().create().fromJson(responseString, Server.class);
		}else{
			throw new Fit2CloudException(responseString);
		}
	}
	
	public boolean terminateServer(long serverId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/terminateserver/server/"+serverId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			return "true".equals(responseString);
		}else{
			throw new Fit2CloudException(response.getBody());
		}
	}
	
	public Server startServer(long serverId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/startserver/server/"+serverId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			return new GsonBuilder().create().fromJson(responseString, Server.class);
		}else{
			throw new Fit2CloudException(responseString);
		}
	}
	
	public boolean stopServer(long serverId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/stopserver/server/"+serverId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			return "true".equals(responseString);
		}else{
			throw new Fit2CloudException(response.getBody());
		}
	}
	
	public List<ClusterParam> getClusterParams(long clusterId)
			throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/cluster/"+clusterId+"/params");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			Type listType = new TypeToken<ArrayList<ClusterParam>>() {}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
			
		}else{
			throw new Fit2CloudException(responseString);
		}
	}
	
	public ClusterParam getClusterParam(long clusterId, String name)
			throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/cluster/"+clusterId+"/param?name="+name);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			return new GsonBuilder().create().fromJson(responseString, ClusterParam.class);
		}else{
			throw new Fit2CloudException(responseString);
		}
	}

	public boolean setClusterParam(long clusterId, String name, String value) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/cluster/"+clusterId+"/param");
		request.addBodyParameter("name", name);
		request.addBodyParameter("value", value);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			return "true".equals(responseString);
		}else{
			throw new Fit2CloudException(response.getBody());
		}
	}
	
	public boolean deleteClusterParam(long clusterId, String name) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/cluster/"+clusterId+"/param/delete?name="+name);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			return "true".equals(responseString);
		}else{
			throw new Fit2CloudException(response.getBody());
		}
	}
}
