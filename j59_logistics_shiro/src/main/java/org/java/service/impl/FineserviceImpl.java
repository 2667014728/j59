package org.java.service.impl;

import org.java.dao.fineMapper;
import org.java.dao.warehouseMapper;
import org.java.service.Fineservice;
import org.java.service.Warehouservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FineserviceImpl implements Fineservice {

    @Autowired
    private fineMapper mapper;

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

}
