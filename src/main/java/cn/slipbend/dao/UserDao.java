package cn.slipbend.dao;

import cn.slipbend.model.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    /**
     * 根据id修改用户信息
     * @param user
     */
   @Update("<script>"+
           "update user set\n" +
           "\t\t<trim suffixOverrides=\",\">\n" +
           "\t\t\t<if test=\"username !=null and username != ''\"> username=#{username}, </if>\n" +
           "\t\t\t<if test=\"sex !=null and sex != ''\"> sex=#{sex}, </if>\n" +
           "\t\t\t<if test=\"age !=null and age != ''\"> age=#{age}, </if>\n" +
           "\t\t\t<if test=\"hobby !=null and hobby != ''\"> hobby=#{hobby}, </if>\n" +
           "\t\t\t<if test=\"icon !=null and icon != ''\"> icon=#{icon}, </if>\n" +
           "\t\t\t<if test=\"city !=null and city != ''\"> city=#{city}, </if>\n" +
           //"\t\t\t<if test=\"area.num !=null and area.num != ''\"> area_Num=#{area.num}, </if>\n" +
           "\t\t</trim>\n" +
           "\t\twhere id=#{id}"+
           "</script>")
    void updateUserInfoById(User user);

    /**
     * 根据用户id修改车辆信息
     * @param id
     * @param carModel
     * @param carAge
     * @param engineModel
     */
    @Update("<script>"+
            "update user set\n" +
            "\t\t<trim suffixOverrides=\",\">\n" +
            "\t\t\t<if test=\"carModel !=null and carModel != ''\"> car_model=#{carModel}, </if>\n" +
            "\t\t\t<if test=\"carAge !=null and carAge != ''\"> car_age=#{carAge}, </if>\n" +
            "\t\t\t<if test=\"engineModel !=null and engineModel != ''\"> engine_model=#{engineModel}, </if>\n" +
            "\t\t</trim>\n" +
            "\t\twhere id=#{id}"+
            "</script>")
    void updateCarInfo(Integer id, String carModel, String carAge, String engineModel);

    /**
     * 根据id查询用户
     * @param user
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findUserById(User user);

    /**
     * 计算用户行驶总距离
     * @param id
     * @return
     */
    @Select("SELECT SUM(leng) FROM route_record WHERE user_id=#{id}")
    Integer findUserAllLength(Integer id);

    /**
     * 根据里程公里数查看达成里程成就的人数
     * @param mileage
     * @return
     */
    @Select("SELECT COUNT(*) FROM (SELECT SUM(leng) allleng FROM route_record GROUP BY user_id) AS a WHERE allleng>=#{mileage}")
    Integer findCompletePeopleByMileage(Double mileage);

    /**
     * 查看用户关注列表
     * @param id
     * @return
     */
    @Select("SELECT user.`id`,user.`username`,user.`icon` FROM follow INNER JOIN USER ON follow.`follow_user_id`=user.`id` WHERE fans_user_id=#{id}")
    List<User> findUserFollow(Integer id);

    /**
     * 查看用户粉丝列表
     * @param id
     * @return
     */
    @Select("SELECT user.`id`,user.`username`,user.`icon` FROM follow INNER JOIN USER ON follow.`fans_user_id`=user.`id` WHERE follow_user_id=#{id}")
    List<User> findUserFans(Integer id);

   /**
    * 查询用户行程次数
    * @param id
    * @return
    */
   @Select("SELECT COUNT(*) FROM route_record WHERE user_id = #{id}")
   Integer findCountRoute(Integer id);

   /**
    * 查询用户关注数
    * @param id
    * @return
    */
   @Select("SELECT COUNT(*) FROM follow WHERE fans_user_id = #{id}")
   Integer findCountFollow(Integer id);

   /**
    * 查询用户粉丝数
    * @param id
    * @return
    */
   @Select("SELECT COUNT(*) FROM follow WHERE follow_user_id=#{id}")
   Integer findCountFans(Integer id);

   /**
    * 查询用户动态数
    * @param id
    * @return
    */
   @Select("SELECT COUNT(*) FROM DYNAMIC WHERE user_id =#{id}")
   Integer findCountDynamic(Integer id);

    /**
     * 查询用户总油耗
     * @param id
     * @return
     */
    @Select("SELECT SUM(oil) FROM route_record WHERE user_id = #{id}")
    Integer findUserAllOil(Integer id);

    /**
     * 根据id查询用户名/头像/车辆型号
     * @param id
     * @return
     */
    @Select("SELECT username,icon,car_model carModel FROM user WHERE id = #{id}")
    User findUsernameIconCarmodelByUserId(Integer id);
}
