package cn.slipbend.service;

import cn.slipbend.model.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @Date:
 * @Description: 用户操作
 */
public interface UserService {
    /**
     * 修改用户个人资料
     * @param user
     * @return
     */
    Object updateUserInfo(User user);

    /**
     * 修改车辆信息
     * @param id
     * @param carModel
     * @param carAge
     * @param engineModel
     * @return
     */
    Object updateCarInfo(Integer id, String carModel, String carAge, String engineModel);

    /**
     * 计算用户行驶总距离
     * @param id
     * @return
     */
    Object findUserDrivingLength(Integer id);

    /**
     * 查找达成里程成就的人数
     * @param mileage
     * @return
     */
    Object findCompletePeopleByMileage(Double mileage);

    /**
     * 查看用户关注列表
     * @param id
     * @return
     */
    Object findUserFollow(Integer id);
    /**
     * 查看用户粉丝列表
     * @param id
     * @return
     */
    Object findUserFans(Integer id);

    /**
     * 查看用户个人信息
     * @param id
     * @return
     */
    Object findUserInfo(Integer id);
}
