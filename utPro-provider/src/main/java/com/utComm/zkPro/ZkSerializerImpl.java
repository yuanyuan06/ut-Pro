package com.utComm.zkPro;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

/**
 * Created by Administrator on 2015/12/29.
 */
public class ZkSerializerImpl implements ZkSerializer {
    public byte[] serialize(Object o) throws ZkMarshallingError {
        return o.toString().getBytes();
    }

    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        return new String(bytes);
    }
}
