package cn.slipbend.dao;

import cn.slipbend.model.Mode;
import cn.slipbend.model.RouteRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface RouteDao {

    /**
     * 根据modeName查询mode
     * @param modeName
     * @return
     */
    @Select("SELECT * FROM mode WHERE mode_name = #{modeName}")
    Mode findModeIdByModelName(String modeName);

    /**
     * 插入用户行程记录
     * @param routeRecord
     */
    @Insert("INSERT INTO route_record(user_id,mode_id,s_longitude,s_latitude,e_longitude,e_latitude,TIME,speed,leng,altitude,imageUrl) VALUES (#{user.id},#{mode.id},#{sLongitude},#{sLatitude},#{eLongitude},#{eLatitude},#{time},#{speed},#{leng},#{altitude},#{imageUrl}) ")
    void insertRouteRecord(RouteRecord routeRecord);

    /**
     * 根据modeId查找所有子mode
     * @param id
     * @return
     */
    @Select("select * from mode where parent_id=#{id}")
    @Results(id = "ModeMapper",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "modeName",column = "mode_name"),
            @Result(property = "sLongitude",column = "s_longitude"),
            @Result(property = "sLatitude",column = "s_latitude"),
            @Result(property = "eLongitude",column = "e_longitude"),
            @Result(property = "eLatitude",column = "e_latitude"),
            @Result(property = "hot",column = "hot"),
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "user.id",column = "user_id"),
            @Result(property = "createTime",column = "create_time"),
    })
    List<Mode> findModeListByPid(Integer id);

    /**
     * 查询用户在此模式的排名
     * @param userId
     * @param modeId
     */
    @Select("SELECT MIN(名次) FROM\n" +
            "(SELECT a.mode_id,a.user_id,a.time,(@rowNum:=@rowNum+1) AS 名次\n" +
            "FROM route_record a,\n" +
            "(SELECT (@rowNum :=0) ) b WHERE a.mode_id=#{modeId}\n" +
            "ORDER BY TIME)AS c\n" +
            "WHERE user_id=#{userId}")
    Integer findModeRanking(Integer userId, Integer modeId);

    /**
     * 查询用户在此模式下的最好成绩
     * @param userId
     * @param id
     * @return
     */
    @Select("SELECT TIME FROM route_record WHERE user_id=#{userId} AND mode_id=#{id} ORDER BY TIME LIMIT 0,1")
    Time findBestGrades(Integer userId, Integer id);

    /**
     * 获取赛道热度排序
     * @return
     */
    @Select("SELECT * FROM MODE ORDER BY hot DESC")
    @ResultMap("ModeMapper")
    List<Mode> findModesByHot();

    /**
     * 根据modeId查询最早的用户及路线信息
     * @return
     */
    @Select("SELECT r.id,MIN(r.create_time) mintime,u.username, u.icon,r.imageUrl FROM route_record r INNER JOIN USER u ON r.user_id = u.id WHERE mode_id =#{id} GROUP BY mode_id ")
    @Results(id = "RouteMapper2",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "user.username",column = "username"),
            @Result(property = "user.icon",column = "icon"),
            @Result(property = "createTime",column = "mintime"),
            @Result(property = "imageUrl",column = "imageUrl"),
    })
    RouteRecord findEarliestUserAndRoute(Integer id);

    /**
     * 查看路线详情
     * @param routeId
     * @return
     */
    @Results(id = "RouteMapper",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "user.username",column = "username"),
            @Result(property = "user.icon",column = "icon"),
            @Result(property = "mode.modeName",column = "mode_name"),
            @Result(property = "sLongitude",column = "s_longitude"),
            @Result(property = "sLatitude",column = "s_latitude"),
            @Result(property = "eLongitude",column = "e_longitude"),
            @Result(property = "eLatitude",column = "e_latitude"),
            @Result(property = "time",column = "time"),
            @Result(property = "speed",column = "speed"),
            @Result(property = "leng",column = "leng"),
            @Result(property = "altitude",column = "altitude"),
            @Result(property = "hot",column = "hot"),
            @Result(property = "oil",column = "oil"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "imageUrl",column = "imageUrl"),
            @Result(property = "mood",column = "mood"),
            @Result(property = "photo",column = "photo"),
    })
    @Select("SELECT u.username, u.icon,r.*,m.mode_name FROM route_record r \n" +
            "INNER JOIN USER u ON r.user_id = u.id  \n" +
            "INNER JOIN MODE m ON r.mode_id = m.id\n" +
            "WHERE r.id=#{routeId}")
    RouteRecord findRouteDetailById(Integer routeId);

    /**
     * 根据路线id修改心情
     * @param routeId
     * @param mood
     */
    @Update("UPDATE route_record SET mood = #{mood} WHERE id =#{routeId}")
    void updateMoodByRouteId(Integer routeId, Integer mood);

    /**
     * 根据路线id添加相册
     * @param routeId
     * @param photo
     */
    @Update("UPDATE route_record SET photo = #{photo} WHERE id =#{routeId}")
    void updatePhoto(Integer routeId, String photo);
}
