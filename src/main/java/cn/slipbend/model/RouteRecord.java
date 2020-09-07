package cn.slipbend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

@Data
@ComponentScan
public class RouteRecord {

  private Integer id;
  private User user;
  private Mode mode;
  private Double sLongitude;
  private Double sLatitude;
  private Double eLongitude;
  private Double eLatitude;
  @JsonFormat(timezone = "GMT+8",pattern="HH:mm:ss")
  private Date time;
  private Double speed;
  private Double leng;
  private Double altitude;
  private Integer hot;
  private Integer oil;
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
  private Date createTime;
  private String imageUrl;
  private Integer mood;
  private String photo;
}
