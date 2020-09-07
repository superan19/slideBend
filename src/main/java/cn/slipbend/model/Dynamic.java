package cn.slipbend.model;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

@Data
@ComponentScan
public class Dynamic {

  private long id;
  private User userId;
  private String text;
  private String imgsUrl;
  private long good;
  private long stepOn;
  private String power;
  private double lon;
  private double lat;
  private java.sql.Timestamp createDate;
}

