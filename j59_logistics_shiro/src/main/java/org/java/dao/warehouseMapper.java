package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sun.font.CreatedFontTracker;

import java.util.List;
import java.util.Map;
@Repository
@Mapper
public interface warehouseMapper {

    //查询所有员工信息
    public List<Map<String,Object>> findAll(@Param("start") int start, @Param("size") int size);

    //总条数
    public int getCount();

    //条件查询
    public  Map<String,Object> findById(String user_id);

    //新建员工
    public void CreatedWarehouse(Map m);

    //新建关联表
    public void addrole(Map m);


    //修改员工
    public void update(Map map);
    //修改关联表
    public  void  updaterole(String user_id);

    //删除员工
    public  void  del(String user_id);

    //删除关联表
    public  void  delrole(String user_id);

}
