package com.minivision.sjzx.trade.service;

import com.minivision.sjzx.trade.entity.Trade;

import java.util.List;
import java.util.Map;

public interface TradeService {

    Trade findByCodeOrName(Map<String,Object> codeOrName);
    Trade findByCode(String code);
    List<Trade> findByParentId(String parentId);
    List<Map<String,Object>> listWithGb();
}
