package com.utComm;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
//   @Test
    public void re() throws KeeperException, InterruptedException, UnsupportedEncodingException {

       String node = "/ray";
       ZkConfig zk = new ZkConfig("127.0.0.1",3000);
       ZooKeeper zkConfig = zk.getZkConfig();
       List<String> lists = zkConfig.getChildren(node,true);
       for(String n: lists){
           String nodeValue = new String (zkConfig.getData(node + "/" + n, null, null), "UTF-8");
           System.out.println(n+":"+nodeValue);
       }
   }


    @Test
    public void rr() {
        System.out.println(addString("a", "b", "c", "d"));


    }

    private String addString(String... arr){
        StringBuffer sb = new StringBuffer();
        for(String str: arr){
            sb.append(str);
        }
        return sb.toString();
    }
}

class ZkConfig implements Watcher{

    private String zkhost;
    private Integer outTime;

    public ZkConfig(String zkhost, Integer outTime) {
        this.zkhost = zkhost;
        this.outTime = outTime;
    }

    public ZooKeeper getZkConfig(){
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper("127.0.0.1",3000,this);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return zk;
    }

    public void process(WatchedEvent event) {

    }
}
