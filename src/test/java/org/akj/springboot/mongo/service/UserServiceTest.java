package org.akj.springboot.mongo.service;

import org.akj.springboot.mongo.bean.GenderEnum;
import org.akj.springboot.mongo.bean.User;
import org.akj.springboot.mongo.bean.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
    }


    @Test
    void create() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName("Jamie");
        userInfo.setLastName("Zhang");
        userInfo.setAge(31);
        userInfo.setGender(GenderEnum.MALE);
        userInfo.setPhone("13991999999");

        User u = new User();
        u.setUserInfo(userInfo);
        u.setAuthType("PASSWORD");
        u.setUserName("jamie-002");
        u.setPassword("123456");
        Date date = new Date();
        u.setCreateDate(date);
        u.setLastUpdateDate(date);

        User user = userService.create(u);

        Assertions.assertNotNull(user.getId());

    }

    @Test
    public void createMany() {
        int size = 10000;
        List<String> authTypes = new ArrayList<>();
        authTypes.addAll(Arrays.asList(new String[]{"WECHAT", "GITHUB", "FACEBOOK", "PASSWORD", "OTP"}));
        for (int i = 0; i < size; i++) {
            try {
                User u = new User();

                u.setAuthType(authTypes.get((int) (Math.random() * 5)));
                u.setUserName("Test-" + i + 1);

                if (Arrays.asList(new String[]{"WECHAT", "GITHUB", "FACEBOOK"}).contains(u.getAuthType())) {
                    u.setToken(UUID.randomUUID().toString());
                } else {
                    u.setPassword(UUID.randomUUID().toString().substring(0, 8));
                }

                u.setSchemaVersion("1.0");

                UserInfo userInfo = new UserInfo();
                userInfo.setAge((int) (Math.random() * 60) + 1);
                if (((int) (Math.random() * 60) + 1) % 2 == 0) userInfo.setGender(GenderEnum.MALE);
                userInfo.setFirstName(Arrays.asList(new String[]{"King", "Jerry", "Tom", "Johnny", "Johson", "Daniel",
                        "Kobe",
                        "James", "Michael", "Charles", "William", "Vic"}).get((int) (Math.random() * 12)));
                userInfo.setPhone(generateMobilePhone());
                u.setUserInfo(userInfo);

                userService.create(u);
            } catch (Exception e) {

            }
        }

    }

    private String generateMobilePhone() {
        StringBuilder builder = new StringBuilder("+861");
        //builder.append();
        int k = 0;
        while (k < 1) {
            k = (int) (Math.random() * 10);
        }

        for (int i = 0; i < 9; i++) {
            builder.append((int) (Math.random() * 10));
        }

        return builder.toString();
    }

    @Test
    void findByUserName() {
    }
}