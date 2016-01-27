package com.utComm.ut.service;


import com.utPro.entity.Person;

import java.util.List;

/**
 * Created by yy on 2016/1/9.
 */
public interface ReceiveMqMsgService {

    List<Person> queryAllPerson();

    void savePerson(Person person);

    void insertPerson(Person person);

    void batchInsertPerson(List<Person> persons);

    List<String> queryWhCodeList();

    void receiveMqMsg();
}

