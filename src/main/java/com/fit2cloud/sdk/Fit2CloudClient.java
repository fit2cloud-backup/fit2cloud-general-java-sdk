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
import com.fit2cloud.sdk.model.Script;
import com.fit2cloud.sdk.model.Server;
import com.fit2cloud.sdk.model.ViewScriptlog;
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
	
	/**
	 * pageSize和pageNum可以不传,不传则返回所有脚本列表
	 * 
	 * @param pageSize
	 * @param pageNum
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<Script> getScripts(Integer pageSize, Integer pageNum) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/scripts");
		if(pageSize != null && pageSize.intValue() > 0) {
			request.addBodyParameter("pageSize", String.valueOf(pageSize));
		}
		if(pageNum != null && pageNum.intValue() > 0) {
			request.addBodyParameter("pageNum", String.valueOf(pageNum));
		}
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		System.out.println("responseString >> "+responseString);
		if (code==200){
			Type listType = new TypeToken<ArrayList<Script>>() {}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
			
		}else{
			throw new Fit2CloudException(responseString);
		}
	}
	
	public Script getScript (long scriptId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/script/"+scriptId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			return new GsonBuilder().create().fromJson(responseString, Script.class);
		}else{
			throw new Fit2CloudException(responseString);
		}
	}
	
	public Long addScript(String name, String description, String scriptText) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/script/add");
		request.addBodyParameter("name", name);
		request.addBodyParameter("description", description);
		request.addBodyParameter("scriptText", scriptText);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			return Long.parseLong(responseString);
		}else{
			throw new Fit2CloudException(response.getBody());
		}
	}
	
	public boolean editScript(long scriptId, String description, String scriptText) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/script/"+scriptId+"/update");
		request.addBodyParameter("description", description);
		request.addBodyParameter("scriptText", scriptText);
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
	
	public boolean deleteScript(long scriptId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/script/"+scriptId+"/delete");
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
	
	/**
	 * 所有搜索条件均非必要, 若所有搜索条件均为null或0,则返回该用户下所有执行日志
	 * 
	 * @param clusterId
	 * @param clusterRoleId
	 * @param serverId
	 * @param scriptId
	 * @param status
	 * @param pageSize
	 * @param pageNum
	 * @param sort
	 * @param order
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<ViewScriptlog> getLoggings(Long clusterId, Long clusterRoleId, Long serverId, Long scriptId, String status, Integer pageSize, Integer pageNum, String sort, String order) throws Fit2CloudException {
		StringBuffer requestParamSb = new StringBuffer();
		if(clusterId != null && clusterId.intValue() > 0) {
			requestParamSb.append("clusterId=");
			requestParamSb.append(clusterId);
			requestParamSb.append("&");
		}
		if(clusterRoleId != null && clusterRoleId.intValue() > 0) {
			requestParamSb.append("clusterRoleId=");
			requestParamSb.append(clusterRoleId);
			requestParamSb.append("&");
		}
		if(serverId != null && serverId.intValue() > 0) {
			requestParamSb.append("serverId=");
			requestParamSb.append(serverId);
			requestParamSb.append("&");
		}
		if(scriptId != null && scriptId.intValue() > 0) {
			requestParamSb.append("scriptId=");
			requestParamSb.append(scriptId);
			requestParamSb.append("&");
		}
		if(status != null && status.trim().length() > 0) {
			requestParamSb.append("status=");
			requestParamSb.append(status);
			requestParamSb.append("&");
		}
		if(sort != null && sort.trim().length() > 0) {
			requestParamSb.append("sort=");
			requestParamSb.append(sort);
			requestParamSb.append("&");
		}
		if(order != null && order.trim().length() > 0) {
			requestParamSb.append("order=");
			requestParamSb.append(order);
			requestParamSb.append("&");
		}
		String requestParam = requestParamSb.toString();
		if(requestParam != null && requestParam.endsWith("&")) {
			requestParam = requestParam.substring(0, requestParam.length() - 1);
		}
		
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/event/loggings?"+requestParam);
		if(pageSize != null && pageSize.intValue() > 0) {
			request.addBodyParameter("pageSize", String.valueOf(pageSize));
		}
		if(pageNum != null && pageNum.intValue() > 0) {
			request.addBodyParameter("pageNum", String.valueOf(pageNum));
		}
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code==200){
			Type listType = new TypeToken<ArrayList<ViewScriptlog>>() {}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		}else{
			throw new Fit2CloudException(responseString);
		}
	}
}
