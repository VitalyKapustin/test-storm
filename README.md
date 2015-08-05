# test-storm


# storm starting
    ./storm nimbus
    ./storm supervisor
    ./zkServer.sh start

# deploying topology
    ./storm jar [jar file path] [main class]

# activate/deactivate topology
    ./storm activate [topology name]
    ./storm deactivate [topology name]

# kill topology
    ./storm kill [topology name]

# storm UI (browser url: http://{nimbus host}:8080)
    ./storm ui
    
#########################################################################
SETUP CLUSTER: 
* 1. Zookeper:
set up zookeeper configuration file (zoo.cfg):

tickTime=2000
dataDir=d:/tools/zookeeper-3.4.6/data
clientPort=2181
initLimit=5
syncLimit=2
server.1=192.168.15.30:2888:3888
server.2=192.168.15.36:2888:3888


* 2. Run Zookeper on both machines one by one: 
zkServer.cmd
(on linux machines need to pass argument "start")

* 3. Configure Storm (192.168.15.36 - is main host): storm.yaml

storm.zookeeper.servers:
- "192.168.15.30"
- "192.168.15.36"
storm.zookeeper.port: 2181
nimbus.host: "192.168.15.36"
storm.local.dir: "d:/tools/apache-storm-0.9.5/data"
java.library.path: "d:/configurations/jdk/jdk_8/lib"
storm.messaging.transport: backtype.storm.messaging.netty.Context
supervisor.slots.ports:
- 6700
- 6701
- 6702
- 6703

* 4. Run storm:
4.1: storm nimbus (main machine)
4.2 storm ui (main machine)
4.3 storm supervisor (both machines one by one)
4.4 Deploy Topology (main machine): storm jar jar.file path.to.main.class

* Be sure that you have configured STORM_HOME, ZK_HOME.

* Also we set hosts file:

192.168.15.36 wotan
192.168.15.30 sioux

5. Be sure that while configuring topology in java you have set proper number of workers:

    public static void start() {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("transactions", new TransactionSpout(), 4);
        builder.setBolt("saveTransaction", new SaveTransactionBolt(), 6).shuffleGrouping("transactions");

        Config conf = new Config();
        conf.setDebug(false);
        conf.setNumWorkers(2);
        try {
            StormSubmitter.submitTopology("com", conf, builder.createTopology());
        } catch (AlreadyAliveException e) {
            e.printStackTrace();
        } catch (InvalidTopologyException e) {
            e.printStackTrace();
        }
    }