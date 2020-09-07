package cn.slipbend.model;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

@Data
@ComponentScan
public class Group {

  private long id;
  private User userId;
  private String name;
  private long number;
  private long hotLv;
  private String introduce;
  private java.sql.Timestamp craeteDate;
}
