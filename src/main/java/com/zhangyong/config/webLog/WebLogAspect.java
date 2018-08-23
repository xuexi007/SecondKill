package com.zhangyong.config.webLog;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * <p>ClassName:  </p>
 * <p>Description: WebLog切面监控Controller访问</p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/7/18 15:51
 */
@Component
@Aspect
@Order(1)
public class WebLogAspect extends BaseAspect {

    protected ThreadLocal<Long> logStartTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.zhangyong.controller.*Controller.*(..)) && !execution(public * com.zhangyong..Controller.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doLogBefore(JoinPoint joinPoint) {
        logStartTime.set(System.currentTimeMillis());
        // 接收到请求,记录请求内容;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录下请求内容;
        UUID uuid = UUID.randomUUID();
        logger.info("LOGINFO---START--->" + uuid.hashCode());
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD :" + request.getMethod());
        logger.info("IP : " + request.getRemoteHost());
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length != 0) {
            logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + " ARGS :" + JSONObject.toJSONString(args[0]));
        } else {
            logger.info("CLASS_METHOD :" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + " ARGS :" + "--NULL--");
        }
        logger.info("LOGINFO-----END---->" + uuid.hashCode());
    }

    @After("webLog()")
    public void doLogAfter(JoinPoint joinPoint) {
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doLogAfterReturning(Object ret) {
        //TODO 处理完请求,返回内容;
        logger.info("RESPONSE : " + ret);
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - logStartTime.get()));
    }

    @AfterThrowing(throwing = "ex", pointcut = "webLog()")
    public void doLogAfterThrowing(Throwable ex) {
        logger.info("EXCEPTION : " + ex.toString());
    }

    /**
     * @param request
     * @auther zhangyong@shopin.cn
     * @desc 获取ip地址信息
     * @date 2018/7/18  17:27
     * @from JDK 1.8
     */
    public String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }
}
