package cn.slipbend.dao;

import cn.slipbend.model.Area;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaDao {
    @Select("SELECT * FROM area WHERE name = #{name}")
    @Results(id = "AreaMapper",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "name",column = "name"),
            @Result(property = "pid",column = "pid"),
            @Result(property = "sort",column = "sort"),
            @Result(property = "level",column = "level"),
            @Result(property = "num",column = "num"),
            @Result(property = "code",column = "code")
    })
    Area getByName(String name);
    @Select("SELECT * FROM area WHERE num = #{num}")
    @ResultMap("AreaMapper")
    Area getByNum(String num);

}
