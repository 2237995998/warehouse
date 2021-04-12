package com.ye.warehouse;

import com.ye.warehouse.dao.MessageMapper;
import com.ye.warehouse.entity.SystemManager;
import com.ye.warehouse.util.UserUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @PackageName:com.ye.warehouse
 * @ClassName:Test
 * @Description:
 * @author:何进业
 * @date:2021/4/2 21:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = WarehouseApplication.class)
public class Test {


    @Autowired
    private MessageMapper messageMapper;
    @org.junit.Test
    public void test() {
        SystemManager systemManager = new SystemManager();
        systemManager.setSystemManagerId(5);
        int result = UserUtil.getId(systemManager, 0);
        System.out.println(result);

    }

    @org.junit.Test
    public void messageTest(){
        int count = messageMapper.selectConversationCount(1,1);
        System.out.println(count);
    }
}
