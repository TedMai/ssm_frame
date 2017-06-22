package com.yingjun.ssm.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.UUIDCodec;
import com.yingjun.ssm.entity.App;
import com.yingjun.ssm.user.dao.AppDao;
import com.yingjun.ssm.util.DateUtils;
import com.yingjun.ssm.util.StringUtils;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.UUID;

/**
 * AppServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>06/21/2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class AppServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppDao appDao;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: setDao(AppDao dao)
     */
    @Test
    public void testSetDao() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: enable(Boolean isEnable, List<String> idList)
     */
    @Test
    public void testEnable() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: save(App t)
     */
    @Test
    public void testSave() throws Exception {
//        App app = appDao.findByCode("33");
//        System.out.println(JSON.toJSON(app));
        System.out.println("----------------");
        App app = new App();
        app.setId(StringUtils.randomUUID());
        app.setCode("99");
        app.setCreateTime(DateUtils.getCurrentTimestamp());
        app.setIsEnable(true);
        app.setSort(88);
        app.setName("呆呆呆呆呆呆 ");
        int count = appDao.insert(app);
        System.out.println(count);
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
        System.out.println(StringUtils.randomUUID());
    }
    /**
     * Method: findByAll(String name)
     */
    @Test
    public void testFindByAll() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findPaginationByName(String name, Pagination<App> p)
     */
    @Test
    public void testFindPaginationByName() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findByCode(String code)
     */
    @Test
    public void testFindByCode() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findByUserId(Boolean isEnable, String userId)
     */
    @Test
    public void testFindByUserId() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: deleteById(List<String> idList)
     */
    @Test
    public void testDeleteById() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findAppCodeByUserId(Boolean isEnable, String userId)
     */
    @Test
    public void testFindAppCodeByUserId() throws Exception {
//TODO: Test goes here... 
    }


} 
