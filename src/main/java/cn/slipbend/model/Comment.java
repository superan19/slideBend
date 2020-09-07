package cn.slipbend.model;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

@Data
@ComponentScan
public class Comment {

  private long id;
  private Dynamic dynamicId;
  private User userId;
  private String text;
  private User coverUserId;
  private long pid;
  private long good;
  private java.sql.Timestamp createDate;
}
