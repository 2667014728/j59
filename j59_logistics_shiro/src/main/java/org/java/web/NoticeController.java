package org.java.web;

import org.java.service.impl.NoticeserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class NoticeController {
    @Autowired
    private NoticeserviceImpl warehouservice ;
    @Autowired
    private HttpServletRequest request;


    @RequestMapping(value = {"loadwaren","loadwaren/{page}","loadwaren/{page}/{size}"})
    public String LoadWarehou(Model model,@PathVariable(value = "page",required = false) Integer page, @PathVariable(value = "size",required = false) Integer size){
        System.out.println("进入controller");
        if(page==null){
            page=1;//默认为第1页
        }
        if(size==null){
            size=6;//默认每一页显示6条
        }
          //计算总条数，以及总页面
        int count = warehouservice.getCount();
        int maxPage = count%size==0?count/size:count/size+1;

        List<Map<String ,Object>> list=warehouservice.findAll(page,size);
        model.addAttribute("list",list );
        model.addAttribute("page",page );
        model.addAttribute("size",size );
        model.addAttribute("count",count );
        model.addAttribute("maxPage",maxPage );
        System.out.println(list);
      return "notice";
    }
    @RequestMapping("addn")
    public  String Createwate(@RequestParam Map m){
        HttpSession session=request.getSession();
        Map<String,Object> user= (Map<String, Object>) session.getAttribute("usera");
        m.put("uid",user.get("user_id"));
        m.put("uname",user.get("user_name"));
        System.out.println(m);
        warehouservice.CreatedWarehouse(m);


        return  "redirect:loadwaren";
    }
    @RequestMapping("upcangkun/{nid}")
    public  String upcangku(@PathVariable("nid") String nid,Model model){

       Map<String,Object> map=warehouservice.findByid(nid);
       model.addAttribute("m",map);

       return  "upcangkun";
    }
    @RequestMapping("updatecangn")
    public  String updatecang(@RequestParam Map m){
        System.out.println(m);
        warehouservice.update(m);
        System.out.println(m);
        return  "redirect:loadwaren";
    }

    @RequestMapping("delcangn/{nid}")
    public  String del(@PathVariable("nid") String nid){
      warehouservice.del(nid);
        return  "redirect:/loadwaren";
    }
}
