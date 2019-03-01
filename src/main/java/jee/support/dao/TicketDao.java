package jee.support.dao;

import jee.support.entity.Ticket;
import org.apache.ibatis.annotations.*;

import java.util.List;

//TicketDao类,封装对ticket对象CRUD操作
@Mapper
public interface TicketDao {
    //根据id获取票据对象,如果不存在返回null
    @Select("select * from ticket t where t.ticketId=#{id}")
    @ResultMap("TicketWithUserAndAtt") //返回结果使用xml文件定义的resultMap封装
    public Ticket getTicket(long id);

    //插入票据,返回新票据的id号
    @Insert("insert into ticket(userId, Subject, Body,DateCreated,Money," +
            "Type,status)" +
            " value(#{user.userId},#{subject},#{body}, #{dateCreated}, #{money}, " +
            "#{type},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "ticketId")
    public long addTicket(Ticket ticket);

    //删除票据
    @Delete("delete from ticket  where ticketId=#{id}")
    public int delTicket(long ticketId);

    //根据票据主体进行模糊查询
   public List<Ticket> findBySubjectLike(String keyword);

    //更新票据信息
    @Update("update ticket  set subject=#{subject}, body=#{body}, money=#{money}," +
            "type=#{type} where ticketId=#{ticketId}")
    public int updateTicket(Ticket ticket);

    //获取票据列表
    @Select("select * from ticket")
    @ResultMap("TicketWithUserAndAtt")
    public List<Ticket> findAllTickets();

}

