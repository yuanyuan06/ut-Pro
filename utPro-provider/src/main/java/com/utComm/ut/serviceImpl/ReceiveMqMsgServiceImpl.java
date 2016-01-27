package com.utComm.ut.serviceImpl;

import com.utComm.ut.dao.PersonDao;
import com.utComm.ut.entity.Person;
import com.utComm.ut.service.ReceiveMqMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yy on 2016/1/6.
 */
@Service("receiveMqMsgService")
public class ReceiveMqMsgServiceImpl implements ReceiveMqMsgService {

    @Autowired
    private PersonDao personDao;


    @Override
    public List<Person> queryAllPerson() {
        return null;
    }

    @Override
    public void savePerson(Person person) {

    }

    @Override
    public void insertPerson(Person person) {

    }

    @Override
    public void batchInsertPerson(List<Person> persons) {

    }

    @Override
    public List<String> queryWhCodeList() {
        return null;
    }

    @Override
    public void receiveMqMsg() {

    }

}
