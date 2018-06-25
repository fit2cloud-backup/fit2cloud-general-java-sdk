package com.fit2cloud.tools;

import com.fit2cloud.sdk.Fit2CloudClient;
import com.fit2cloud.sdk.Fit2CloudException;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

public class UpdateUserPasswordUtils {
    public static void main(String[] args)
            throws Fit2CloudException
    {
        if (args.length != 4)
        {
            System.out.println("请输入参数");
            System.exit(0);
        }
        String restApiEndpoint =  args[0];
        String userName = args[1];
        String oldPassword = args[2];
        String newPassword = args[3];

        if (StringUtils.isBlank(restApiEndpoint)){
            JsonObject result = new JsonObject();
            result.addProperty("resultCode",false);
            result.addProperty("message","访问节点参数为空！" );
            System.out.println(result );
        }
        if (StringUtils.isBlank(userName)){
            JsonObject result = new JsonObject();
            result.addProperty("resultCode",false);
            result.addProperty("message","用户参数为空！" );
            System.out.println(result );
        }
        if (StringUtils.isBlank(oldPassword)){
            JsonObject result = new JsonObject();
            result.addProperty("resultCode",false);
            result.addProperty("message","原始md5密码参数为空！" );
            System.out.println(result );
        }
        if (StringUtils.isBlank(newPassword)){
            JsonObject result = new JsonObject();
            result.addProperty("resultCode",false);
            result.addProperty("message","更新md5密码参数为空！" );
            System.out.println(result );
        }
        System.out.println("访问节点：  " + restApiEndpoint);
        System.out.println("更新账户： = " + userName);
        try {
            Fit2CloudClient fit2CloudClient = new Fit2CloudClient();
            Object result = fit2CloudClient.updatePassword(restApiEndpoint, userName, oldPassword, newPassword);
            System.out.println("result = " + result);
        }catch (Exception e){
            JsonObject result = new JsonObject();
            result.addProperty("resultCode",false);
            result.addProperty("message",e.getMessage());
            System.out.println("result = " + result);
        }
    }

}
