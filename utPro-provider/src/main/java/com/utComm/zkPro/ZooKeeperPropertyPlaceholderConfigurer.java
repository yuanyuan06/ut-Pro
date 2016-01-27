package com.utComm.zkPro;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContextException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2015/12/29.
 */
public class ZooKeeperPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer implements Watcher{

    public String zkhost;
    public String znodes;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //重写zookeeper中存储的配置
    private List<String> overrideLocaltions;

    public List<String> getOverrideLocaltions() {
        return overrideLocaltions;
    }

    public void setOverrideLocaltions(List<String> overrideLocaltions) {
        this.overrideLocaltions = overrideLocaltions;
    }

    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
//          移至最后一行执行
//        super.processProperties(beanFactoryToProcess, props);

         zkhost = props.getProperty("zk.host");
         znodes = props.getProperty("zk.cofing.root");

        ZooKeeper zk;
        try {
             zk = new ZooKeeper(zkhost, 3000, this);
        } catch (IOException e) {
            logger.error("Failed to connect to zk server" + zkhost, e);
            throw new ApplicationContextException("Failed to connect to zk server" + zkhost, e);
        }

        try {
            for (String znode : znodes.split(",")) {
                List<String> children = zk.getChildren(znode.trim(), true);
                for (String child : children) {
                    try {
                        byte[] bytes = zk.getData(znode + "/" + child, null, null);
                        String value = new String(bytes, "UTF-8");
//                        logger.error(value);

                        logger.info("Zookeeper key:{}\t value:{}",child,value);
                        props.setProperty(child, value);
                    } catch (Exception e) {
                        logger.error("Read property(key:{}) error",child);
                        logger.error("Exception:",e);
                    }
                }
            }
        }catch (KeeperException e) {
            logger.error("Failed to get property from zk server" + zkhost, e);
            throw new ApplicationContextException("Failed to get property from zk server" + zkhost, e);
        } catch (InterruptedException e) {logger.error("Failed to get property from zk server" + zkhost, e);
            throw new ApplicationContextException("Failed to get property from zk server" + zkhost, e);
        } finally {
            try {
                zk.close();
            } catch (InterruptedException e) {
                logger.error("Error found when close zookeeper connection.", e);
            }
        }
        Properties overProps=queryOverrideLocation();
        //将扩展的properties信息覆盖zookeeper获取的属性
        copyProperties(props, overProps);
        logger.error("\r\n zookeeper nodes :{}", props);
        super.processProperties(beanFactoryToProcess, props);

    }

    private Properties queryOverrideLocation(){
        Properties props=new Properties();
        for(String location: overrideLocaltions){
            PathMatchingResourcePatternResolver pmrpr=new PathMatchingResourcePatternResolver();
            try{
                Resource[] resource=pmrpr.getResources(location);
                Properties prop = PropertiesLoaderUtils.loadProperties(resource[0]);
                copyProperties(props,prop);
            }
            catch(Exception e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return props;
    }

    /**
     * 将source中的属性覆盖到dest属性中
     * @param dest
     * @param source
     */
    private void copyProperties(Properties dest,Properties source){

        Enumeration<?> enums=source.propertyNames();

        while(enums.hasMoreElements()){
            String key=(String)enums.nextElement();
            dest.put(key, source.get(key));
        }
    }

    public void process(WatchedEvent event) {

    }
}
