package com.utComm.ut.dao;



import java.util.List;

/**
 * Created by Administrator on 2015/12/25.
 */
public interface PersonDao {

    List<Person> queryAllPerson();
    
    void savePerson(Person person);

    void insertPerson(Person person);

    void batchInsertPerson(List<Person> persons);

    List<String> queryWhCodeList();
}
