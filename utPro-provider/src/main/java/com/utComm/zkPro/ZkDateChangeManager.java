package com.utComm.zkPro;

/**
 * Created by Administrator on 2015/12/29.
 */
public interface ZkDateChangeManager {
    void changeData(String dataPath,Object data);

    void deleteData(String dataPath);
}
