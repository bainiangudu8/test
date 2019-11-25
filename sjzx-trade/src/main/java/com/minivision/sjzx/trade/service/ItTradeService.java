package com.minivision.sjzx.trade.service;

import com.minivision.sjzx.trade.entity.Trade;

import java.util.List;
import java.util.Map;

public interface ItTradeService {

    List<Map<String,Object>> findByFirstCodeOrName(Map<String,Object> codeOrName);
    List<Map<String,Object>> findBysecondCodeOrName(Map<String,Object> codeOrName);
    List<Map<String,Object>> findBythreeCodeOrName(Map<String,Object> codeOrName);
    List<Map<String,Object>> listWithIt();
}
