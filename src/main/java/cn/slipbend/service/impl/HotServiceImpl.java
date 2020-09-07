package cn.slipbend.service.impl;

import cn.slipbend.dao.HotDao;
import cn.slipbend.dao.RouteDao;
import cn.slipbend.model.Mode;
import cn.slipbend.model.RouteRecord;
import cn.slipbend.service.HotService;
import cn.slipbend.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Date:
 * @Description: 热度
 */
@Service
public class HotServiceImpl implements HotService {
    @Autowired
    private HotDao hotDao;
    //增加赛道模式热度
    @Override
    public Object addModeHot(String modeName) {
        Map<String,Object> res = new HashMap<>();
        hotDao.addModeHot(modeName);
        res.put("msg","热度+1");
        return res;
    }
}
