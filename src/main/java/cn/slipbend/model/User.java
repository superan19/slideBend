package cn.slipbend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

@Data
@ComponentScan
//@ApiModel(value = "用户")
public class User {
  //@ApiModelProperty(value = "用户id",name = "id", required = true)
  private Integer id;
  //@ApiModelProperty(value = "用户名",name = "username", required = true)
  private String username;
  //@ApiModelProperty(value = "密码",name = "password", required = true)
  private String password;
  //@ApiModelProperty(value = "手机",name = "phone", required = true)
  private String phone;
  //@ApiModelProperty(value = "头像",name = "icon", required = true)
  private String icon;
  //@ApiModelProperty(value = "性别",name = "sex", required = true)
  private String sex;
  //@ApiModelProperty(value = "年龄",name = "age", required = true)
  private Integer age;
  //@ApiModelProperty(value = "汽车型号",name = "carModel", required = true)
  private String carModel;
  //@ApiModelProperty(value = "引擎型号",name = "engineModel", required = true)
  private String engineModel;
  //@ApiModelProperty(value = "车龄",name = "carAge", required = true)
  private Integer carAge;
  //@ApiModelProperty(value = "是否出现通讯录",name = "mail", required = true)
  private Integer mail;
  //@ApiModelProperty(value = "是否出现附近",name = "nearby", required = true)
  private Integer nearby;
  //@ApiModelProperty(value = "是否为关注人聊天",name = "noAttention", required = true)
  private Integer noAttention;
  //@ApiModelProperty(value = "爱好",name = "hobby", required = true)
  private String hobby;
  private String city;
  //@ApiModelProperty(value = "地区",name = "area", required = true)
  //private Area area;
}
