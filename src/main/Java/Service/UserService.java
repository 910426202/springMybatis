package Service;

import Entity.User;
import MapperInterface.UserMapperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by panting1 on 2017/7/23.
 */
@Transactional
@Service
public class UserService {
    //通过MapperScannerConfigurerkey可以直接调用映射接口，就可以直接向数据库执行sql
    @Autowired
    private UserMapperDao userMapperDao;

    public User getUser(Integer id){
        return userMapperDao.find(id);
    }

    public List<User> query(User user){
        return userMapperDao.query(user);
    }
}
