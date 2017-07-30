package Controller;

import Annotaion.AuthorityAnnotation;
import Entity.User;
import Service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by panting1 on 2017/7/25.
 */
//@RestController 只返回字符串
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    //参数指定logger的名称
    private static Logger logger = LogManager.getLogger("loggerName");

    //在web.xml中配置了welcomfile后此处的根url映射无效
    @RequestMapping("/")
    public String index(){
        logger.debug("***************** redirect:login");
        //重定向
        return "home";
    }

    @RequestMapping("/home")
    public String home(){
        logger.debug("***************** redirect:login");
        //重定向
        return "home";
    }


    //防止中文返回显示为？
    @RequestMapping(value = "/find/{id}",produces="text/plain;charset=UTF-8")
    @ResponseBody //@Controller+@ResponseBody = @RestController
    public String find(@PathVariable("id") Integer id){
        logger.debug("------- find");
//        如果返回class类型则需要添加json支持
        return userService.getUser(id).toString();
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @AuthorityAnnotation
    public String login(@RequestParam("username") String username,
                      @RequestParam("password") String password){
        logger.debug("-------------------- login " + username + " " + password);
        //这里可以进行权限验证
        return "success";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam("username") String username){
        logger.debug("------------ query");
        User user = new User();
        user.setUsername(username);
        logger.debug(userService.query(user));
        return userService.query(user).toString();
    }

//    使用aspect进行权限验证
    @RequestMapping("/login2")
    @AuthorityAnnotation
    public String login2(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse){
        logger.debug("-------- login2");
        return "redirect:/";
    }
}
