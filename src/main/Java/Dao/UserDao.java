package Dao;

import Entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by panting1 on 2017/7/23.
 * 使用模板类SqlSessionTemplate访问数据库
 */
@Repository
public class UserDao {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public User getUser(int id){
//        使用SqlSessionTemplate的调用mapper文件中的sql映射项名称一一对应
//        缺点：通过字符串获取SQL只有在运行期才能发现低等的拼写错误
        return (User)sqlSessionTemplate.selectOne("UserMapper.getUser",id);

//        方案2：位mapper文件中的sql项写一个DAO接口，接口的方法名称和sql语句的id
//        获取接口对应的mapper文件中的sql映射项
//        UserMapperDao dao = sqlSessionTemplate.getMapper(UserMapperDao.class);
//        return dao.getUser(id);

//        方案3：在方案2的接口基础上，mybatis-spring中的MapperScannerConfiguer可以讲映射接口直接转为spring的
//                bean这样就可以在service中 直接注入映射接口bean，假设映射接口定义为userMapperDao,在spring配置文件中配置MapperScannerConfiguer
    }
}
