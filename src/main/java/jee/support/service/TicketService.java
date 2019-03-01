package jee.support.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jee.support.dao.AttachmentDao;
import jee.support.dao.TicketDao;
import jee.support.entity.Attachment;
import jee.support.entity.Ticket;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

//TicketDao类,封装对ticket对象CRUD操作
@Service
public class TicketService {
    @Autowired
    TicketDao ticketDao;

    @Autowired
    AttachmentDao attachmentDao;
    //根据id获取票据对象,如果不存在返回null
    public Ticket getTicket(long id) {
        return ticketDao.getTicket(id);
    }

    // 添加票据,返回该票据对应的id号
    @Transactional(rollbackFor = Exception.class)  //处理事务回滚
    public long addTicket(Ticket ticket) {
        //设置票据默认信息
        ticket.setDateCreated(new Date());
        ticket.setStatus(0);
         ticketDao.addTicket(ticket);
        // 插入票据记录,获取票据id
        long ticketId = ticket.getTicketId();
        System.out.println("new id:" + ticketId);
        //插入附件记录到数据库
        for (Attachment attachment: ticket.getAttachments()) {
            attachment.setTicketId(ticketId);//设置ticketId
            attachmentDao.addAttachment(attachment);
        }
         return ticketId;
    }

    //删除票据
    @Transactional
    public int delTicket(long ticketId) {
        return ticketDao.delTicket(ticketId);
    }

    //根据票据主体进行模糊查询
    public List<Ticket> findBySubjectLike(String keyword){
        return ticketDao.findBySubjectLike(keyword) ;
    }

    //更新票据信息
//    返回受影响的行数
    @Transactional
    public int  updateTicket(long id, Ticket ticket) {
        ticket.setTicketId(id); //设置更新的ticket的id
        return ticketDao.updateTicket(ticket);
    }

     //获取票据列表
    //入参: 当期页, 每页的记录数
    public PageInfo<Ticket> findTicketList(int page, int size){
        PageHelper.startPage(page, size);
        List list = ticketDao.findAllTickets();
        PageInfo<Ticket> pageInfo = new PageInfo<>(list);

        return  pageInfo;  //获取分页对象
    }

    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext("config" +
                "/applicationContext.xml");
        System.out.println(factory);
        TicketService ticketService = (TicketService) factory.getBean("ticketService");
//
        Ticket ticket= ticketService.getTicket(41);
        System.out.println(ticket.getSubject());
        //查询用户名
        System.out.println(ticket.getUser().getUsername());
    }
}

