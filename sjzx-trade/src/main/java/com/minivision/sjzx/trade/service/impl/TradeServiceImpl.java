package com.minivision.sjzx.trade.service.impl;

import com.minivision.sjzx.trade.dao.TradeDao;
import com.minivision.sjzx.trade.entity.Trade;
import com.minivision.sjzx.trade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @descriptions:
 * @author: 研发常晨
 * @date: 2019/8/21 17:26
 * @version: 1.0
 */
@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeDao tradeDao;

    @Override
    public Trade findByCodeOrName(Map<String,Object> codeOrName) {
        return tradeDao.findByCodeOrName(codeOrName);
    }

    @Override
    public Trade findByCode(String code) {
        return tradeDao.findByCode(code);
    }

    @Override
    public List<Trade> findByParentId(String parentId) {
        return tradeDao.findByParentId(parentId);
    }

    @Override
    public List<Map<String, Object>> listWithGb() {
        return tradeDao.listWithGb();
    }
}
