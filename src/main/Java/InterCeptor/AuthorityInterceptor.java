package InterCeptor;

import Annotaion.AuthorityAnnotation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * Created by panting1 on 2017/7/30.
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {
    //参数指定logger的名称
    private static Logger logger = LogManager.getLogger("loggerName");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if (handler instanceof HandlerMethod){
            logger.debug("==== instanceof");
            HandlerMethod hm = (HandlerMethod)handler;
            AuthorityAnnotation authorityAnnotation =
                    hm.getMethod().getAnnotation(AuthorityAnnotation.class);
            logger.debug("===== " + authorityAnnotation.Value());
            if ("yes".equals(authorityAnnotation.Value())){
                //此处验证
                logger.debug("====== 需要验证");
                return true;
            }else{
                logger.debug("不需要验证");
                return false;
            }
        }
        return false;
    }
}
