package cn.slipbend.service.impl;

import cn.slipbend.dao.UserDao;
import cn.slipbend.model.User;
import cn.slipbend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Date:
 * @Description: 用户操作
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    //修改用户个人资料
    @Override
    public Object updateUserInfo(User user) {
        Map<String,Object> res = new HashMap<>();
        userDao.updateUserInfoById(user);
        res.put("msg","修改成功");
        //返回user信息
        User newUser = userDao.findUserById(user);
        res.put("user",newUser);
        return res;
    }

    //修改车辆信息
    @Override
    public Object updateCarInfo(Integer id, String carModel, String carAge, String engineModel) {
        Map<String,Object> res = new HashMap<>();
        userDao.updateCarInfo(id,carModel,carAge,engineModel);
        Date date = new Date();
        //新建日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //将日期对象格式化成指定格式并以String输出
        String updateTime = simpleDateFormat.format(date);
        res.put("msg","修改成功");
        res.put("updateTime",updateTime);
        return res;
    }

    //计算用户行驶总距离
    @Override
    public Object findUserDrivingLength(Integer id) {
        Map<String,Object> res = new HashMap<>();
        Integer length = userDao.findUserAllLength(id);
        res.put("alllength",length);
        return res;
    }
    //查找达成里程成就的人数
    @Override
    public Object findCompletePeopleByMileage(Double mileage) {
        Map<String,Object> res = new HashMap<>();
        Integer nums = userDao.findCompletePeopleByMileage(mileage);
        res.put("nums",nums);
        return res;
    }
    //查看用户关注列表
    @Override
    public Object findUserFollow(Integer id) {
        Map<String,Object> res = new HashMap<>();
        List<User> userList = userDao.findUserFollow(id);
        res.put("follow",userList);
        return res;
    }
    //查看用户粉丝列表
    @Override
    public Object findUserFans(Integer id) {
        Map<String,Object> res = new HashMap<>();
        List<User> userList = userDao.findUserFans(id);
        res.put("fans",userList);
        return res;
    }

    //查看用户个人信息
    //头像 用户名 行程数 关注数 粉丝数 动态数
    //行驶总里程 本周耗油 车辆型号 上次记录距今多少天
    @Override
    public Object findUserInfo(Integer id) {
        Map<String,Object> res = new HashMap<>();
        User user = userDao.findUsernameIconCarmodelByUserId(id);//用户名 头像 车辆型号
        Integer routeCount = userDao.findCountRoute(id);//行程次数
        Integer followCount = userDao.findCountFollow(id);//关注数
        Integer fansCount = userDao.findCountFans(id);//粉丝数
        Integer dynamicCount = userDao.findCountDynamic(id);//动态数
        Integer allLength = userDao.findUserAllLength(id);//总里程
        Integer allOil = userDao.findUserAllOil(id);//总油耗
        res.put("user",user);
        res.put("routeCount",routeCount);
        res.put("followCount",followCount);
        res.put("fansCount",fansCount);
        res.put("dynamicCount",dynamicCount);
        res.put("allLength",allLength);
        res.put("allOil",allOil);
        return res;
    }
}
