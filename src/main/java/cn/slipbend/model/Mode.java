package cn.slipbend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

@Data
@ComponentScan
public class Mode {

  private Integer id;
  private String modeName;
  private Double sLongitude;
  private Double sLatitude;
  private Double eLongitude;
  private Double eLatitude;
  private Integer hot;
  private Integer parentId;
  private User user;
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
  private Date createTime;

}
