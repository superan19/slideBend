package cn.slipbend.service;

import cn.slipbend.model.Area;

public interface AreaServeice {
    Area getByName(String name);
    Area getByNum(String num);
}
