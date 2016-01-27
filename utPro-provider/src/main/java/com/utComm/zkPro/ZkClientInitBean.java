package com.utComm.zkPro;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2015/12/29.
 */
public class ZkClientInitBean implements InitializingBean{



    @Autowired(required=false)
    private IZkDataListener iZkDataListener;

    @Autowired(required=false)
    private IZkChildListener iZkChildListener;

    @Autowired(required=false)
    private IZkStateListener iZkStateListener;

    @Autowired(required=false)
    private ZkDateChangeManager zkDateChangeManager;

    @Autowired(required=false)
    private ZkChildChangeManager zkChildChangeManager;

    @Autowired
    private ZkClient zkClient;

    private List<String> dataChangePaths=null;

    private List<String> dataChildPaths=null;

    /**
     * 是否订阅状态改变事件
     */
    private Boolean subscribeStateChange=false;


    public Boolean getSubscribeStateChange() {
        return subscribeStateChange;
    }

    public void setSubscribeStateChange(Boolean subscribeStateChange) {
        this.subscribeStateChange = subscribeStateChange;
    }

    public List<String> getDataChangePaths() {
        return dataChangePaths;
    }

    public void setDataChangePaths(List<String> dataChangePaths) {
        this.dataChangePaths = dataChangePaths;
    }

    public List<String> getDataChildPaths() {
        return dataChildPaths;
    }

    public void setDataChildPaths(List<String> dataChildPaths) {
        this.dataChildPaths = dataChildPaths;
    }

    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub

        if(dataChangePaths!=null){
            for(String path:dataChangePaths){

                zkClient.subscribeDataChanges(path, iZkDataListener);

                zkDateChangeManager.changeData(path, null);
            }
        }

        if(dataChildPaths!=null){
            for(String path:dataChildPaths){

                zkClient.subscribeChildChanges(path, iZkChildListener);

                zkChildChangeManager.changeData(path, null);
            }
        }

        if(subscribeStateChange){
            zkClient.subscribeStateChanges(iZkStateListener);
        }

    }

}
