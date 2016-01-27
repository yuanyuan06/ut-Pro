package com.utComm.zkPro;

import java.util.List;

/**
 * Created by Administrator on 2015/12/29.
 */
public interface ZkChildChangeManager {

    void changeData(String parentPath, List<String> currentChilds);
}
