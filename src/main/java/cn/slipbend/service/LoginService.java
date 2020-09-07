package cn.slipbend.service;

import cn.slipbend.model.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: AnZX
 * @Date: 2020/07/20/18:09
 * @Description:
 */
public interface LoginService {
    /**
     * 手机号密码登录
     * @param phone
     * @param password
     * @return
     */
    User loginByPassword(String phone, String password);

    /**
     * 根据手机号查看用户是否注册
     * @param phone
     * @return
     */
    User findUserByPhone(String phone);

    /**
     * 注册用户
     * @param phone
     */
    void insertUser(String phone);

    /**
     * 修改密码
     * @param phone
     * @param newPassword
     */
    void updatePassword(String phone, String newPassword);

    /**
     * 修改性别年龄
     * @param id
     * @param sex
     * @param age
     * @return
     */
    Object updateSexAndAge(String id, String sex, Integer age);

    /**
     * 修改汽车和发动机型号
     * @param id
     * @param carModel
     * @param engineModel
     * @return
     */
    Object updateCarModel(String id, String carModel, String engineModel);

    /**
     * 修改头像和用户名
     * @param id
     * @param imageUrl
     * @param username
     * @return
     */
    Object updateImageAndUsername(String id, String imageUrl, String username);
}
