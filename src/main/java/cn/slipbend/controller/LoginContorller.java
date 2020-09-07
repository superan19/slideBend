package cn.slipbend.controller;

import cn.slipbend.model.User;
import cn.slipbend.service.LoginService;
import cn.slipbend.util.AppMD5Util;
import cn.slipbend.util.SMSsend;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: AnZX
 * @Date:
 * @Description:用户登录模块
 */
@RestController
//@PropertySource(value = {"classpath:const.properties"})
@RequestMapping("/login")
@Api(value = "App接口", description = "登录的接口")
public class LoginContorller {
    @Autowired
    private LoginService loginService;

    /*@RequestMapping("/sendSMS")
    public void sendSMS(String phone){
        SMSsend.send(phone);
    }*/
    /*@RequestMapping("/phone")
    public Object phone(String code){
        Map<String,Object> res = new HashMap<>();
        res.put("success",true);
        if(code.equals(SMSsend._code.toString())) {
            res.put("msg","正确");
        }else{
            res.put("msg","失败");
        }
        return res;
    }*/

    @RequestMapping("/sendSMS")
    @ApiOperation(value = "发送验证码", httpMethod = "POST",notes = "通过手机号发送验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "phone",value = "手机号",required = true,dataType = "String"),
    })
    public Object sendSMS(String phone){
        SMSsend.send(phone);
        Map<String,Object> res = new HashMap<>();
        res.put("smsCode",SMSsend._code.toString());
        return res;
    }

    @RequestMapping("/loginByPassword")
    @ApiOperation(value = "密码登录", httpMethod = "POST",notes = "通过手机号和密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "phone",value = "手机号",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "password",value = "密码",required = true,dataType = "String")
    })
    public Object loginByPassword(String phone, String password){
        password = AppMD5Util.getMD52(password);
        User user = loginService.loginByPassword(phone,password);
        Map<String,Object> res = new HashMap<>();
        res.put("user",user);
        if(user!=null){
            res.put("msg","登录成功");
            return res;
        }else {
            res.put("msg","登录失败,用户名或密码错误");
            return res;
        }
    }

    @RequestMapping("/loginBySmscode")
    @ApiOperation(value = "验证码登录", httpMethod = "POST",notes = "通过手机号和验证码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "phone",value = "手机号",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "smsCode",value = "短信验证码",required = true,dataType = "String")
    })
    public Object smsLogin(String phone,String smsCode){
        Map<String,Object> res = new HashMap<>();
        if(StringUtils.equals(smsCode,SMSsend._code.toString())){
            //查看手机号是否注册 没有则自动注册
            User user = loginService.findUserByPhone(phone);
            if(user==null){
                loginService.insertUser(phone);
                user = loginService.findUserByPhone(phone);
            }
            res.put("msg","登录成功");
            res.put("user",user);
            return res;
        }else {
            res.put("msg","登录失败,验证码不正确");
            return res;
        }
    }

    @RequestMapping("/updatePassword")
    @ApiOperation(value = "重置密码", httpMethod = "POST",notes = "修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "phone",value = "手机号",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "newPassword",value = "新密码",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "newPassword2",value = "再次确认密码",required = true,dataType = "String")
    })
    public Object updatePassword(String phone,String newPassword,String newPassword2){
        Map<String,Object> res = new HashMap<>();
        if(StringUtils.equals(newPassword,newPassword2)){
            //判断是否注册
            User user = loginService.findUserByPhone(phone);
            if(user!=null){
                newPassword = AppMD5Util.getMD52(newPassword);
                loginService.updatePassword(phone,newPassword);
                res.put("msg","修改成功");
                return res;
            }else{
                res.put("msg","请先注册");
                return res;
            }
        }else{
            res.put("msg","修改失败,两次密码不一致");
            return res;
        }
    }

    @RequestMapping("/updateImageAndUsername")
    @ApiOperation(value = "完善头像和用戶名", httpMethod = "POST",notes = "完善头像和用戶名")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "imageUrl",value = "图片路径",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "username",value = "用户昵称",required = true,dataType = "String"),
    })
    public Object updateImageAndUsername(String id,String imageUrl,String username){
        return loginService.updateImageAndUsername(id,imageUrl,username);
    }

    @RequestMapping("/updateSexAndAge")
    @ApiOperation(value = "完善性别年龄", httpMethod = "POST",notes = "完善性别年龄")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "sex",value = "性别",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "age",value = "年龄",required = true,dataType = "Integer"),
    })
    public Object updateSexAndAge(String id,String sex,Integer age){
        return loginService.updateSexAndAge(id,sex,age);
    }

    @RequestMapping("/updateCarModel")
    @ApiOperation(value = "完善汽车和发动机型号", httpMethod = "POST",notes = "完善汽车和发动机型号")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "carModel",value = "汽车型号",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "engineModel",value = "发动机型号",required = true,dataType = "String"),
    })
    public Object updateCarModel(String id,String carModel,String engineModel){
        return loginService.updateCarModel(id,carModel,engineModel);
    }


}
