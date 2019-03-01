package jee.support.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
//使用lomok插件，可以不写setter和getter方法
@Data
@Accessors(chain = true)
//@Builder
public class Ticket {

    private long ticketId;
    private String subject;
    private String body;
    private Date dateCreated;
    private Float money;
    private String type;
    int status;
    User user;                //用户对象
    List<Attachment> attachments; //附件列表
}
