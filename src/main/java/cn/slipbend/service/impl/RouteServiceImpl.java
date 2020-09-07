package cn.slipbend.service.impl;

import cn.slipbend.dao.RouteDao;
import cn.slipbend.model.Mode;
import cn.slipbend.model.RouteRecord;
import cn.slipbend.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Date:
 * @Description: 用户行程记录
 */
@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteDao routeDao;

    //根据modeName查询mode
    @Override
    public Mode findModeIdByModelName(String modeName) {
        return routeDao.findModeIdByModelName(modeName);
    }

    //插入用户行程记录
    @Override
    public Object insertRouteRecord(RouteRecord routeRecord) {
        Map<String,Object> res = new HashMap<>();
        routeDao.insertRouteRecord(routeRecord);
        res.put("msg","保存成功");
        return res;
    }

    //查询用户此模式的排名和最好成绩
    @Override
    public Object findModeHotAndRank(Integer userId, String modeName) {
        Map<String,Object> res = new HashMap<>();
        //查询modeName查找mode
        Mode mode = routeDao.findModeIdByModelName(modeName);
        //根据modeId查找所有子mode
        List<Mode> modeList = routeDao.findModeListByPid(mode.getId());
        for (Mode modes : modeList) {
            //查询用户在此模式的排名
            Integer ranking = routeDao.findModeRanking(userId,modes.getId());
            //查询用户在此模式的最好成绩
            Time bestGrades = routeDao.findBestGrades(userId,modes.getId());
            List list = new ArrayList();//记录热度 排名 成绩
            list.add(modes.getHot());
            list.add(ranking);
            list.add(bestGrades);
            res.put(modes.getModeName(),list);
        }
        return res;
    }

    //精选路线
    @Override
    public Object findfeaturedRoute() {
        Map<String,Object> res = new LinkedHashMap<>();
        //获取赛道热度排序
        List<Mode> modes = routeDao.findModesByHot();
        //根据modeId查询最早的用户及路线信息
        for (Mode mode : modes) {
            RouteRecord routeRecord = routeDao.findEarliestUserAndRoute(mode.getId());
            if(routeRecord!=null){
                Integer routeid = routeRecord.getId();
                String username = routeRecord.getUser().getUsername();
                String icon = routeRecord.getUser().getIcon();
                String imageUrl = routeRecord.getImageUrl();
                Date date = routeRecord.getCreateTime();
                //新建日期格式
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //将日期对象格式化成指定格式并以String输出
                String createTime = simpleDateFormat.format(date);
                Integer hot = mode.getHot();
                List list = new ArrayList();//记录精选线路相关信息
                list.add(routeid);
                list.add(username);
                list.add(icon);
                list.add(imageUrl);
                list.add(createTime);
                list.add(hot);

                res.put(mode.getModeName(),list);
            }
        }
        return res;
    }
    //查看路线详情
    @Override
    public Object findRouteDetailById(Integer routeId) {
        Map<String,Object> res = new LinkedHashMap<>();
        RouteRecord routeRecord = routeDao.findRouteDetailById(routeId);
        res.put("routeDetail",routeRecord);
        return res;
    }
    //修改心情
    @Override
    public Object updateMood(Integer routeId, Integer mood) {
        Map<String,Object> res = new LinkedHashMap<>();
        routeDao.updateMoodByRouteId(routeId,mood);
        res.put("msg","修改成功");
        return res;
    }
    //行程添加相册
    @Override
    public Object addPhoto(Integer routeId, String photo) {
        Map<String,Object> res = new LinkedHashMap<>();
        routeDao.updatePhoto(routeId,photo);
        res.put("msg","上传成功");
        res.put("photo",photo);
        return res;
    }
}
