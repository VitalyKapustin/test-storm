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