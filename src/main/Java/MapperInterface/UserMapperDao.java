package MapperInterface;

import Entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by panting1 on 2017/7/23.
 */
@Repository//value=userMapperDao
public interface UserMapperDao{
    User find(Integer id);
    List<User> query(User user);
}