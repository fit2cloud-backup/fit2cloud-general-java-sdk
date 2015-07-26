# FIT2CLOUD(通用版) Java SDK 使用指南

## 第一步：引用Jar包

### 方法1：使用Maven

```xml

<!-- repository -->
<repositories>
	<repository>
		<id>fit2cloud</id>
		<url>http://repository.fit2cloud.com/content/groups/public/</url>
		<releases>
			<enabled>true</enabled>
		</releases>
		<snapshots>
			<enabled>true</enabled>
		</snapshots>
	</repository>  
</repositories>

<!-- dependency -->
<dependency>
  <groupId>com.fit2cloud</groupId>
  <artifactId>fit2cloud-general-java-sdk</artifactId>
  <version>1.0</version>
</dependency>
```

### 方法2：直接下载Jar包

下载地址是：
http://repository.fit2cloud.com/content/repositories/fit2cloud-public/com/fit2cloud/fit2cloud-general-java-sdk/1.0/fit2cloud-general-java-sdk-1.0-jar-with-dependencies.jar

## 第二步：调用Fit2Cloud API

```java
		String apiKey = "你的consumer key";
		String apiSecret = "你的secret key";
		String restApiEndpoint = "你的restApiEndpoint";
		Fit2CloudClient client = new Fit2CloudClient(apiKey, apiSecret, restApiEndpoint);
		System.out.println("List clusters.....");
		System.out.println(client.getClusters());
```
API列表：

1. 获取集群列表
2. 获取单个集群的信息
3. 获取集群下面的虚机组列表
4. 获取指定条件下的虚机列表
5. 在指定集群的特定虚机组下,按指定的虚机启动模板启动虚机
6. 关闭指定的虚机
7. 在指定虚机中执行脚本
8. 获取脚本执行日志