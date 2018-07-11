package com.hk.solr.example;

import com.google.common.collect.Maps;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @author: kevin
 * @date 2018-06-27 09:05
 */

public class SolrTest {

    private SolrClient solrClient;

    @Before
    public void init() {
        solrClient = new HttpSolrClient("http://192.168.64.128:8983/solr/test/");
    }

    @Test
    public void query() throws IOException, SolrServerException {
        Map<String, String> map = Maps.newHashMap();
        map.put("q", "*:*");// 查询条件
        SolrParams params = new MapSolrParams(map);
        QueryResponse response = solrClient.query(params);
        SolrDocumentList results = response.getResults();
        results.forEach(result -> {
            System.err.println(result.getFieldNames());
            System.out.println(result.getFieldValueMap());
        });
    }

    @After
    public void distory() {
        if (null != solrClient) {
            try {
                solrClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
