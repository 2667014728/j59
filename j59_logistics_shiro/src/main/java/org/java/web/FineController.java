package org.java.web;

import org.java.service.Warehouservice;
import org.java.service.impl.FineserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class FineController {
    @Autowired
    private FineserviceImpl warehouservice;

    @RequestMapping(value = {"loadwaref","loadwaref/{page}","loadwaref/{page}/{size}"})
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

      return "fine";
    }
    @RequestMapping("addf")
    public  String Createwate(@RequestParam Map m){
        System.out.println(m);
        warehouservice.CreatedWarehouse(m);
        System.out.println(m);

        return  "redirect:loadwaref";
    }
    @RequestMapping("upcangkuf/{fid}")
    public  String upcangku(@PathVariable("fid") String fid,Model model){

       Map<String,Object> map=warehouservice.findByid(fid);
       model.addAttribute("m",map);

       return  "upcangkuf";
    }
    @RequestMapping("updatecangf")
    public  String updatecang(@RequestParam Map m){
        System.out.println(m);
        warehouservice.update(m);
        System.out.println(m);
        return  "redirect:loadwaref";
    }

    @RequestMapping("delcang/{fid}")
    public  String del(@PathVariable("fid") String fid){
      warehouservice.del(fid);
        return  "redirect:/loadwaref";
    }
}
