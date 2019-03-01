package jee.support.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping(value = "user")
public class userController {

    @RequestMapping(value = "/yonghu",method = RequestMethod.GET)
    public String info(){
        return "info";
    }
    @RequestMapping(value = "/img",method = RequestMethod.POST)
    public String tupian(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException {
        String filename=file.getName();
        String path=request.getServletContext().getRealPath("/");
        Path path1= Paths.get(path+filename);
        try{
            Files.copy(file.getInputStream(),path1, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            throw new Error("不能上传图片");
        }
        session.setAttribute("picture",filename);
        return "redirect:/tickets";
    }
    @RequestMapping(value = "/lookimg/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> lookimg(@PathVariable String filename, HttpServletRequest request) throws MalformedURLException {
        String path=request.getServletContext().getRealPath("/");
        Path path1=Paths.get(path+filename);
        Resource resource=new UrlResource(path1.toUri());
        if(resource.exists() || resource.isReadable()){
            return ResponseEntity.ok().body(resource);
        }
        else{
            throw new Error("文件读取错误");
        }
    }
}
