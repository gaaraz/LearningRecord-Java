import com.example.springcloud.SecurityMain8888;
import com.example.springcloud.dto.Role;
import com.example.springcloud.dto.User;
import com.example.springcloud.mapper.RoleMapper;
import com.example.springcloud.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = SecurityMain8888.class)
public class MapperTest {

    @Autowired
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    @Test
    public void testUserMapper(){
//        List<User> users = userMapper.selectList(null);
//        System.out.println(users);
        List<Role> roles = roleMapper.selectRolesByPath("/hello");
        System.out.println(roles);
        List<Role> roleList = roleMapper.selectRolesByUserId(1L);
        System.out.println(roleList);
    }

    @Test
    public void TestPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }
}