package org.java.service;

import java.util.List;
import java.util.Map;

public interface Analyzeservice {

    //查询所有仓库信息
    public List<Map<String,Object>> selectjt();
    //查询所有仓库信息
    public List<Map<String,Object>> selectyz();
    //查询所有仓库信息
    public List<Map<String,Object>> selectkh();


}
