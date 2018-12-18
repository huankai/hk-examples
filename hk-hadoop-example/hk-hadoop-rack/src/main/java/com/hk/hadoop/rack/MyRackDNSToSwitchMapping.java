package com.hk.hadoop.rack;

import org.apache.hadoop.net.DNSToSwitchMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Hadoop 机架感知（DataNode 副本节点选择）
 *
 * @author sjq-278
 * @date 2018-11-21 11:43
 */
public class MyRackDNSToSwitchMapping implements DNSToSwitchMapping {
    @Override
    public List<String> resolve(List<String> list) {
        List<String> result = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (String ip : list) {
                int ipNumber;
                if (ip.startsWith("192.168")) {
                    ipNumber = Integer.parseInt(ip.substring(ip.lastIndexOf(".") + 1));
                } else {
                    ipNumber = Integer.parseInt(ip.substring(ip.length()));
                }

                if (ipNumber > 129) {
                    result.add("/rack1/" + ipNumber);
                } else {
                    result.add("/rack2/" + ipNumber);
                }
            }
        }
        return result;
    }

    @Override
    public void reloadCachedMappings() {

    }

    @Override
    public void reloadCachedMappings(List<String> list) {

    }
}
