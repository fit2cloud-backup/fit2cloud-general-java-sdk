package com.fit2cloud.tools;

import com.domain.Result;
import com.fit2cloud.sdk.Fit2CloudClient;
import com.fit2cloud.sdk.Fit2CloudException;
import org.apache.commons.lang3.StringUtils;

public class UpdateUserPasswordUtils {
    public static void main(String[] args)
            throws Fit2CloudException
    {
        if (args.length != 4)
        {
            Result result = new Result();
            result.setResultCode(false);
            result.setMessage("请正确输入参数！");
            System.out.println("result = " + result);
            System.exit(0);
        }
        String restApiEndpoint =  args[0];
        String userName = args[1];
        String oldPassword = args[2];
        String newPassword = args[3];

        if (StringUtils.isBlank(restApiEndpoint)){
            Result result = new Result();
            result.setResultCode(false);
            result.setMessage("访问节点参数为空！");
            System.out.println(result);
        }
        if (StringUtils.isBlank(userName)){
            Result result = new Result();
            result.setResultCode(false);
            result.setMessage("用户参数为空！" );
            System.out.println(result);
        }
        if (StringUtils.isBlank(oldPassword)){
            Result result = new Result();
            result.setResultCode(false);
            result.setMessage("原始md5密码参数为空！");
            System.out.println(result);
        }
        if (StringUtils.isBlank(newPassword)){
            Result result = new Result();
            result.setResultCode(false);
            result.setMessage("更新md5密码参数为空！" );
            System.out.println(result);
        }
        try {
            Fit2CloudClient fit2CloudClient = new Fit2CloudClient();
            Object result = fit2CloudClient.updatePassword(restApiEndpoint, userName, oldPassword, newPassword);
            System.out.println(result);
        }catch (Exception e){
            Result result = new Result();
            result.setResultCode(false);
            result.setMessage(e.getMessage());
            System.out.println(result);
        }
    }

}
