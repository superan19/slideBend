package cn.slipbend.service;

import cn.slipbend.model.Mode;
import cn.slipbend.model.RouteRecord;

/**
 * Created with IntelliJ IDEA.
 *
 * @Date:
 * @Description: 用户行程记录
 */
public interface RouteService {
    /**
     * 根据modeName查询mode
     * @param modeName
     * @return
     */
    Mode findModeIdByModelName(String modeName);

    /**
     * 插入用户行程记录
     * @param routeRecord
     * @return
     */
    Object insertRouteRecord(RouteRecord routeRecord);

    /**
     * 查询用户此模式的排名和最好成绩
     * @param userId
     * @param modeName
     * @return
     */
    Object findModeHotAndRank(Integer userId, String modeName);

    /**
     * 精选路线
     * @return
     */
    Object findfeaturedRoute();

    /**
     * 查看路线详情
     * @param routeId
     * @return
     */
    Object findRouteDetailById(Integer routeId);

    /**
     * 修改心情
     * @param routeId
     * @param mood
     * @return
     */
    Object updateMood(Integer routeId, Integer mood);

    /**
     * 添加相册
     * @param routeId
     * @param photo
     * @return
     */
    Object addPhoto(Integer routeId, String photo);
}
