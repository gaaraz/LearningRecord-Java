
import cn.hutool.crypto.digest.DigestUtil;
import com.example.NettyServerApplication01;
import com.example.dto.UserDto;
import com.example.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/27
 */
@SpringBootTest(classes = NettyServerApplication01.class)
@RunWith(SpringRunner.class)
public class SpringTest {
    @Resource
    private UserService service;

    @Test
    public void testUser(){
        UserDto userDto = new UserDto();
        userDto.setUsername("wangwu");
        userDto.setPassword(DigestUtil.md5Hex("123456"));
        service.addUser(userDto);
    }

    @Test
    public void getUser(){
        UserDto userDto = service.queryUser("zhangsan");
        System.out.println(userDto);
    }
}
