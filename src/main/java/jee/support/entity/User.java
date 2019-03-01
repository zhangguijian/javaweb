package jee.support.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
public class User {
    private long userId;  //用户id
    private String username; //用户名
    private String password; //密码
    private int accountNonExpired; //是否过期
    private int accountNonLocked; //是否被锁住
    private int enabled;    //是否激活
    private String email;   //email地址
}
