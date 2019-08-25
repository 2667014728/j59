package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface analyzeMapper {

    //查询所有员工信息
    public List<Map<String,Object>> selectjt();

    //查询所有员工信息
    public List<Map<String,Object>> selectyz();
    //查询所有员工信息
    public List<Map<String,Object>> selectkh();



}
