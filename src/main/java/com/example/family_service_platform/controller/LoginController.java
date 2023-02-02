package com.example.family_service_platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.family_service_platform.bean.TblUserRecord;
import com.example.family_service_platform.returnJson.Permission;
import com.example.family_service_platform.returnJson.Permissions;
import com.example.family_service_platform.returnJson.ReturnObject;
import com.example.family_service_platform.returnJson.UserInfo;
import com.example.family_service_platform.service.LoginService;
import com.example.family_service_platform.bean.TblUserRecord;
import com.example.family_service_platform.returnJson.Permission;
import com.example.family_service_platform.returnJson.Permissions;
import com.example.family_service_platform.returnJson.ReturnObject;
import com.example.family_service_platform.returnJson.UserInfo;
import com.example.family_service_platform.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {},allowCredentials = "true")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/auth/2step-code")
    public Boolean test(){
        System.out.println("前端框架自带的一个验证规则，写不写无所谓");
        return true;
    }

    @RequestMapping("/auth/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        System.out.println("login");
        TblUserRecord tblUserRecord = loginService.login(username,password);
        tblUserRecord.setToken(tblUserRecord.getUserName());
        //将用户数据写入到session中
        session.setAttribute("userRecord",tblUserRecord);
        ReturnObject returnObject = new ReturnObject(tblUserRecord);
        return JSONObject.toJSONString(returnObject);
    }

    @RequestMapping("/user/info")
    public String getInfo(HttpSession session){
        TblUserRecord tblUserRecord = (TblUserRecord) session.getAttribute("userRecord");
        //获取模块信息
        String[] split = tblUserRecord.getTblRole().getRolePrivileges().split("-");
        //创建权限集合对象
        Permissions permissions = new Permissions();
        //向权限集合对象中添加具体的权限
        List<Permission> permissionList = new ArrayList<>();
        for (String s : split) {
            permissionList.add(new Permission(s));
        }
        permissions.setPermissions(permissionList);
        //设置返回值的result
        UserInfo userInfo = new UserInfo(tblUserRecord.getUserName(),permissions);
        return JSONObject.toJSONString(new ReturnObject(userInfo));
    }

    @RequestMapping("/auth/logout")
    public void logOut(HttpSession session){
        System.out.println("logout");
        session.invalidate();
    }
}
