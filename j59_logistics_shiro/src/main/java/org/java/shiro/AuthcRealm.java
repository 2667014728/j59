package org.java.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.java.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.testng.annotations.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 马果
 * @Date: 2019/6/27 14:20
 * @Description:
 */
public class AuthcRealm extends AuthorizingRealm {
    @Autowired
    private InfoService infoService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession ses;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("####################################正在进行授权操作");
        //获得用户的凭证（用户名），根据用户名到数据表中，加载用户的访问权限
        String principal = (String) principals.getPrimaryPrincipal();
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        System.out.println("读取缓存中用户信息"+principal);
        List<Map<String,Object>> role= (List<Map<String, Object>>) redisTemplate.opsForHash().get(principal,"selectqx" );
        if (role==null){
            Map<String,Object>  user=infoService.dl(principal,"111");
            System.out.println(user);
            System.out.println(user.get("user_name")+"---------------------------1");
            String rida=((Integer)user.get("rid")).toString();
            System.out.println("缓存中没有数据");
            role=infoService.selectqx(rida,user.get("user_name").toString());
            if (role==null){
                return null;
            }

        }
        List<String> permissions = new ArrayList<>();
        for ( Map<String,Object> rolea : role
             ) {
            permissions.add("user:"+rolea.get("Permissions"));
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
       Cookie[] cookie = request.getCookies();//获取的是请求里的所有cookie组成的数组
        Cookie cookie1=null;
      for(int i=0;i<cookie.length;i++){
           if("username".equals(cookie[i].getName())){
               System.out.println("找到cookie1了");
             cookie1=cookie[i];
             break;
           }
       }
        SimpleAuthenticationInfo info=null;
        //获得token中的用户凭证，即为用户名
        String principal  = token.getPrincipal().toString();
        if (cookie1==null){
            Map<String,Object> user=null;
            user=infoService.dl(principal,"111");
            //到数据库中，判断，用户名是存在,如果不存在，返回null,存在则返回用 户的正确信息，包含正确的密码
            if(user==null){
                System.out.println("------------------用户名不存在");
                return null;//用户名不存在
            }
            Cookie cookie3 = new Cookie("username",user.get("user_name").toString());
            response.addCookie(cookie3);//放松到客户段
            ses.setAttribute("usera",user);
            //123,accp,3
            String pwd = user.get("user_pwd").toString();//从数据库中，查询到的正确的密码
            String salt="accp";
            info = new SimpleAuthenticationInfo(principal,pwd, ByteSource.Util.bytes(salt),"myrealm"  );

        }else {
            String principa2  = token.getPrincipal().toString();
            RedisSerializer redisSerializer = new StringRedisSerializer();
            redisTemplate.setKeySerializer(redisSerializer);
            System.out.println("读取缓存中用户信息"+cookie1.getValue());
            Map<String,Object> user1 = (Map<String, Object>) redisTemplate.opsForHash().get(principa2,"dl" );
            if (user1==null){
                System.out.println("------------------用户名不存在");
                return null;//用户名不存在
            }
            ses.setAttribute("usera",user1);
            String pwd = user1.get("user_pwd").toString();//从数据库中，查询到的正确的密码
            System.out.println(pwd);
            String salt="accp";
            info = new SimpleAuthenticationInfo(principal,pwd, ByteSource.Util.bytes(salt),"myrealm"  );
        }
        System.out.println("登陆成功");
        return info;
    }

    @Test
    public void testMD5(){
        Md5Hash md5 = new Md5Hash("123", "accp",3 );
        System.out.println("加密后的密码是:"+md5.toString());
    }

    @Test
    public void test(){
        Map<String,Object>  user1=infoService.dl("admin","111");
        System.out.println(user1);
        System.out.println((String) user1.get("rid")+"1111");
    }
}
