package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: 马果
 * @Date: 2019/6/22 16:15
 * @Description:
 */
@Mapper
public interface InfoMapper {

  public Map<String,Object> dl(@Param("username") String username, @Param("pwd") String pwd);

  public List<Map<String,Object>> selectqx(@Param("rid") String rid,@Param("username") String username);

}
