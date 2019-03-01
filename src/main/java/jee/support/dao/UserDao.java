package jee.support.dao;

import jee.support.entity.User;
import jee.support.utils.MybatisUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

//UserDao接口类,封装对User对象CRUD操作
public interface UserDao {

    //根据id获取用户对象,如果不存在返回null
    public User getUserById(long id);

    // 添加用户,返回新添加的用户id
    public long addUser(User user);
    //校验是否存在该用户,不存在则返回null,存在则返回用户
    //使用@Param来传递参数,传入参数为username和password
    public User findByNameAndPwd(@Param("username") String username,
                             @Param("password") String password);


    public void deleteUser(long userId);

}

