package org.java.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Warehouservice {

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

    //新建关联表
    public void addrole(Map m);

    //删除关联表
    public  void  delrole(String user_id);

    //修改关联表
    public  void  updaterole(String user_id);
}
