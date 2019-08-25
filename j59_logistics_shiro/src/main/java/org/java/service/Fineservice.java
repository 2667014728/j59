package org.java.service;

import java.util.List;
import java.util.Map;

public interface Fineservice {

    //查询所有仓库信息
    public List<Map<String,Object>> findAll(int page, int size);

    //总条数
    public int getCount();

    //条件查询
    public  Map<String,Object> findByid(String id);

    //新建仓库
    public void CreatedWarehouse(Map m);



    //修改仓库
    public void update(Map map);


    //删除仓库
    public  void  del(String id);

}
