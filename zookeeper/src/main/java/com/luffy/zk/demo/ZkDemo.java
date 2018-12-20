package com.luffy.zk.demo;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @ClassName ZkDemo
 * @Description TODO
 * @Author ps
 * @Date 2018/12/20 6:38 PM
 * @Version 1.0
 **/
public class ZkDemo {

    private static final String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
    private ZooKeeper zooKeeper;

    public static void main(String[] args) {
        ZkDemo zkDemo = new ZkDemo();
        zkDemo.initZk();

        try {
            Stat stat = zkDemo.zooKeeper.exists("/workers",new DemoWatch());
            System.out.println(new Gson().toJson(stat));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            zkDemo.close();
        }
    }

    public void initZk(){
        try {
            zooKeeper = new ZooKeeper(connectString,1500,new DemoWatch());
        } catch (IOException e) {
            throw new RuntimeException("init error");
        }
    }

    public void close(){
        if(!Optional.fromNullable(zooKeeper).isPresent()){
            try {
                zooKeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class DemoWatch implements Watcher{

    public void process(WatchedEvent event) {
        System.out.println(new Gson().toJson(event));
    }
}
