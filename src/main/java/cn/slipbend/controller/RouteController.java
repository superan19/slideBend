package cn.slipbend.controller;

import cn.slipbend.model.Mode;
import cn.slipbend.model.RouteRecord;
import cn.slipbend.model.User;
import cn.slipbend.service.RouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: AnZX
 * @Date: 2020/07/31/14:29
 * @Description:用户行程路线模块
 */
@RestController
@RequestMapping("/route")
@Api(value = "App接口", description = "用户行程路线的接口")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @RequestMapping("/saveRoute")
        @ApiOperation(value = "保存行程", httpMethod = "POST",notes = "保存本次行程")
        @ApiImplicitParams({
                @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
                @ApiImplicitParam(paramType = "query",name = "modeName",value = "模式名称",required = true,dataType = "String"),
                @ApiImplicitParam(paramType = "query",name = "sLongitude",value = "开始经度",required = true,dataType = "String"),
                @ApiImplicitParam(paramType = "query",name = "sLatitude",value = "开始纬度",required = true,dataType = "String"),
                @ApiImplicitParam(paramType = "query",name = "eLongitude",value = "结束经度",required = true,dataType = "String"),
                @ApiImplicitParam(paramType = "query",name = "eLatitude",value = "结束纬度",required = true,dataType = "String"),
                @ApiImplicitParam(paramType = "query",name = "time",value = "用时",required = true,dataType = "Date"),
                @ApiImplicitParam(paramType = "query",name = "speed",value = "速度",required = true,dataType = "String"),
                @ApiImplicitParam(paramType = "query",name = "leng",value = "路长(里程)",required = true,dataType = "String"),
                @ApiImplicitParam(paramType = "query",name = "altitude",value = "海拔",required = true,dataType = "String"),
                @ApiImplicitParam(paramType = "query",name = "imageUrl",value = "图片路径",required = true,dataType = "String"),
        })
        public Object saveRoute(Integer userId, String modeName, Double sLongitude, Double sLatitude, Double eLongitude, Double eLatitude, Date time, Double speed, Double leng, Double altitude,String imageUrl){
            RouteRecord routeRecord = new RouteRecord();
            User user = new User();
            user.setId(userId);
            routeRecord.setUser(user);
            routeRecord.setSLongitude(sLongitude);
            routeRecord.setSLatitude(sLatitude);
            routeRecord.setELongitude(eLongitude);
            routeRecord.setELatitude(eLatitude);
            routeRecord.setTime(time);
            routeRecord.setSpeed(speed);
            routeRecord.setLeng(leng);
            routeRecord.setAltitude(altitude);
            routeRecord.setImageUrl(imageUrl);
            //获取modeId
            Mode mode = routeService.findModeIdByModelName(modeName);
            routeRecord.setMode(mode);
            return routeService.insertRouteRecord(routeRecord);
        }

    @RequestMapping("/findModeRank")
    @ApiOperation(value = "查询此模式下所有子模式的热度以及用户排名和最好成绩", httpMethod = "POST",notes = "查询此模式下所有子模式的热度以及用户排名和最好成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "modeName",value = "模式名称",required = true,dataType = "String"),
    })
    public Object findModeRank(Integer userId, String modeName){
        return routeService.findModeHotAndRank(userId,modeName);
    }

    @RequestMapping("/featuredRoute")
    @ApiOperation(value = "精选路线", httpMethod = "POST",notes = "精选路线")
    @ApiImplicitParams({
            //@ApiImplicitParam(paramType = "query",name = "modeName",value = "精选模式",required = false,dataType = "String"),
    })
    public Object featuredRoute(){
        return routeService.findfeaturedRoute();
    }

    @RequestMapping("/routeDetail")
    @ApiOperation(value = "查看行程详情", httpMethod = "POST",notes = "查看行程详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "routeId",value = "路线行程id",required = true,dataType = "Integer"),
    })
    public Object routeDetail(Integer routeId){
        return routeService.findRouteDetailById(routeId);
    }

    @RequestMapping("/updateMood")
    @ApiOperation(value = "修改心情", httpMethod = "POST",notes = "修改心情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "routeId",value = "路线行程id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "mood",value = "心情",required = true,dataType = "Integer"),
    })
    public Object updateMood(Integer routeId,Integer mood){
        return routeService.updateMood(routeId,mood);
    }

    @RequestMapping("/addPhoto")
    @ApiOperation(value = "添加相册", httpMethod = "POST",notes = "添加相册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "routeId",value = "路线行程id",required = true,dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "photo",value = "相片",required = true,dataType = "String"),
    })
    public Object addPhoto(Integer routeId,String photo){
        return routeService.addPhoto(routeId,photo);
    }
}
