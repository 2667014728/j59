package org.java.service;

import org.apache.ibatis.annotations.Param;
import org.java.dao.InfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: 马果
 * @Date: 2019/6/22 16:16
 * @Description:
 */
@Service
public class InfoService {

    @Autowired
    private InfoMapper infoMapper;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Cacheable("dl")
    public Map<String,Object> dl( String username, String pwd){

        //设置redisTemplate序列化方式
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
         Map<String,Object> user = (Map<String, Object>) redisTemplate.opsForHash().get(username,"dl" );



       if(user==null){
          System.out.println("从数据库中加载数据");
          user = infoMapper.dl(username,pwd);
          if (user==null){
              return null;
          }
          redisTemplate.opsForHash().;
       }else{
           System.out.println("从缓存中加载数据-----------------------");
      }
      return user;
    }
    @Cacheable("selectqx")
    public List<Map<String,Object>> selectqx(String rid,String username ){
        List<Map<String,Object>> role= (List<Map<String, Object>>) redisTemplate.opsForHash().get(username,"selectqx" );
        System.out.println("从数据库中加载数据111111111111");

        if(role==null){
            role = infoMapper.selectqx(rid,username);
            redisTemplate.opsForHash().put(username, "selectqx",role );
        }else{
            System.out.println("从缓存中加载数据-----------------------111111111111");
        }
        return role;
    }
}

