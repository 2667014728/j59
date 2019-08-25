package org.java.web;

import org.java.service.Analyzeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class AnalyzeController {
    @Autowired
    private Analyzeservice warehouservice;

    @RequestMapping("selecttj")
    public  String Createwate(@RequestParam Map m,Model model){
        System.out.println(111);
        List<Map<String, Object>> jt = warehouservice.selectjt();
        List<Map<String, Object>> yz = warehouservice.selectyz();
        List<Map<String, Object>> kh = warehouservice.selectkh();
;       if (jt==null){
            model.addAttribute("jt",0);
        }else {
            model.addAttribute("jt",jt.size());
        }
        if (yz==null){
            model.addAttribute("yz",0);
        }else {
            model.addAttribute("yz",yz.size());
        }
        if (kh==null){
            model.addAttribute("kh",0);
        }else {
            model.addAttribute("kh",kh.size());
        }

        double jtje=0;
        double yzje=0;
        for (Map<String, Object> a: yz
        ) {
            yzje+=(double)a.get("od_mark");
        }
        for (Map<String, Object> a: jt
             ) {
            jtje+=(double)a.get("od_mark");
        }
        System.out.println(jtje);
        System.out.println(yzje);
        model.addAttribute("jtje",jtje);
        model.addAttribute("yzje",yzje);
        return  "/homepage";
    }

//    @RequestMapping(value = {"loadware1","loadware2/{page}","loadware2/{page}/{size}"})
//    public String LoadWarehou(Model model, @PathVariable(value = "page",required = false) Integer page, @PathVariable(value = "size",required = false) Integer size){
//        System.out.println("进入controller");
//        if(page==null){
//            page=1;//默认为第1页
//        }
//        if(size==null){
//            size=6;//默认每一页显示6条
//        }
//        //计算总条数，以及总页面
//        int count = warehouservice.getCount();
//        int maxPage = count%size==0?count/size:count/size+1;
//
//        List<Map<String ,Object>> list=warehouservice.findAll(page,size);
//        model.addAttribute("list",list );
//        model.addAttribute("page",page );
//        model.addAttribute("size",size );
//        model.addAttribute("count",count );
//        model.addAttribute("maxPage",maxPage );
//
//        return "personnel";
//    }
//    @RequestMapping(value = {"loadware2","loadware2/{page}","loadware2/{page}/{size}"})
//    public String LoadWarehou2(Model model,@PathVariable(value = "page",required = false) Integer page, @PathVariable(value = "size",required = false) Integer size){
//        System.out.println("进入controller");
//        if(page==null){
//            page=1;//默认为第1页
//        }
//        if(size==null){
//            size=6;//默认每一页显示6条
//        }
//        //计算总条数，以及总页面
//        int count = warehouservice.getCount();
//        int maxPage = count%size==0?count/size:count/size+1;
//
//        List<Map<String ,Object>> list=warehouservice.findAll(page,size);
//        model.addAttribute("list",list );
//        model.addAttribute("page",page );
//        model.addAttribute("size",size );
//        model.addAttribute("count",count );
//        model.addAttribute("maxPage",maxPage );
//
//        return "personnel";
//    }

}
