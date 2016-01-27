package com.utComm;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/6.
 */
public class zkConnect {

    @Test
    public void connect() throws Exception {

        String zkHost = "10.8.17.155";

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkHost, retryPolicy);
        client.start();

        List<String> strings = client.getChildren().forPath("/");
        List<String> list = new ArrayList<String>();


        for(String node: strings){
            String path = "/";
            cycleNode(client, node, list, path);

        }
    }

    private void cycleNode(CuratorFramework client, String node, List<String> list, String path) throws Exception {
        List<String> nodes = client.getChildren().forPath("/");
        if(nodes.size() > 1){
            path = path + node+ "/";
            List<String> nodeList = client.getChildren().forPath(path);
            for(String nd: nodeList){
                cycleNode(client, nd, list, path);
            }
        }else{
            list.add(node);
            System.out.println(node);
        }
    }
}
