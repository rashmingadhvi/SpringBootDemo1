package com.rmk.demo1;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@Component
@Aspect
public class ACLCheckAOP {

    Map<String,String> aclMap = Map.of("admin","user1,user3","dev","user2");
    @Before(value = "@annotation(com.rmk.demo1.AdminUserAnnotation)")
    void checkAdminUsers() throws DemoAppException {

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String user =request.getHeader("user");
        if(Objects.isNull(user) || !aclMap.get("admin").contains(user)){
            throw new DemoAppException(HttpServletResponse.SC_UNAUTHORIZED+"~Access Denied", HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
