package com.minivision.sjzx.trade.dao;

import java.util.List;
import java.util.Map;

public interface ItTradeDao {

    List<Map<String,Object>> findByFirstCodeOrName(Map<String,Object> codeOrName);
    List<Map<String,Object>> findBysecondCodeOrName(Map<String,Object> codeOrName);
    List<Map<String,Object>> findBythreeCodeOrName(Map<String,Object> codeOrName);
    List<Map<String,Object>> listWithIt();
}

