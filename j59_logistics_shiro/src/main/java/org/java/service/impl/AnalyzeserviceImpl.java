package org.java.service.impl;

import org.java.dao.analyzeMapper;
import org.java.dao.warehouseMapper;
import org.java.service.Analyzeservice;
import org.java.service.Warehouservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AnalyzeserviceImpl implements Analyzeservice {

    @Autowired
    private analyzeMapper mapper;

    public List<Map<String,Object>> selectjt(){ return mapper.selectjt();}

    public List<Map<String,Object>> selectyz(){return mapper.selectyz();}

    public List<Map<String,Object>> selectkh(){return mapper.selectkh();}


}
