package Aspect;

import Annotaion.AuthorityAnnotation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by panting1 on 2017/7/30.
 */
@Component
@Aspect
public class AuthorityAspect {
    //参数指定logger的名称
    private static Logger logger = LogManager.getLogger("loggerName");

    //@execution(* * *(..))也可以
    @Pointcut("@annotation(Annotaion.AuthorityAnnotation)")
    public void pointcut(){}

    //http://localhost:8080/login2?username=xx&password=123
    @Before(value = "pointcut() && @annotation(authValue)")
    public void beforeCheck(JoinPoint joinPoint, AuthorityAnnotation authValue){
        logger.debug("=========== Before");
        //获取目标方法名称
        logger.debug("目标方法名称：" + joinPoint.getSignature().getName());

        //获取目标方法的参数
        Object[] args = joinPoint.getArgs();

        if (args !=null && args.length > 0){
            for (Object arg : args){
                if (arg instanceof HttpServletRequest){
                    HttpServletRequest request = (HttpServletRequest)arg;
                    logger.debug(arg.toString());
                    logger.debug("username " + request.getParameter("username"));
                    logger.debug("password " + request.getParameter("password"));
                    return;
                }
            }
        }
        throw new RuntimeException("beforeCheck failed");
    }
}
