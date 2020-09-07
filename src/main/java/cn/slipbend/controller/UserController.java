package cn.slipbend.controller;

import cn.slipbend.model.User;
import cn.slipbend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: AnZX
 * @Date:
 * @Description:用户操作模块
 */
@RestController
@PropertySource(value = {"classpath:const.properties"})
@RequestMapping("/user")
@Api(value = "App接口", description = "操作用户的接口")
public class UserController {
    @Value("${IMAGES_PATH}")
    private String webRealPath;
    @Value("${LOCAL_IMAGES_PATH}")
    private String localRealPath;
    @Autowired
    private UserService userService;

    @RequestMapping("/updateUserInfo")
    @ApiOperation(value = "修改个人资料", httpMethod = "POST",notes = "修改个人资料")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "imageUrl",value = "图片路径",required = false,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "username",value = "用户名",required = false,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "sex",value = "性别",required = false,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "age",value = "年龄",required = false,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "hobby",value = "爱好",required = false,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "city",value = "城市",required = false,dataType = "String"),
    })
    public Object updateUserInfo(Integer id,String imageUrl,String username,String sex,Integer age,String hobby,String city){
        User user = new User();
        user.setId(id);
        user.setIcon(imageUrl);
        user.setUsername(username);
        user.setSex(sex);
        user.setAge(age);
        user.setHobby(hobby);
        user.setCity(city);
        System.out.println(user);
        return userService.updateUserInfo(user);
    }

    @RequestMapping("/updateCarInfo")
    @ApiOperation(value = "修改车辆信息", httpMethod = "POST",notes = "修改车辆信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "carModel",value = "汽车型号",required = false,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "carAge",value = "车龄",required = false,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "engineModel",value = "引擎型号",required = false,dataType = "Integer"),
    })
    public Object updateCarInfo(Integer id,String carModel,String carAge,String engineModel){
        return userService.updateCarInfo(id,carModel,carAge,engineModel);
    }

    @RequestMapping("/drivingLevel")
    @ApiOperation(value = "我的-行驶等级", httpMethod = "POST",notes = "查看我的行驶等级")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "Integer"),
    })
    public Object drivingLevel(Integer id){
        return userService.findUserDrivingLength(id);
    }

    @RequestMapping("/completePeople")
    @ApiOperation(value = "查看达成里程成就的人数", httpMethod = "POST",notes = "根据里程公里数查看达成里程成就的人数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "mileage",value = "里程公里数",required = true,dataType = "String"),
    })
    public Object completePeople(Double mileage){
        return userService.findCompletePeopleByMileage(mileage);
    }

    @RequestMapping("/userFollow")
    @ApiOperation(value = "查看我的关注列表", httpMethod = "POST",notes = "查看我的关注列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "Integer"),
    })
    public Object userFollow(Integer id){
        return userService.findUserFollow(id);
    }

    @RequestMapping("/userFans")
    @ApiOperation(value = "查看我的粉丝列表", httpMethod = "POST",notes = "查看我的粉丝列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "Integer"),
    })
    public Object userFans(Integer id){
        return userService.findUserFans(id);
    }

    @RequestMapping("/userInfo")
    @ApiOperation(value = "查看用户个人信息", httpMethod = "POST",notes = "查看用户个人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "Integer"),
    })
    public Object userInfo(Integer id){
        return userService.findUserInfo(id);
    }
}
