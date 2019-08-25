package org.java.service.impl;
import org.java.service.Warehouservice;
import org.java.dao.warehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class WarehouserviceImpl  implements Warehouservice {

    @Autowired
    private  warehouseMapper mapper;

    @Override
    public List<Map<String, Object>> findAll(int page, int size) {
        System.out.println("进入service");
        //计算开始下标
        int start = (page-1)*size;
        return mapper.findAll(start,size);
    }

    @Override
    public int getCount() {
        return mapper.getCount();
    }

    @Override
    public Map<String, Object> findByid(String id) {
        System.out.println(mapper.findById(id));
        return mapper.findById(id);
    }

    @Override
    public void CreatedWarehouse(Map m) {
     mapper.CreatedWarehouse(m);
    }

    @Override
    public void update(Map map) {
     mapper.update(map);
    }

    @Override
    public void del(String id) {
       mapper.del(id);
    }


    //新建关联表
    @Override
    public void addrole(Map m) {
        mapper.addrole(m);
    }

    //删除关联表
    @Override
    public  void  delrole(String user_id) {
        mapper.delrole(user_id);
    }

    //修改关联表
    @Override
    public  void  updaterole(String user_id) {
        mapper.updaterole(user_id);
    }
}
