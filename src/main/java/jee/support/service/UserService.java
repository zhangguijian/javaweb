package jee.support.service;

import jee.support.dao.TicketDao;
import jee.support.dao.UserDao;
import jee.support.entity.Ticket;
import jee.support.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//TicketDao类,封装对ticket对象CRUD操作
@Service
public class UserService {

    @Autowired
    UserDao userDao;
    //获取用户
    public User getUser(long id){
        return  userDao.getUserById(id);
    }
    // 添加用户，返回新用户对应的id号
    @Transactional
    public long addUser(User user) {
           return userDao.addUser(user);
    }

    @Transactional
    public  void deleteUserById(long id){
        userDao.deleteUser(id);
    }

    //校验是否存在该用户,不存在则返回null,存在则返回用户
    //使用@Param来传递参数,传入参数为username和password
    public User authenticate( String username,String password){
        return userDao.findByNameAndPwd(username, password);
    }

    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext("config" +
                "/applicationContext.xml");
        System.out.println(factory);
        UserService userService = (UserService) factory.getBean("userService");
        System.out.println(userService);
        System.out.println(userService.getUser(1L).getUsername());


    }

}

