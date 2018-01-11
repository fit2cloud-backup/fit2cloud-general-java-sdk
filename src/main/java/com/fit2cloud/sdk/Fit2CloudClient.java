package com.fit2cloud.sdk;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.fit2cloud.sdk.model.Application;
import com.fit2cloud.sdk.model.ApplicationDeployment;
import com.fit2cloud.sdk.model.ApplicationDeploymentEventLog;
import com.fit2cloud.sdk.model.ApplicationDeploymentLog;
import com.fit2cloud.sdk.model.ApplicationRepo;
import com.fit2cloud.sdk.model.ApplicationRevision;
import com.fit2cloud.sdk.model.CloudCredential;
import com.fit2cloud.sdk.model.Cluster;
import com.fit2cloud.sdk.model.ClusterParam;
import com.fit2cloud.sdk.model.ClusterRole;
import com.fit2cloud.sdk.model.ClusterRoleAlertLogging;
import com.fit2cloud.sdk.model.CmdbVm;
import com.fit2cloud.sdk.model.ContactGroup;
import com.fit2cloud.sdk.model.Event;
import com.fit2cloud.sdk.model.GroupEnv;
import com.fit2cloud.sdk.model.KeyPair;
import com.fit2cloud.sdk.model.KeyPassword;
import com.fit2cloud.sdk.model.LaunchConfiguration;
import com.fit2cloud.sdk.model.Logging;
import com.fit2cloud.sdk.model.Metric;
import com.fit2cloud.sdk.model.MetricTop;
import com.fit2cloud.sdk.model.PortMonitor;
import com.fit2cloud.sdk.model.Script;
import com.fit2cloud.sdk.model.Server;
import com.fit2cloud.sdk.model.ServerMetric;
import com.fit2cloud.sdk.model.ServiceCatalogOrder;
import com.fit2cloud.sdk.model.Tag;
import com.fit2cloud.sdk.model.ViewScriptlog;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Fit2CloudClient {

	OAuthService service;
	private String restApiEndpoint;
	private String executeScriptInServerUrl;
	private String getLoggingUrl;

	/**
	 * @param consumerKey
	 *            FIT2CLOUD用户的consumerKey,可以通过FIT2CLOUD控制台的"帐号信息"中获取
	 * @param secret
	 *            FIT2CLOUD用户的SecretKey,可以通过FIT2CLOUD控制台的"帐号信息"中获取
	 * @param restApiUrl
	 *            FIT2CLOUD用户的API Endpoint,可以通过FIT2CLOUD控制台的"帐号信息"中获取
	 */
	public Fit2CloudClient(String consumerKey, String secret, String restApiUrl) {
		if (restApiUrl != null && restApiUrl.endsWith("/")) {
			restApiUrl = restApiUrl.substring(0, restApiUrl.length() - 1);
		}
		restApiEndpoint = restApiUrl;
		executeScriptInServerUrl = String.format("%s/executescript/server", restApiEndpoint);
		getLoggingUrl = String.format("%s/event/loggings/", restApiEndpoint);
		service = new ServiceBuilder().provider(Fit2CloudApi.class).apiKey(consumerKey).apiSecret(secret).build();
	}

	/**
	 * 获取当前用户所有集群信息
	 * 
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<Cluster> getClusters() throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/clusters");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<Cluster>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定集群信息
	 * 
	 * @param clusterId
	 *            集群ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public Cluster getCluster(long clusterId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/cluster/" + clusterId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, Cluster.class);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定集群下所有虚机组信息
	 * 
	 * @param clusterId
	 *            集群ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<ClusterRole> getClusterRoles(long clusterId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/cluster/" + clusterId + "/roles");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<ClusterRole>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定虚机组信息
	 * 
	 * @param clusterRoleId
	 *            虚机组ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public ClusterRole getClusterRole(long clusterRoleId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/clusterrole/" + clusterRoleId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, ClusterRole.class);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 所有参数均非必须, 若所有参数均为null, 则返回当前用户拥有的所有虚机
	 * 
	 * @param clusterId
	 *            集群ID,(可选)
	 * @param clusterRoleId
	 *            虚机组ID,(可选)
	 * @param sort
	 *            排序字段,(可选)
	 * @param order
	 *            排序方式,(可选)
	 * @param pageSize
	 *            分页大小,(可选,默认9999)
	 * @param pageNum
	 *            分页编号,(可选,默认1)
	 * @param showTerminated
	 *            是否显示已关闭虚机
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<Server> getServers(Long clusterId, Long clusterRoleId, String sort, String order, Integer pageSize,
			Integer pageNum, boolean showTerminated) throws Fit2CloudException {

		StringBuffer requestParamSb = new StringBuffer();
		if (clusterId != null && clusterId.intValue() > 0) {
			requestParamSb.append("clusterId=");
			requestParamSb.append(clusterId);
			requestParamSb.append("&");
		}
		if (clusterRoleId != null && clusterRoleId.intValue() > 0) {
			requestParamSb.append("clusterRoleId=");
			requestParamSb.append(clusterRoleId);
			requestParamSb.append("&");
		}
		if (sort != null && sort.trim().length() > 0) {
			requestParamSb.append("sort=");
			requestParamSb.append(sort.trim());
			requestParamSb.append("&");
		}
		if (order != null && order.trim().length() > 0) {
			requestParamSb.append("order=");
			requestParamSb.append(order.trim());
			requestParamSb.append("&");
		}
		if (pageSize != null && pageSize.intValue() > 0) {
			requestParamSb.append("pageSize=");
			requestParamSb.append(pageSize);
			requestParamSb.append("&");
		}
		if (pageNum != null && pageNum.intValue() > 0) {
			requestParamSb.append("pageNum=");
			requestParamSb.append(pageNum);
			requestParamSb.append("&");
		}
		String requestParam = requestParamSb.toString();
		if (requestParam != null && requestParam.endsWith("&")) {
			requestParam = requestParam.substring(0, requestParam.length() - 1);
		}
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/servers?" + requestParam);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<Server>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定虚机信息
	 * 
	 * @param serverId
	 *            虚机ID
	 * @return
	 * @throws Fit2CloudException
	 */
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

	/**
	 * 在指定虚机上执行指定脚本
	 * 
	 * @param serverId
	 *            虚机ID
	 * @param scriptContent
	 *            脚本内容
	 * @return 返回执行脚本事件ID, 可根据此ID获取返回的所有执行日志
	 * @throws Fit2CloudException
	 */
	public long executeScript(long serverId, String scriptContent) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, executeScriptInServerUrl);
		request.addBodyParameter("serverId", String.valueOf(serverId));
		request.addBodyParameter("scriptContent", scriptContent);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return Long.parseLong(responseString);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取事件返回的所有日志信息(如执行脚本事件)
	 * 
	 * @param eventId
	 *            事件ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<Logging> getLoggingsByEventId(long eventId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, getLoggingUrl + eventId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<Logging>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定事件信息, 可以获取事件的响应代码/执行时间等信息
	 * 
	 * @param eventId
	 *            事件ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public Event getEvent(long eventId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/event/" + eventId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, Event.class);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 创建虚机
	 * 
	 * @param clusterId
	 *            虚机所在的集群
	 * @param clusterRoleId
	 *            虚机所在的虚机组
	 * @param launchConfigurationId
	 *            创建虚机所使用的模板
	 * @return 创建后的虚机信息
	 * @throws Fit2CloudException
	 */
	public Server launchServer(long clusterId, long clusterRoleId, long launchConfigurationId)
			throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/launchserver/cluster/" + clusterId
				+ "/clusterrole/" + clusterRoleId + "?launchConfigurationId=" + launchConfigurationId);
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

	/**
	 * 创建虚机. 此接口将立刻返回虚机ID,而后台将异步创建虚机. 之后通过返回的虚机信息中的ID来查询完整的虚机信息
	 * 
	 * @param clusterId
	 *            虚机所在的集群
	 * @param clusterRoleId
	 *            虚机所在的虚机组
	 * @param launchConfigurationId
	 *            创建虚机所使用的模板
	 * @return
	 * @throws Fit2CloudException
	 */
	public Server launchServerAsync(long clusterId, long clusterRoleId, long launchConfigurationId)
			throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/launchserver/async/cluster/" + clusterId
				+ "/clusterrole/" + clusterRoleId + "?launchConfigurationId=" + launchConfigurationId);
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

	/**
	 * 删除虚机
	 * 
	 * @param serverId
	 *            虚机ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public boolean terminateServer(long serverId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/terminateserver/server/" + serverId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return "true".equals(responseString);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	/**
	 * 启动处于停止中状态的虚机
	 * 
	 * @param serverId
	 *            虚机ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public Server startServer(long serverId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/startserver/server/" + serverId);
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

	/**
	 * 停止虚机
	 * 
	 * @param serverId
	 *            虚机ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public boolean stopServer(long serverId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/stopserver/server/" + serverId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return "true".equals(responseString);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	/**
	 * 获取指定集群的集群参数
	 * 
	 * @param clusterId
	 *            集群ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<ClusterParam> getClusterParams(long clusterId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/cluster/" + clusterId + "/params");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<ClusterParam>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);

		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定集群下指定名称的集群参数
	 * 
	 * @param clusterId
	 *            集群ID
	 * @param name
	 *            集群参数名称
	 * @return
	 * @throws Fit2CloudException
	 */
	public ClusterParam getClusterParam(long clusterId, String name) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET,
				restApiEndpoint + "/cluster/" + clusterId + "/param?name=" + name);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, ClusterParam.class);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 设置集群参数, 若之前无此参数,则添加; 若有则替换
	 * 
	 * @param clusterId
	 *            集群ID
	 * @param name
	 *            集群参数名称
	 * @param value
	 *            集群参数值
	 * @return
	 * @throws Fit2CloudException
	 */
	public boolean setClusterParam(long clusterId, String name, String value) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/cluster/" + clusterId + "/param");
		request.addBodyParameter("name", name);
		request.addBodyParameter("value", value);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return "true".equals(responseString);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	/**
	 * 删除指定集群的集群参数
	 * 
	 * @param clusterId
	 *            集群ID
	 * @param name
	 *            集群参数名称
	 * @return
	 * @throws Fit2CloudException
	 */
	public boolean deleteClusterParam(long clusterId, String name) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST,
				restApiEndpoint + "/cluster/" + clusterId + "/param/delete?name=" + name);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return "true".equals(responseString);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	/**
	 * pageSize和pageNum可以不传,不传则返回所有脚本列表
	 * 
	 * @param pageSize
	 *            分页大小,(可选,默认9999)
	 * @param pageNum
	 *            分页编号,(可选,默认1)
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<Script> getScripts(Integer pageSize, Integer pageNum) throws Fit2CloudException {
		StringBuffer requestParamSb = new StringBuffer();
		if (pageSize != null && pageSize.intValue() > 0) {
			requestParamSb.append("pageSize=");
			requestParamSb.append(pageSize);
			requestParamSb.append("&");
		}
		if (pageNum != null && pageNum.intValue() > 0) {
			requestParamSb.append("pageNum=");
			requestParamSb.append(pageNum);
			requestParamSb.append("&");
		}
		String requestParam = requestParamSb.toString();
		if (requestParam != null && requestParam.endsWith("&")) {
			requestParam = requestParam.substring(0, requestParam.length() - 1);
		}
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/scripts?" + requestParam);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<Script>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);

		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定脚本信息
	 * 
	 * @param scriptId
	 *            脚本ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public Script getScript(long scriptId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/script/" + scriptId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, Script.class);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 添加脚本
	 * 
	 * @param name
	 *            脚本名称
	 * @param description
	 *            脚本描述
	 * @param scriptText
	 *            脚本内容
	 * @return 脚本ID
	 * @throws Fit2CloudException
	 */
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
		if (code == 200) {
			return Long.parseLong(responseString);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	/**
	 * 编辑指定脚本
	 * 
	 * @param scriptId
	 *            脚本ID
	 * @param description
	 *            脚本描述
	 * @param scriptText
	 *            脚本内容
	 * @return
	 * @throws Fit2CloudException
	 */
	public boolean editScript(long scriptId, String description, String scriptText) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/script/" + scriptId + "/update");
		request.addBodyParameter("description", description);
		request.addBodyParameter("scriptText", scriptText);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return "true".equals(responseString);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	/**
	 * 删除指定脚本
	 * 
	 * @param scriptId
	 *            脚本ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public boolean deleteScript(long scriptId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/script/" + scriptId + "/delete");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return "true".equals(responseString);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	/**
	 * 所有搜索条件均非必要, 若所有搜索条件均为null或0,则返回该用户下所有执行日志
	 * 
	 * @param clusterId
	 *            集群ID,(可选)
	 * @param clusterRoleId
	 *            虚机组ID,(可选)
	 * @param serverId
	 *            虚机ID,(可选)
	 * @param scriptId
	 *            脚本ID,(可选)
	 * @param status
	 *            执行日志状态,(可选, 取值范围 : success | failed | expired)
	 * @param pageSize
	 *            分页大小,(可选,默认9999)
	 * @param pageNum
	 *            分页编号,(可选,默认1)
	 * @param sort
	 *            排序字段,(可选)
	 * @param order
	 *            排序方式,(可选)
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<ViewScriptlog> getLoggings(Long clusterId, Long clusterRoleId, Long serverId, Long scriptId,
			String status, Integer pageSize, Integer pageNum, String sort, String order) throws Fit2CloudException {
		StringBuffer requestParamSb = new StringBuffer();
		if (clusterId != null && clusterId.intValue() > 0) {
			requestParamSb.append("clusterId=");
			requestParamSb.append(clusterId);
			requestParamSb.append("&");
		}
		if (clusterRoleId != null && clusterRoleId.intValue() > 0) {
			requestParamSb.append("clusterRoleId=");
			requestParamSb.append(clusterRoleId);
			requestParamSb.append("&");
		}
		if (serverId != null && serverId.intValue() > 0) {
			requestParamSb.append("serverId=");
			requestParamSb.append(serverId);
			requestParamSb.append("&");
		}
		if (scriptId != null && scriptId.intValue() > 0) {
			requestParamSb.append("scriptId=");
			requestParamSb.append(scriptId);
			requestParamSb.append("&");
		}
		if (status != null && status.trim().length() > 0) {
			requestParamSb.append("status=");
			requestParamSb.append(status);
			requestParamSb.append("&");
		}
		if (sort != null && sort.trim().length() > 0) {
			requestParamSb.append("sort=");
			requestParamSb.append(sort);
			requestParamSb.append("&");
		}
		if (order != null && order.trim().length() > 0) {
			requestParamSb.append("order=");
			requestParamSb.append(order);
			requestParamSb.append("&");
		}
		if (pageSize != null && pageSize.intValue() > 0) {
			requestParamSb.append("pageSize=");
			requestParamSb.append(pageSize);
			requestParamSb.append("&");
		}
		if (pageNum != null && pageNum.intValue() > 0) {
			requestParamSb.append("pageNum=");
			requestParamSb.append(pageNum);
			requestParamSb.append("&");
		}
		String requestParam = requestParamSb.toString();
		if (requestParam != null && requestParam.endsWith("&")) {
			requestParam = requestParam.substring(0, requestParam.length() - 1);
		}

		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/event/loggings?" + requestParam);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<ViewScriptlog>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定资源的标签列表
	 * 
	 * @param clusterId
	 *            集群ID,(可选)
	 * @param clusterRoleId
	 *            虚机组ID,(可选)
	 * @param serverId
	 *            虚机ID,(可选)
	 * @param tagName
	 *            标签名称,(可选)
	 * @param pageSize
	 *            分页大小,(可选,默认9999)
	 * @param pageNum
	 *            分页编号,(可选,默认1)
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<Tag> getTags(Long clusterId, Long clusterRoleId, Long serverId, String tagName, Integer pageSize,
			Integer pageNum) throws Fit2CloudException {
		StringBuffer requestParamSb = new StringBuffer();
		if (clusterId != null && clusterId.intValue() > 0) {
			requestParamSb.append("clusterId=");
			requestParamSb.append(clusterId);
			requestParamSb.append("&");
		}
		if (clusterRoleId != null && clusterRoleId.intValue() > 0) {
			requestParamSb.append("clusterRoleId=");
			requestParamSb.append(clusterRoleId);
			requestParamSb.append("&");
		}
		if (serverId != null && serverId.intValue() > 0) {
			requestParamSb.append("serverId=");
			requestParamSb.append(serverId);
			requestParamSb.append("&");
		}
		if (tagName != null && tagName.trim().length() > 0) {
			requestParamSb.append("tagName=");
			requestParamSb.append(tagName.trim());
			requestParamSb.append("&");
		}
		if (pageSize != null && pageSize.intValue() > 0) {
			requestParamSb.append("pageSize=");
			requestParamSb.append(pageSize);
			requestParamSb.append("&");
		}
		if (pageNum != null && pageNum.intValue() > 0) {
			requestParamSb.append("pageNum=");
			requestParamSb.append(pageNum);
			requestParamSb.append("&");
		}
		String requestParam = requestParamSb.toString();
		if (requestParam != null && requestParam.endsWith("&")) {
			requestParam = requestParam.substring(0, requestParam.length() - 1);
		}
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/tags?" + requestParam);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<Tag>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 给指定虚机设置标签. 若无则新增标签; 若有则替换
	 * 
	 * @param serverId
	 *            虚机ID
	 * @param tagName
	 *            标签名称
	 * @param tagValue
	 *            标签值
	 * @return
	 * @throws Fit2CloudException
	 */
	public Tag saveTag(Long serverId, String tagName, String tagValue) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/tags/save");
		if (serverId != null && serverId.intValue() > 0) {
			request.addBodyParameter("serverId", String.valueOf(serverId));
		}
		if (tagName != null && tagName.trim().length() > 0) {
			request.addBodyParameter("tagName", tagName.trim());
		}
		if (tagValue != null && tagValue.trim().length() > 0) {
			request.addBodyParameter("tagValue", tagValue.trim());
		}
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, Tag.class);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	/**
	 * 删除指定虚机的标签
	 * 
	 * @param serverId
	 *            虚机ID
	 * @param tagName
	 *            标签名称
	 * @return
	 * @throws Fit2CloudException
	 */
	public boolean deleteTag(Long serverId, String tagName) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/tags/delete");
		if (serverId != null && serverId.intValue() > 0) {
			request.addBodyParameter("serverId", String.valueOf(serverId));
		}
		if (tagName != null && tagName.trim().length() > 0) {
			request.addBodyParameter("tagName", tagName.trim());
		}
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return "true".equals(responseString);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	/**
	 * 按标签获取虚机列表
	 * 
	 * @param clusterId
	 *            集群ID
	 * @param clusterRoleId
	 *            虚机组ID
	 * @param tagName
	 *            标签名称(必要参数)
	 * @param tagValue
	 *            标签值
	 * @param pageSize
	 *            分页大小
	 * @param pageNum
	 *            分页编号
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<Server> getServersByTag(Long clusterId, Long clusterRoleId, String tagName, String tagValue,
			Integer pageSize, Integer pageNum) throws Fit2CloudException {
		StringBuffer requestParamSb = new StringBuffer();
		if (clusterId != null && clusterId.intValue() > 0) {
			requestParamSb.append("clusterId=");
			requestParamSb.append(clusterId);
			requestParamSb.append("&");
		}
		if (clusterRoleId != null && clusterRoleId.intValue() > 0) {
			requestParamSb.append("clusterRoleId=");
			requestParamSb.append(clusterRoleId);
			requestParamSb.append("&");
		}
		if (tagName != null && tagName.trim().length() > 0) {
			requestParamSb.append("tagName=");
			requestParamSb.append(tagName);
			requestParamSb.append("&");
		}
		if (tagValue != null && tagValue.trim().length() > 0) {
			requestParamSb.append("tagValue=");
			try {
				requestParamSb.append(URLEncoder.encode(tagValue, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			requestParamSb.append("&");
		}
		if (pageSize != null && pageSize.intValue() > 0) {
			requestParamSb.append("pageSize=");
			requestParamSb.append(pageSize);
			requestParamSb.append("&");
		}
		if (pageNum != null && pageNum.intValue() > 0) {
			requestParamSb.append("pageNum=");
			requestParamSb.append(pageNum);
			requestParamSb.append("&");
		}
		String requestParam = requestParamSb.toString();
		if (requestParam != null && requestParam.endsWith("&")) {
			requestParam = requestParam.substring(0, requestParam.length() - 1);
		}
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/tags/servers?" + requestParam);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<Server>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 添加应用版本
	 * 
	 * @param name
	 *            应用版本名称
	 * @param description
	 *            应用版本描述
	 * @param applicationName
	 *            所属应用名称
	 * @param repositoryName
	 *            所属仓库名称
	 * @param location
	 *            应用版本文件的下载路径
	 * @param md5
	 *            应用版本文件的md5值
	 * @return
	 * @throws Fit2CloudException
	 */
	public ApplicationRevision addApplicationRevision(String name, String description, String applicationName,
			String repositoryName, String location, String md5) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/deploy/app/revision/add.json");
		request.addBodyParameter("revName", name);
		request.addBodyParameter("revDescription", description);
		request.addBodyParameter("appName", applicationName);
		if (repositoryName != null) {
			request.addBodyParameter("repoName", repositoryName);
		}
		request.addBodyParameter("location", location);
		if (md5 != null && md5.trim().length() > 0) {
			request.addBodyParameter("md5", md5);
		}
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, ApplicationRevision.class);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	/**
	 * 添加代码部署任务
	 * 
	 * @param applicationName
	 *            应用名称
	 * @param applicationRevisionName
	 *            应用版本名称
	 * @param clusterName
	 *            集群名称
	 * @param clusterRoleName
	 *            虚机组名称
	 * @param serverId
	 *            虚机ID
	 * @param deployPolicy
	 *            部署策略 (allAtOnce | halfAtATime | oneAtATime)
	 * @param description
	 *            部署任务描述
	 * @return
	 * @throws Fit2CloudException
	 */
	public ApplicationDeployment addDeployment(String applicationName, String applicationRevisionName,
			String clusterName, String clusterRoleName, Long serverId, String deployPolicy, String description)
			throws Fit2CloudException {
		return addDeployment(applicationName, applicationRevisionName, clusterName, clusterRoleName, serverId,
				deployPolicy, description, null);
	}

	/**
	 * 添加代码部署任务
	 * 
	 * @param applicationName
	 *            应用名称
	 * @param applicationRevisionName
	 *            应用版本名称
	 * @param clusterName
	 *            集群名称
	 * @param clusterRoleName
	 *            虚机组名称
	 * @param serverId
	 *            虚机ID
	 * @param deployPolicy
	 *            部署策略 (allAtOnce | halfAtATime | oneAtATime)
	 * @param description
	 *            部署任务描述
	 * @param contactGroupId
	 *            通知组ID，null表示不需要接收部署通知邮件
	 * @return
	 * @throws Fit2CloudException
	 */
	public ApplicationDeployment addDeployment(String applicationName, String applicationRevisionName,
			String clusterName, String clusterRoleName, Long serverId, String deployPolicy, String description,
			Long contactGroupId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST,
				restApiEndpoint + "/deploy/app/revision/deployment/add.json");
		request.addBodyParameter("appName", applicationName);
		request.addBodyParameter("revName", applicationRevisionName);
		request.addBodyParameter("clusterName", clusterName);
		if (clusterRoleName != null && clusterRoleName.trim().length() > 0) {
			request.addBodyParameter("clusterRoleName", clusterRoleName);
		}
		if (serverId != null && serverId.longValue() > 0) {
			request.addBodyParameter("serverId", String.valueOf(serverId));
		}
		request.addBodyParameter("deployPolicy", deployPolicy);
		request.addBodyParameter("description", description);
		if (contactGroupId != null && contactGroupId.longValue() > 0) {
			request.addBodyParameter("contactGroupId", String.valueOf(contactGroupId));
		}
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, ApplicationDeployment.class);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	/**
	 * 添加代码部署任务
	 * 
	 * @param applicationId
	 *            应用ID
	 * @param applicationRevisionId
	 *            应用版本ID
	 * @param clusterName
	 *            集群名称
	 * @param clusterRoleName
	 *            虚机组名称
	 * @param serverId
	 *            虚机ID
	 * @param deployPolicy
	 *            部署策略 (allAtOnce | halfAtATime | oneAtATime)
	 * @param description
	 *            部署任务描述
	 * @return
	 * @throws Fit2CloudException
	 */
	public ApplicationDeployment addDeployment(Long applicationId, Long applicationRevisionid, String clusterName,
			String clusterRoleName, Long serverId, String deployPolicy, String description) throws Fit2CloudException {
		return addDeployment(applicationId, applicationRevisionid, clusterName, clusterRoleName, serverId, deployPolicy,
				description, null);
	}

	/**
	 * 添加代码部署任务
	 * 
	 * @param applicationId
	 *            应用ID
	 * @param applicationRevisionId
	 *            应用版本ID
	 * @param clusterName
	 *            集群名称
	 * @param clusterRoleName
	 *            虚机组名称
	 * @param serverId
	 *            虚机ID
	 * @param deployPolicy
	 *            部署策略 (allAtOnce | halfAtATime | oneAtATime)
	 * @param description
	 *            部署任务描述
	 * @param contactGroupId
	 *            通知组ID，null表示不需要接收部署通知邮件
	 * @return
	 * @throws Fit2CloudException
	 */
	public ApplicationDeployment addDeployment(Long applicationId, Long applicationRevisionid, String clusterName,
			String clusterRoleName, Long serverId, String deployPolicy, String description, Long contactGroupId)
			throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST,
				restApiEndpoint + "/deploy/app/revision/deployment/add.json");
		request.addBodyParameter("applicationId", String.valueOf(applicationId));
		request.addBodyParameter("applicationRevisionId", String.valueOf(applicationRevisionid));
		request.addBodyParameter("clusterName", clusterName);
		if (clusterRoleName != null && clusterRoleName.trim().length() > 0) {
			request.addBodyParameter("clusterRoleName", clusterRoleName);
		}
		if (serverId != null && serverId.longValue() > 0) {
			request.addBodyParameter("serverId", String.valueOf(serverId));
		}
		request.addBodyParameter("deployPolicy", deployPolicy);
		request.addBodyParameter("description", description);
		if (contactGroupId != null && contactGroupId.longValue() > 0) {
			request.addBodyParameter("contactGroupId", String.valueOf(contactGroupId));
		}
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, ApplicationDeployment.class);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	/**
	 * 获取部署任务信息
	 * 
	 * @param deploymentId
	 * @return
	 * @throws Fit2CloudException
	 */
	public ApplicationDeployment getDeployment(Long deploymentId) throws Fit2CloudException {
		if (deploymentId == null || deploymentId == 0) {
			throw new Fit2CloudException("invalid parameter!");
		}

		String url = String.format("%s/deploy/app/revision/deployment/get.json?deploymentId=%s", restApiEndpoint,
				deploymentId);
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, ApplicationDeployment.class);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	/**
	 * 获取代码部署记录列表
	 * 
	 * @param clusterId
	 *            集群ID,可选
	 * @param applicationId
	 *            应用ID,可选
	 * @param pageSize
	 *            分页大小,可选
	 * @param pageNum
	 *            分页编号,可选
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<ApplicationDeployment> getDeployments(Long clusterId, Long applicationId, Integer pageSize,
			Integer pageNum) throws Fit2CloudException {
		StringBuffer requestParamSb = new StringBuffer();
		if (clusterId != null && clusterId.intValue() > 0) {
			requestParamSb.append("clusterId=");
			requestParamSb.append(clusterId);
			requestParamSb.append("&");
		}
		if (applicationId != null && applicationId.intValue() > 0) {
			requestParamSb.append("applicationId=");
			requestParamSb.append(applicationId);
			requestParamSb.append("&");
		}
		if (pageSize != null && pageSize.intValue() > 0) {
			requestParamSb.append("pageSize=");
			requestParamSb.append(pageSize);
			requestParamSb.append("&");
		}
		if (pageNum != null && pageNum.intValue() > 0) {
			requestParamSb.append("pageNum=");
			requestParamSb.append(pageNum);
			requestParamSb.append("&");
		}
		String requestParam = requestParamSb.toString();
		if (requestParam != null && requestParam.endsWith("&")) {
			requestParam = requestParam.substring(0, requestParam.length() - 1);
		}

		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/deployments?" + requestParam);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<ApplicationDeployment>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定代码部署任务中,每台主机部署的情况
	 * 
	 * @param deploymentId
	 *            代码部署任务ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<ApplicationDeploymentLog> getDeploymentLogs(Long deploymentId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/deploymentlog/" + deploymentId + "/list");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<ApplicationDeploymentLog>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定代码部署任务中,每台主机部署的情况
	 *
	 * @param deploymentId
	 *            代码部署任务ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<ApplicationDeploymentEventLog> getDeploymentEventLogs(Long deploymentId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET,
				restApiEndpoint + "/deploymentEventlog/" + deploymentId + "/list");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<ApplicationDeploymentEventLog>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取应用信息
	 * 
	 * @param applicationName
	 *            应用名称
	 * @return
	 * @throws Fit2CloudException
	 */
	public Application getApplication(String applicationName) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET,
				restApiEndpoint + "/deploy/app/search?name=" + applicationName);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, Application.class);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取应用信息
	 * 
	 * @param applicationId
	 *            应用ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public Application getApplication(Long applicationId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/deploy/app/search?id=" + applicationId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, Application.class);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取代码部署应用列表
	 * 
	 * @param pageSize
	 *            分页大小,(可选,默认9999)
	 * @param pageNum
	 *            分页编号,(可选,默认1)
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<Application> getApplicationList(Integer pageSize, Integer pageNum) throws Fit2CloudException {
		StringBuffer requestParamSb = new StringBuffer();
		if (pageSize != null && pageSize.intValue() > 0) {
			requestParamSb.append("pageSize=");
			requestParamSb.append(pageSize);
			requestParamSb.append("&");
		}
		if (pageNum != null && pageNum.intValue() > 0) {
			requestParamSb.append("pageNum=");
			requestParamSb.append(pageNum);
			requestParamSb.append("&");
		}
		String requestParam = requestParamSb.toString();
		if (requestParam != null && requestParam.endsWith("&")) {
			requestParam = requestParam.substring(0, requestParam.length() - 1);
		}

		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/deploy/apps?" + requestParam);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<List<Application>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取应用仓库信息
	 * 
	 * @param applicationRepoName
	 *            应用仓库名称
	 * @return
	 * @throws Fit2CloudException
	 */
	public ApplicationRepo getApplicationRepo(String applicationRepoName) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET,
				restApiEndpoint + "/deploy/repo/search?name=" + applicationRepoName);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, ApplicationRepo.class);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取应用仓库信息
	 * 
	 * @param applicationRepoId
	 *            应用仓库ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public ApplicationRepo getApplicationRepo(Long applicationRepoId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET,
				restApiEndpoint + "/deploy/repo/search?id=" + applicationRepoId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, ApplicationRepo.class);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取代码部署仓库列表
	 * 
	 * @param pageSize
	 *            分页大小,(可选,默认9999)
	 * @param pageNum
	 *            分页编号,(可选,默认1)
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<ApplicationRepo> getApplicationRepoList(Integer pageSize, Integer pageNum) throws Fit2CloudException {
		StringBuffer requestParamSb = new StringBuffer();
		if (pageSize != null && pageSize.intValue() > 0) {
			requestParamSb.append("pageSize=");
			requestParamSb.append(pageSize);
			requestParamSb.append("&");
		}
		if (pageNum != null && pageNum.intValue() > 0) {
			requestParamSb.append("pageNum=");
			requestParamSb.append(pageNum);
			requestParamSb.append("&");
		}
		String requestParam = requestParamSb.toString();
		if (requestParam != null && requestParam.endsWith("&")) {
			requestParam = requestParam.substring(0, requestParam.length() - 1);
		}

		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/deploy/repos?" + requestParam);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<List<ApplicationRepo>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取监控数据排行锁支持的监控项列表
	 * 
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<Metric> getTopMetrics() throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/top/metrics");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<Metric>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定监控的监控排行数据
	 * 
	 * @param metricName
	 *            监控项名称,由getTopMetrics方法获取
	 * @param limit
	 *            排行数量限制,(可选,默认5)
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<MetricTop> getTopMetricData(String metricName, Integer limit) throws Fit2CloudException {
		StringBuffer requestParamSb = new StringBuffer();
		if (metricName != null && metricName.trim().length() > 0) {
			requestParamSb.append("metric=");
			requestParamSb.append(metricName);
			requestParamSb.append("&");
		}
		if (limit != null && limit.intValue() > 0) {
			requestParamSb.append("limit=");
			requestParamSb.append(limit);
		}
		String requestParam = requestParamSb.toString();
		if (requestParam != null && requestParam.endsWith("&")) {
			requestParam = requestParam.substring(0, requestParam.length() - 1);
		}
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/top/metric/data?" + requestParam);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<MetricTop>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * @param clusterId
	 *            集群序号(可选)
	 * @param clusterRoleId
	 *            虚机组序号(可选)
	 * @param serverId
	 *            虚机序号(可选)
	 * @param alertType
	 *            告警级别,(可选, 取值范围 : WARN | ERROR)
	 * @param alertStatus
	 *            告警状态,(可选, 取值范围 : showNull [告警中] | showIgnore [已忽略] |
	 *            showNotNull [已解除])
	 * @param pageSize
	 *            分页大小,(可选,默认9999)
	 * @param pageNum
	 *            分页编号,(可选,默认1)
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<ClusterRoleAlertLogging> getAlertLoggings(Long clusterId, Long clusterRoleId, Long serverId,
			String alertType, String alertStatus, Integer pageSize, Integer pageNum) throws Fit2CloudException {
		StringBuffer requestParamSb = new StringBuffer();
		if (clusterId != null && clusterId.intValue() > 0) {
			requestParamSb.append("clusterId=");
			requestParamSb.append(clusterId);
			requestParamSb.append("&");
		}
		if (clusterRoleId != null && clusterRoleId.intValue() > 0) {
			requestParamSb.append("clusterRoleId=");
			requestParamSb.append(clusterRoleId);
			requestParamSb.append("&");
		}
		if (serverId != null && serverId.intValue() > 0) {
			requestParamSb.append("serverId=");
			requestParamSb.append(serverId);
			requestParamSb.append("&");
		}
		if (alertType != null && alertType.trim().length() > 0) {
			requestParamSb.append("alertType=");
			requestParamSb.append(alertType.trim());
			requestParamSb.append("&");
		}
		if (alertStatus != null && alertStatus.trim().length() > 0) {
			requestParamSb.append("alertStatus=");
			requestParamSb.append(alertStatus.trim());
			requestParamSb.append("&");
		}
		if (pageSize != null && pageSize.intValue() > 0) {
			requestParamSb.append("pageSize=");
			requestParamSb.append(pageSize);
			requestParamSb.append("&");
		}
		if (pageNum != null && pageNum.intValue() > 0) {
			requestParamSb.append("pageNum=");
			requestParamSb.append(pageNum);
			requestParamSb.append("&");
		}
		String requestParam = requestParamSb.toString();
		if (requestParam != null && requestParam.endsWith("&")) {
			requestParam = requestParam.substring(0, requestParam.length() - 1);
		}
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/alerts?" + requestParam);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<List<ClusterRoleAlertLogging>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取虚机登录信息
	 * 
	 * @param serverId
	 *            必填, 虚机ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public KeyPassword getServerLoginInfo(long serverId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/server/" + serverId + "/logininfo");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, KeyPassword.class);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取云帐号列表
	 * 
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<CloudCredential> getCloudCredentials() throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/cloudcredentials");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<CloudCredential>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取虚机创建模板列表
	 * 
	 * @param cloudCredentialId
	 *            可选,云帐号ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<LaunchConfiguration> getLaunchconfigurations(Long cloudCredentialId) throws Fit2CloudException {
		StringBuffer requestParamSb = new StringBuffer();
		if (cloudCredentialId != null && cloudCredentialId.intValue() > 0) {
			requestParamSb.append("cloudCredentialId=");
			requestParamSb.append(cloudCredentialId);
			requestParamSb.append("&");
		}
		String requestParam = requestParamSb.toString();
		if (requestParam != null && requestParam.endsWith("&")) {
			requestParam = requestParam.substring(0, requestParam.length() - 1);
		}
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/launchconfigurations?" + requestParam);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<LaunchConfiguration>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取服务目录订单列表
	 * 
	 * @param status
	 *            查询条件：订单状态，可选值参考 @see ServiceCatalogOrderStatus (可选)
	 * @param sort
	 *            排序字段 (可选)
	 * @param order
	 *            排序方式 (可选)
	 * @param pageSize
	 *            分页大小,(可选,默认9999)
	 * @param pageNum
	 *            分页编号,(可选,默认1)
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<ServiceCatalogOrder> getServiceCatalogOrders(String status, String sort, String order, Integer pageSize,
			Integer pageNum) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/servicecatalog/orders?status=" + status
				+ "&sort=" + sort + "&order=" + order + "&pageSize=" + pageSize + "&pageNum=" + pageNum);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<ArrayList<ServiceCatalogOrder>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 更新知道服务订单状态
	 * 
	 * @param orderId
	 *            订单ID
	 * @param status
	 *            更新后的订单状态，可选值参见 @see ServiceCatalogOrderStatus
	 * @return
	 * @throws Fit2CloudException
	 */
	public ServiceCatalogOrder updateServiceCatalogOrder(long orderId, String status) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST,
				restApiEndpoint + "/servicecatalog/order/" + orderId + "/update?status=" + status);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, ServiceCatalogOrder.class);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	/**
	 * 获取通知组列表
	 * 
	 * @param pageSize
	 *            分页大小,(可选,默认9999)
	 * @param pageNum
	 *            分页编号,(可选,默认1)
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<ContactGroup> getContactGroupList(Integer pageSize, Integer pageNum) throws Fit2CloudException {
		StringBuffer requestParamSb = new StringBuffer();
		if (pageSize != null && pageSize.intValue() > 0) {
			requestParamSb.append("pageSize=");
			requestParamSb.append(pageSize);
			requestParamSb.append("&");
		}
		if (pageNum != null && pageNum.intValue() > 0) {
			requestParamSb.append("pageNum=");
			requestParamSb.append(pageNum);
			requestParamSb.append("&");
		}
		String requestParam = requestParamSb.toString();
		if (requestParam != null && requestParam.endsWith("&")) {
			requestParam = requestParam.substring(0, requestParam.length() - 1);
		}

		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/contactgroups?" + requestParam);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<List<ContactGroup>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定通知组信息
	 * 
	 * @param contactGroupId
	 *            通知组ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public ContactGroup getContactGroup(Long contactGroupId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/contactgroup/" + contactGroupId);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, ContactGroup.class);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定主机组下的所有监控项
	 * 
	 * @param clusterRoleId
	 *            主机组ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<KeyPair> getSupportedServerMetrics(Long clusterRoleId) throws Fit2CloudException {
		if (clusterRoleId == null || clusterRoleId <= 0) {
			throw new Fit2CloudException("请检查clusterRoleId的输入！");
		}

		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/metrics?clusterRoleId=" + clusterRoleId);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<List<KeyPair>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定条件的监控数据
	 * 
	 * @param serverId
	 *            主机ID
	 * @param metricName
	 *            监控项 (可选)，可选值参见{@link #getSupportedServerMetrics(Long)}
	 * @param startTime
	 *            查询开始时间点(可选)
	 * @param endTime
	 *            查询结束时间点(可选)
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<ServerMetric> getServerMetrics(Long serverId, String metricName, Long startTime, Long endTime)
			throws Fit2CloudException {
		if (serverId == null || serverId <= 0) {
			throw new Fit2CloudException("请检查serverId的输入！");
		}

		StringBuffer requestParamSb = new StringBuffer();
		requestParamSb.append("serverId=");
		requestParamSb.append(serverId);
		requestParamSb.append("&");

		if (metricName != null && metricName.trim().length() > 0) {
			requestParamSb.append("metricName=");
			requestParamSb.append(metricName);
			requestParamSb.append("&");
		}

		if (startTime != null && startTime.intValue() > 0) {
			requestParamSb.append("startTime=");
			requestParamSb.append(startTime);
			requestParamSb.append("&");
		}
		if (endTime != null && endTime.intValue() > 0) {
			requestParamSb.append("endTime=");
			requestParamSb.append(endTime);
			requestParamSb.append("&");
		}
		String requestParam = requestParamSb.toString();
		if (requestParam != null && requestParam.endsWith("&")) {
			requestParam = requestParam.substring(0, requestParam.length() - 1);
		}

		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/server/metrics?" + requestParam);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<List<ServerMetric>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取端口监控列表(端口监控间隔时间为5分钟）
	 * 
	 * @param pageSize
	 *            分页大小,(可选,默认9999)
	 * @param pageNum
	 *            分页编号,(可选,默认1)
	 * @return
	 * @throws Fit2CloudException
	 */
	public List<PortMonitor> getPortMonitors(Integer pageSize, Integer pageNum) throws Fit2CloudException {
		StringBuffer requestParamSb = new StringBuffer();
		if (pageSize != null && pageSize.intValue() > 0) {
			requestParamSb.append("pageSize=");
			requestParamSb.append(pageSize);
			requestParamSb.append("&");
		}
		if (pageNum != null && pageNum.intValue() > 0) {
			requestParamSb.append("pageNum=");
			requestParamSb.append(pageNum);
			requestParamSb.append("&");
		}
		String requestParam = requestParamSb.toString();
		if (requestParam != null && requestParam.endsWith("&")) {
			requestParam = requestParam.substring(0, requestParam.length() - 1);
		}

		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/monitor/ports?" + requestParam);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			Type listType = new TypeToken<List<PortMonitor>>() {
			}.getType();
			return new GsonBuilder().create().fromJson(responseString, listType);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定监控项的端口监控信息(端口监控间隔时间为5分钟）
	 * 
	 * @param pageSize
	 *            分页大小,(可选,默认9999)
	 * @param pageNum
	 *            分页编号,(可选,默认1)
	 * @return
	 * @throws Fit2CloudException
	 */
	public PortMonitor getPortMonitor(long portMonitorId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET,
				restApiEndpoint + "/monitor/port?portMonitorId=" + portMonitorId);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, PortMonitor.class);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取指定虚机的ssh登录url
	 * 
	 * @param serverId
	 *            虚机ID
	 * @return
	 * @throws Fit2CloudException
	 */
	public String getServerSSHUrl(long serverId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/server/" + serverId + "/openssh");
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return responseString;
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 获取当前工作空间信息
	 * 
	 * @return
	 * @throws Fit2CloudException
	 */
	public GroupEnv getGroupEnv() throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.GET, restApiEndpoint + "/group/info");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, GroupEnv.class);
		} else {
			throw new Fit2CloudException(responseString);
		}
	}

	/**
	 * 将自服务平台的虚机注册到devops平台
	 * 
	 * @param sfServerId
	 *            自服务屁股疼虚机ID
	 * @param cloudServerId
	 *            自服务屁股疼虚机对应的cloudServer ID
	 * @param installAgent
	 *            是否自动安装agent
	 * @param user
	 *            系统的登录用户名
	 * @param password
	 *            系统的登录密码
	 * @param key
	 *            系统的登录秘钥
	 * @param port
	 *            系统的登录端口
	 * @return
	 * @throws Fit2CloudException
	 */
	public Server registerServer(String sfServerId, Long cloudServerId, boolean installAgent, String user,
			String password, String key, Long port) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/sfserver/register");
		request.addBodyParameter("sfServerId", sfServerId);
		request.addBodyParameter("cloudServerId", String.valueOf(cloudServerId));
		request.addBodyParameter("installAgent", String.valueOf(installAgent));
		if (user != null && user.trim().length() > 0) {
			request.addBodyParameter("user", user);
		}
		if (password != null && password.trim().length() > 0) {
			request.addBodyParameter("password", password);
		}
		if (key != null && key.trim().length() > 0) {
			request.addBodyParameter("key", key);
		}
		if (port == null || port <= 0) {
			port = 22l;
		}
		request.addBodyParameter("port", String.valueOf(port));
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, Server.class);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	/**
	 * 将服务器注册到devops平台
	 *
	 * @param server
	 *            server对象
	 * @param installAgent
	 *            是否自动安装agent
	 * @param user
	 *            系统的登录用户名
	 * @param password
	 *            系统的登录密码
	 * @param key
	 *            系统的登录秘钥
	 * @param port
	 *            系统的登录端口
	 * @return
	 * @throws Fit2CloudException
	 */
	public Server registerServer(Server server, boolean installAgent, String user,
								 String password, String key, Long port) throws Fit2CloudException {
		// TODO
		// 交给徐工实现啦
		Server result = new Server();
		result.setId(new Random().nextLong());
		return result;
	}

	public CmdbVm registerCmdbServer(String sfServerId, Long cmdbServerId, boolean installAgent, String user,
			String password, String key, Long port) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/cmdbserver/register");
		request.addBodyParameter("sfServerId", sfServerId);
		request.addBodyParameter("cmdbServerId", String.valueOf(cmdbServerId));
		request.addBodyParameter("installAgent", String.valueOf(installAgent));
		if (user != null && user.trim().length() > 0) {
			request.addBodyParameter("user", user);
		}
		if (password != null && password.trim().length() > 0) {
			request.addBodyParameter("password", password);
		}
		if (key != null && key.trim().length() > 0) {
			request.addBodyParameter("key", key);
		}
		if (port == null || port <= 0) {
			port = 22l;
		}
		request.addBodyParameter("port", String.valueOf(port));
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, CmdbVm.class);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	public CmdbVm registerCmdbServer(Long cmdbServerId) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/cmdbserver/register");
		request.addBodyParameter("cmdbServerId", String.valueOf(cmdbServerId));
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		String responseString = response.getBody();
		if (code == 200) {
			return new GsonBuilder().create().fromJson(responseString, CmdbVm.class);
		} else {
			throw new Fit2CloudException(response.getBody());
		}
	}

	public boolean updateCluster(Long clusterId, String clusterName) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/cmdbserver/updateCluster");
		request.addBodyParameter("clusterId", String.valueOf(clusterId));
		request.addBodyParameter("clusterName", clusterName);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		if (code == 200) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateClusterRole(Long clusterRoleId, String clusterRoleName) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/cmdbserver/updateClusterRole");
		request.addBodyParameter("clusterRoleId", String.valueOf(clusterRoleId));
		request.addBodyParameter("clusterRoleName", clusterRoleName);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		if (code == 200) {
			return true;
		} else {
			return false;
		}
	}

	public boolean changeClusterAndRole(Long cmdbServerId, String clusterName, String clusterRoleName, String sshIp,
			Long sshPort, String sshUser, String sshPwd, String osType) throws Fit2CloudException {
		OAuthRequest request = new OAuthRequest(Verb.POST, restApiEndpoint + "/cmdbserver/changeClusterAndRole");
		request.addBodyParameter("cmdbServerId", String.valueOf(cmdbServerId));
		request.addBodyParameter("clusterName", clusterName);
		request.addBodyParameter("clusterRoleName", clusterRoleName);
		request.addBodyParameter("sshIp", sshIp);
		request.addBodyParameter("sshPort", String.valueOf(sshPort));
		request.addBodyParameter("sshUser", sshUser);
		request.addBodyParameter("sshPwd", sshPwd);
		request.addBodyParameter("osType", osType);
		request.setCharset("UTF-8");
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		Response response = request.send();
		int code = response.getCode();
		System.out.println(code);
		if (code == 200) {
			return true;
		} else {
			return false;
		}
	}

}
