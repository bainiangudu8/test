package com.minivision.sjzx.trade.service.impl;

import com.minivision.sjzx.trade.dao.ItTradeDao;
import com.minivision.sjzx.trade.dao.TradeDao;
import com.minivision.sjzx.trade.entity.Trade;
import com.minivision.sjzx.trade.service.ItTradeService;
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
public class ItTradeServiceImpl implements ItTradeService {

    @Autowired
    private ItTradeDao itTradeDao;


    @Override
    public List<Map<String, Object>> findByFirstCodeOrName(Map<String,Object> codeOrName) {
        return itTradeDao.findByFirstCodeOrName(codeOrName);
    }

    @Override
    public List<Map<String, Object>> findBysecondCodeOrName(Map<String,Object> codeOrName) {
        return itTradeDao.findBysecondCodeOrName(codeOrName);
    }

    @Override
    public List<Map<String, Object>> findBythreeCodeOrName(Map<String,Object> codeOrName) {
        return itTradeDao.findBythreeCodeOrName(codeOrName);
    }

    @Override
    public List<Map<String, Object>> listWithIt() {
        return itTradeDao.listWithIt();
    }
}
