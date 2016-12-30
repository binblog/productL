import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by bin.liang on 2016/12/21.
 */
public class ZkConnectionTest {


    @Test
    public void establishConnection() throws IOException, KeeperException, InterruptedException {
//        ZkConnection zkConnection = new ZkConnection("localhost:" + ZkTestSystem.getInstance().getZkServer().getPort());
//        ZkClient zkClient = new ZkClient(zkConnection);// connect

        int CLIENT_PORT = 2181;

        ZooKeeper zk = new ZooKeeper("localhost:" + CLIENT_PORT,
                5000, new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                System.out.println("已经触发了" + event.getType() + "事件！");
            }
        });

        // 创建一个目录节点
        zk.create("/hello", "hello,zk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);

        // 创建一个子目录节点
        zk.create("/hello/hello2", "hello2, zk".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

        System.out.println(zk.getChildren("/hello", true));

        // 修改子目录节点数据
        zk.setData("/hello/hello2", "modifyChildDataOne".getBytes(), -1);
        System.out.println("目录节点状态：[" + zk.exists("/testRootPath", true) + "]");

        System.out.println(new String(zk.getData("/hello/hello2", true, null)));


//        while (true) {
//            Thread.sleep(1000 * 10);
//            System.out.println("----");
//        }
//        System.out.println(new String(zk.getData("/hello/hello2",true,null)));


        zk.create("/hello/hello3", "hello3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, String name) {
                System.out.println(rc);
                System.out.println(path);
                System.out.println(name);
            }
        }, "1234");


        zk.exists("/hello/hello3", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("111 " + event.getType());
            }
        });

        zk.exists("/hello/hello3", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("222 " + event.getType());
            }
        });


        
        zk.setData("/hello/hello3", "null".getBytes(), 1);
        zk.delete("/hello/hello3", -1);
        zk.delete("/hello/hello2", -1);
        zk.delete("/hello", -1);
    }
}
