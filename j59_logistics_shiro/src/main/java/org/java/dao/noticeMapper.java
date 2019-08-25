package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface noticeMapper {

    //查询所有员工信息
    public List<Map<String,Object>> findAll(@Param("start") int start, @Param("size") int size);

    //总条数
    public int getCount();

    //条件查询
    public  Map<String,Object> findById(String nid);

    //新建员工
    public void CreatedWarehouse(Map m);


    //修改员工
    public void update(Map map);


    //删除员工
    public  void  del(String user_id);


}
