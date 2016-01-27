package com.utComm;

import com.utComm.ut.dao.PersonDao;
import com.utComm.ut.entity.Person;
import com.utComm.ut.eum.EumComm;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Administrator on 2015/12/28.
 */
public class ReflectTest {
//    @Test
    public void rer(){
        Field[] fields = Person.class.getDeclaredFields();
        for(Field field: fields){
            System.out.println(Modifier.toString(2));
            System.out.println(field.getModifiers());
        }
    }

//    @Test
    public void fd(){
        System.out.println(1&2);
        System.out.println(1&1);
        System.out.println(3&3);
        System.out.println(1&3);
    }

//    @Test
    public void rrr(){
        ApplicationContext atx = new ClassPathXmlApplicationContext("application.xml");
        PersonDao personDao = (PersonDao) atx.getBean("personDao");
        System.out.println(personDao.queryAllPerson().size());
    }

    @Test
    public void resr(){
        System.out.println(EumComm.valueOf(0));

    }




}
