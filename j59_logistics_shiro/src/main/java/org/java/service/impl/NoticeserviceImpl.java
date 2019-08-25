package org.java.service.impl;

import org.java.dao.noticeMapper;
import org.java.dao.warehouseMapper;
import org.java.service.Noticeservice;
import org.java.service.Warehouservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeserviceImpl implements Noticeservice {

    @Autowired
    private noticeMapper mapper;

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
        System.out.println("进入service");
     mapper.update(map);
    }

    @Override
    public void del(String id) {
       mapper.del(id);
    }


}
