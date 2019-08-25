package org.java.web;

import org.apache.ibatis.annotations.Param;
import org.java.service.Warehouservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class WarehouController {
    @Autowired
    private Warehouservice warehouservice;
    //跳转
    @GetMapping("forward/{target}")
    public String forward(@PathVariable("target") String target){
        return "/"+target;
    }

    @RequestMapping(value = {"loadware","loadware/{page}","loadware/{page}/{size}"})
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

      return "personnel";
    }
    @RequestMapping("add")
    public  String Createwate(@RequestParam Map m){
        System.out.println(m);
        warehouservice.CreatedWarehouse(m);
        System.out.println(m);

        return  "redirect:loadware";
    }
    @RequestMapping("upcangku/{wh_id}")
    public  String upcangku(@PathVariable("wh_id") String wh_id,Model model){

       Map<String,Object> map=warehouservice.findByid(wh_id);
       model.addAttribute("m",map);

       return  "upcangku";
    }
    @RequestMapping("updatecang")
    public  String updatecang(@RequestParam Map m){
        System.out.println(m);
        warehouservice.update(m);
        warehouservice.updaterole(m.get("rid").toString());
        return  "redirect:loadware";
    }

    @RequestMapping("delcang/{user_id}")
    public  String del(@PathVariable("user_id") String user_id){
      warehouservice.del(user_id);
        return  "redirect:/loadware";
    }
}
