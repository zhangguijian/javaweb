package jee.support.controller;

import com.github.pagehelper.PageInfo;
import jee.support.constants.Constants;
import jee.support.entity.Attachment;
import jee.support.entity.Ticket;
import jee.support.entity.User;
import jee.support.dao.TicketDao;
import jee.support.dao.UserDao;
import jee.support.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tickets")
public class TicketController  {
    @Autowired
    TicketService ticketService;
    @Autowired
    UserDao userDao;

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public String toCreateTicketForm(){
        return "addTicket";
    }


    //处理文件上传
    public List uploadFiles(@RequestParam("file") MultipartFile[] files,
                                    HttpServletRequest request) throws IOException {
        String savePath = request.getServletContext().getRealPath("/upload");
        File dir = new File(savePath);
        //判断上传文件夹是否存在
        if (!dir.exists()) {
            dir.mkdirs();
        }

        HttpSession session = request.getSession();
        //获取当前登录用户
        User user = (User)session.getAttribute(Constants.SESSION_USER);
        List attachmentList = new ArrayList<Attachment>();
        for (MultipartFile  file: files) {
            Attachment attachment = new Attachment();
            //文件名为用户名id_文件名
            String filename = user.getUserId()+"_"+ file.getOriginalFilename();
            //保存文件
            if(!file.isEmpty()) {
                File newFile = new File(dir, filename);
                file.transferTo(newFile);
                attachment.setFileName(filename);
                attachment.setMimeType(file.getContentType());
                attachment.setFileUrl("/upload/" + filename);
                attachmentList.add(attachment);
            }
        }
        return attachmentList;
    }

    //添加票据  POST请求
    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String createTicket(Ticket  ticket,
                               @RequestParam("file") MultipartFile[] files,
                               HttpServletRequest request,
                               Model model,
                               HttpSession session) throws IOException {
        //接收客户上传的文件
        List attachmentList= uploadFiles(files, request);

        //将相关记录插入数据库
        System.out.println(ticket);
        //获取当前用户对象
        User user = (User)session.getAttribute(Constants.SESSION_USER);
        ticket.setUser(user);
        ticket.setAttachments(attachmentList);
        long ticketId = ticketService.addTicket(ticket);
        //跳转到查看详情的界面
        return "redirect:view/"+ticketId;
    }

    //查看票据
    @RequestMapping("/view/{ticketId}")
    public String viewTicket(@PathVariable("ticketId") Long id,
                             Model model){
        Ticket ticket = ticketService.getTicket(id);
        model.addAttribute("ticket", ticket);
        return "viewTicket";
    }

    //分页显示票据
    @GetMapping(value = {"/list","/",""})
    public String pageTicketList(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "size", defaultValue = "10") int size,
                                 Model model){

        //默认每页记录
        PageInfo<Ticket> pageInfo = ticketService.findTicketList(page, size);
        model.addAttribute("pageInfo", pageInfo);
        return  "listTicketPage";
    }

    //跳转到编辑票据的页面
    @RequestMapping(value = "/edit/{ticketId}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("ticketId") Integer id, Model model){
        //获取票据
        Ticket ticket = ticketService.getTicket(id);
        model.addAttribute("ticketId", id);
        model.addAttribute("ticket", ticket);
        return "editTicket";
    }

    //更新票据对象
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Ticket ticket ,
                         @RequestParam("ticketId") long ticketId,
                         Model model,
                         HttpSession session){

        ticketService.updateTicket(ticketId, ticket);
        return "redirect:/tickets/view/"+ticketId;
    }


    //删除票据对象
    @RequestMapping(value = "/del/{ticketId}", method = RequestMethod.GET)
    public String delete(@PathVariable("ticketId") long ticketId){
        //获取票据
        ticketService.delTicket(ticketId);
        return "redirect:/tickets";
    }
}
