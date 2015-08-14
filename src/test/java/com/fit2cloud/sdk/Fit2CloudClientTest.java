package com.fit2cloud.sdk;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fit2cloud.sdk.model.Cluster;
import com.fit2cloud.sdk.model.ClusterParam;
import com.fit2cloud.sdk.model.ClusterRole;
import com.fit2cloud.sdk.model.Event;
import com.fit2cloud.sdk.model.Logging;
import com.fit2cloud.sdk.model.Script;
import com.fit2cloud.sdk.model.Server;
import com.google.gson.Gson;

public class Fit2CloudClientTest {
	
	private String apiKey;
	private String apiSecret;
	private String apiEndpoint;
	private Fit2CloudClient client;
	
	@Before
	public void setUp() throws Exception {
		this.apiKey = "YOUR_API_KEY";
		this.apiSecret = "YOUR_API_SECRET_KEY";
		this.apiEndpoint = "YOUR_API_ENDPOINT";
		this.client = new Fit2CloudClient(apiKey, apiSecret, apiEndpoint);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetClusters() throws Exception {
		System.out.println("List clusters...");
		List<Cluster> clusters = this.client.getClusters();
		assert clusters!=null && clusters.size()>0;
		System.out.println(clusters);
	}
	
	@Test
	public void testGetCluster() throws Exception {
		long clusterId = 2;
		Cluster cluster = this.client.getCluster(clusterId);
		System.out.println(cluster);
	}
	
	@Test
	public void testGetClusterRoles() throws Exception {
		long clusterId = 2;
		List<ClusterRole> clusterRoles = client.getClusterRoles(clusterId);
		assert clusterRoles!=null && clusterRoles.size()>0;
		System.out.println(clusterRoles);
	}
	
	@Test
	public void testGetServers() throws Exception {
		long clusterId = 2;
		long clusterRoleId = 2;
		String sort = "vm_id";
		String order = "desc";
		Integer pageSize = 3;
		Integer pageNum = 2;
		boolean showTerminated = true;
		List<Server> servers = client.getServers(clusterId, clusterRoleId, sort, order, pageSize, pageNum, showTerminated);
		assert servers!=null;
		System.out.println(servers);
	}
	
	@Test
	public void testGetServer() throws Exception {
		long serverId = 5;
		Server server = client.getServer(serverId);
		assert server!=null;
		System.out.println(server);
	}
	
	@Test
	public void testExecuteScriptInServer() throws Exception {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("#!/bin/bash").append("\n");
			sb.append("pwd").append("\n");
			sb.append("ls -l").append("\n");
			sb.append("date").append("\n");
			String scriptContent = sb.toString();
			long eventId = client.executeScript(446, scriptContent);
			System.out.println(eventId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetLoggingsByEventId() throws Exception {
		try {
			long eventId = 1l;
			List<Logging> logs = client.getLoggingsByEventId(eventId);
			System.out.println(new Gson().toJson(logs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetEvent() throws Exception {
		try {
			long eventId = 86l;
			Event event = client.getEvent(eventId);
			System.out.println(new Gson().toJson(event));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSetClusterParam() throws Exception {
		try {
			long clusterId = 2;
			String paramName = "testParam";
			String paramValue = "testdata";
			boolean result = client.setClusterParam(clusterId, paramName, paramValue);
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testGetClusterParams() throws Exception {
		try {
			long clusterId = 2;
			List<ClusterParam> result = client.getClusterParams(clusterId);
			System.out.println(new Gson().toJson(result));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testGetClusterParam() throws Exception {
		try {
			long clusterId = 2;
			String paramName = "testParam";
			ClusterParam result = client.getClusterParam(clusterId, paramName);
			System.out.println(new Gson().toJson(result));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testDeleteClusterParam() throws Exception {
		try {
			long clusterId = 2;
			String paramName = "testParam2";
			boolean result = client.deleteClusterParam(clusterId, paramName);
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testLaunchServer() throws Exception {
		try {
			Server server = client.launchServer(2, 2, 17);
			System.out.println(server);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testTerminateServer() throws Exception {
		try {
			boolean result = client.terminateServer(18);
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testStartServer() throws Exception {
		try {
			Server server = client.startServer(18);
			System.out.println(server);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testStopServer() throws Exception {
		try {
			boolean result = client.stopServer(18);
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testGetScripts() throws Exception {
		try {
			List<Script> scripts = client.getScripts(null, null);
			System.out.println(new Gson().toJson(scripts));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testGetScript() throws Exception {
		try {
			long scriptId = 1l;
			Script script = client.getScript(scriptId);
			System.out.println(script);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testAddScript() throws Exception {
		try {
			String scriptText = "#!/bin/bash\r\n\r\n#列出系统进程\r\nps aux\r\n";
			String description = "script description";
			String name = "testScript";
			Long scriptId = client.addScript(name, description, scriptText);
			System.out.println(scriptId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testEditScript() throws Exception {
		try {
			String scriptText = "#!/bin/bash\r\n\r\n#列出系统进程[编辑]\r\nps aux\r\n";
			String description = "edit script description";
			long scriptId = 1l;
			boolean result = client.editScript(scriptId, description, scriptText);
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testDeleteScript() throws Exception {
		try {
			long scriptId = 1l;
			boolean result = client.deleteScript(scriptId);
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
