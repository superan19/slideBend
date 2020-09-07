package cn.slipbend.service.impl;

import cn.slipbend.dao.AreaDao;
import cn.slipbend.model.Area;
import cn.slipbend.service.AreaServeice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl implements AreaServeice {
    @Autowired
    private AreaDao areaDao;
    @Override
    public Area getByName(String name) {
        return areaDao.getByName(name);
    }

    @Override
    public Area getByNum(String num) {
        return areaDao.getByNum(num);
    }
}
