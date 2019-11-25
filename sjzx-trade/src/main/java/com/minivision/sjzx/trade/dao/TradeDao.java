package com.minivision.sjzx.trade.dao;

import com.minivision.sjzx.trade.entity.Trade;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface TradeDao {

    Trade findByCodeOrName(Map<String,Object> codeOrName);
    Trade findByCode(String code);
    List<Trade> findByParentId(String parentId);
    List<Map<String,Object>> listWithGb();
}
