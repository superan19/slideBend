package cn.slipbend.model;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

@Data
@ComponentScan
public class Area {

  private long id;
  private String name;
  private long pid;
  private long sort;
  private long level;
  private String num;
  private String code;

}

