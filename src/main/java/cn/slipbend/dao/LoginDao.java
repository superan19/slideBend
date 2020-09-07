package cn.slipbend.dao;

import cn.slipbend.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: AnZX
 * @Date: 2020/07/20/18:13
 * @Description:
 */
@Repository
public interface LoginDao {
    /**
     * 根据手机号密码查找用户
     * @param phone
     * @param password
     * @return
     */
    @Select("SELECT * FROM user WHERE phone = #{phone} and password = #{password}")
    User findUserByPhoneAndPassword(String phone, String password);
    /**
     * 根据手机号查找用户
     * @param phone
     * @return
     */
    @Select("SELECT * FROM user WHERE phone = #{phone}")
    User findUserByPhone(String phone);
    @Insert("insert into user (phone) VALUES (#{phone}) ")
    void insertUser(String phone);

    /**
     * 修改密码
     * @param phone
     * @param newPassword
     */
    @Update("UPDATE USER SET PASSWORD = #{newPassword} WHERE phone =#{phone}")
    void updatePassword(String phone, String newPassword);

    /**
     * 修改性别年龄
     * @param id
     * @param sex
     * @param age
     */
    @Update("UPDATE USER SET sex = #{sex},age = #{age} WHERE id =#{id}")
    void updateSexAndAge(String id, String sex, Integer age);

    /**
     * 修改汽车和发动机型号
     * @param id
     * @param carModel
     * @param engineModel
     */
    @Update("UPDATE USER SET car_model = #{carModel},engine_model = #{engineModel} WHERE id =#{id}")
    void updateCarModel(String id, String carModel, String engineModel);

    /**
     * 修改头像和用户名
     * @param id
     * @param imageUrl
     * @param username
     */
    @Update("UPDATE USER SET icon = #{imageUrl},username = #{username} WHERE id =#{id}")
    void updateImageAndUsername(String id, String imageUrl, String username);
}
