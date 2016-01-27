package com.utComm;

import com.utComm.ut.dao.PersonDao;
import com.utComm.ut.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application.xml"})
public class App
{

    @Autowired
    private PersonDao personDao;

//    @Test
    public void fdf(){
        System.out.println(personDao.queryAllPerson().size());
    }

//    @Test
    public void testSava(){
        Person person = new Person();
            person.setAge(18);
            person.setName("tt");
            person.setSex(1);
        personDao.savePerson(person);
        System.out.println(person.getId());
    }

//    @Test
    public void testInsert(){

        Person person = new Person();
            person.setAge(18);
            person.setName("tt");
            person.setSex(1);
        personDao.insertPerson(person);
        System.out.println(person.getId());

    }

    @Test
    public void testBatchInsert() throws IOException {
        List<Person> persons = new ArrayList<Person>();

        Person person = new Person();
        person.setAge(18);
        person.setName("tt");
        person.setSex(1);

        Person person1 = new Person();
        person1.setAge(18);
        person1.setName("tt");
        person1.setSex(1);

        persons.add(person);
        persons.add(person1);

        personDao.batchInsertPerson(persons);
        for(Person p: persons){
            System.out.println(p.getId());
        }
    }
}

