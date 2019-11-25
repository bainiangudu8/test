package com.minivision.sjzx.trade.controller;

import com.minivision.sjzx.trade.entity.Trade;
import com.minivision.sjzx.trade.service.ItTradeService;
import com.minivision.sjzx.trade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @descriptions:
 * @author: 研发常晨
 * @date: 2019/8/21 17:04
 * @version: 1.0
 */
@RestController
@RequestMapping("/business")
public class TradeController {

    private static final String ONE = "1";//查找该节点名称
    private static final String TWO = "2";//查找该节点父类
    private static final String THREE = "3";//查找该节点子类
    private static final String FOUR = "4";//查找根节点

    private static final String GB = "gb";//查找类型为国标的行业数据
    private static final String IT = "it";//查找类型为互联网的行业数据

    @Autowired
    private TradeService tradeService;

    @Autowired
    private ItTradeService itTradeService;

    @RequestMapping(value = "/getinDustryInfoByCode")
    public Map<String,Object> findByCode(@RequestBody Map<String,String> map){
        String tag = map.get("tag");
        String code = map.get("search_data");
        String type = map.get("search_type");
        Map<String,Object> info = new HashMap<>();
        info.put("msg","输入信息有误或该数据不存在，请重新输入!");
        info.put("code","10001");
        info.put("data","");
        Map<String,Object> result = info;
        if(!(ONE.equals(type) || TWO.equals(type) || THREE.equals(type) || FOUR.equals(type))){
            return info;
        }
        String name = "";
        Map<String,Object> codeOrName = new HashMap<>();
        codeOrName.put("code",code);
        codeOrName.put("name",name);
        if(GB.equals(tag)){
            try {
                Trade trade = tradeService.findByCodeOrName(codeOrName);
                result = getResult(type,trade);
                result.put("msg","success");
                result.put("code",10000);
            }catch (Exception e){
                e.printStackTrace();
                return info;
            }
        }
        if(IT.equals(tag)){
                List<Map<String,Object>> firstList = itTradeService.findByFirstCodeOrName(codeOrName);
                List<Map<String,Object>> secondList = itTradeService.findBysecondCodeOrName(codeOrName);
                List<Map<String,Object>> threeList = itTradeService.findBythreeCodeOrName(codeOrName);
                if (firstList != null && firstList.size() != 0) {
                    result = getFirstResult(firstList, code, type);
                    return result;
                }
                if (secondList != null && secondList.size() != 0) {
                    result = getSecondResult(secondList, code, type);
                    return result;
                }
                if (threeList != null && threeList.size() != 0) {
                    result = getThreeResult(threeList, code, type);
                    return result;
                }
                return info;
        }
        return result;
    }

    @RequestMapping(value = "/getinDustryInfoByName")
    public Map<String,Object> findByName(@RequestBody Map<String,String> map){
        String tag = map.get("tag");
        String name = map.get("search_data");
        String type = map.get("search_type");
        Map<String,Object> info = new HashMap<>();
        info.put("msg","输入信息有误或该数据不存在，请重新输入!");
        info.put("code","10001");
        info.put("data","");
        Map<String,Object> result = info;
        if(!(ONE.equals(type) || TWO.equals(type) || THREE.equals(type) || FOUR.equals(type))){
            return info;
        }
        String code = "";
        Map<String,Object> codeOrName = new HashMap<>();
        codeOrName.put("code",code);
        codeOrName.put("name",name);
        if(GB.equals(tag)){
            try {
                Trade trade = tradeService.findByCodeOrName(codeOrName);
                result = getResult(type,trade);
                result.put("msg","success");
                result.put("code","10000");
            }catch (Exception e){
                e.printStackTrace();
                return info;
            }
        }
        if(IT.equals(tag)){
            List<Map<String,Object>> firstList = itTradeService.findByFirstCodeOrName(codeOrName);
            List<Map<String,Object>> secondList = itTradeService.findBysecondCodeOrName(codeOrName);
            List<Map<String,Object>> threeList = itTradeService.findBythreeCodeOrName(codeOrName);
            if(firstList!=null && firstList.size()!=0){
                result = getFirstResult(firstList,name,type);
                return result;
            }
            if(secondList!=null && secondList.size()!=0){
                result = getSecondResult(secondList,name,type);
                return result;
            }
            if(threeList!=null && threeList.size()!=0){
                result = getThreeResult(threeList,name,type);
                return result;
            }
            return info;
        }
        return result;
    }

    @RequestMapping("/getinDustryInfo")
    public List<Map<String,Object>> list(@RequestBody Map<String,String> map){
        String tag = map.get("tag");
        List<Map<String,Object>> result = new ArrayList<>();
        Map<String,Object> info = new HashMap<>();
        info.put("msg","输入信息有误或该数据不存在，请重新输入!");
        info.put("code","10001");
        info.put("data","");
        result.add(info);
        if(GB.equals(tag)){
            result = tradeService.listWithGb();
        }
        if(IT.equals(tag)){
            result = itTradeService.listWithIt();
            }
        return result;
    }

    public Map<String,Object> getResult(String type,Trade trade){
        Map<String,Object> resultMap = new HashMap<>();
        if(trade==null){
            return null;
        }
        Trade tradePar = null;
        if(ONE.equals(type)){
            resultMap.put("data",trade);
        }
        if(TWO.equals(type)){
                tradePar = tradeService.findByCode(trade.getParentId());
                if(tradePar==null){
                    resultMap.put("data","");
                }else{
                    resultMap.put("data", tradePar);
                }
        }
        if(THREE.equals(type)){
            List<Trade> trades =  tradeService.findByParentId(trade.getCode());
            if(trades!=null && trades.size()!=0){
                resultMap.put("data",trades);
            }else {
                resultMap.put("data", "");
            }
        }
        if(FOUR.equals(type)){
            while(!"".equals(trade.getParentId())){
                trade = tradeService.findByCode(trade.getParentId());
            }
            resultMap.put("data",trade);
        }
        return resultMap;
    }

    public Map<String,Object> getFirstResult(List<Map<String,Object>> firstList,String codeOrName,String type){
        Map<String,Object> tempMap = null;
        Map<String,Object> resultMap = new HashMap<>();
        List<Map<String,Object>> resultList = new ArrayList<>();
        Map<String,Object> itTrade = firstList.get(0);
        if(ONE.equals(type)){
            tempMap = new HashMap<>();
            tempMap.put("code",itTrade.get("zy_first_code"));
            tempMap.put("code_parent","");
            tempMap.put("name",itTrade.get("zy_first"));
            tempMap.put("desc","");
            resultMap.put("data",tempMap);
        }
        if(TWO.equals(type)){
            resultMap.put("data","");
        }
        if(THREE.equals(type)){
            for(Map<String,Object> single:firstList){
                tempMap = new HashMap<>();
                tempMap.put("code",single.get("zy_second_code"));
                tempMap.put("code_parent",single.get("zy_first_code"));
                tempMap.put("name",single.get("zy_second"));
                tempMap.put("desc","");
                resultList.add(tempMap);
            }
            resultMap.put("data",resultList);
        }
        if(FOUR.equals(type)){
            tempMap = new HashMap<>();
            tempMap.put("code",itTrade.get("zy_first_code"));
            tempMap.put("code_parent","");
            tempMap.put("name",itTrade.get("zy_first"));
            tempMap.put("desc","");
            resultMap.put("data",tempMap);
        }
        resultMap.put("msg","success");
        resultMap.put("code","10000");
        return resultMap;
    }

    public Map<String,Object> getSecondResult(List<Map<String,Object>> secondList,String code,String type){
        Map<String,Object> tempMap = null;
        Map<String,Object> resultMap = new HashMap<>();
        List<Map<String,Object>> resultList = new ArrayList<>();
        Map<String,Object> itTrade = secondList.get(0);
        if(ONE.equals(type)){
            tempMap = new HashMap<>();
            tempMap.put("code",itTrade.get("zy_second_code"));
            tempMap.put("code_parent",itTrade.get("zy_first_code"));
            tempMap.put("name",itTrade.get("zy_second"));
            tempMap.put("desc","");
            resultMap.put("data",tempMap);
        }
        if(TWO.equals(type)){
            tempMap = new HashMap<>();
            tempMap.put("code",itTrade.get("zy_first_code"));
            tempMap.put("code_parent","");
            tempMap.put("name",itTrade.get("zy_first"));
            tempMap.put("desc","");
            resultMap.put("data",tempMap);
        }
        if(THREE.equals(type)){
            for(Map<String,Object> single:secondList){
                tempMap = new HashMap<>();
                tempMap.put("code",single.get("zy_three_code"));
                tempMap.put("code_parent",itTrade.get("zy_second_code"));
                tempMap.put("name",single.get("zy_three"));
                tempMap.put("desc","");
                resultList.add(tempMap);
            }
            resultMap.put("data",resultList);
        }
        if(FOUR.equals(type)){
            tempMap = new HashMap<>();
            tempMap.put("code",itTrade.get("zy_first_code"));
            tempMap.put("code_parent","");
            tempMap.put("name",itTrade.get("zy_first"));
            tempMap.put("desc","");
            resultMap.put("data",tempMap);
        }
        resultMap.put("msg","success");
        resultMap.put("code","10000");
        return resultMap;
    }

    public Map<String,Object> getThreeResult(List<Map<String,Object>> threeList,String code,String type){
        Map<String,Object> tempMap = null;
        Map<String,Object> resultMap = new HashMap<>();
        List<Map<String,Object>> resultList = new ArrayList<>();
        Map<String,Object> itTrade = threeList.get(0);
        if(ONE.equals(type)){
            tempMap = new HashMap<>();
            tempMap.put("code",itTrade.get("zy_three_code"));
            tempMap.put("code_parent",itTrade.get("zy_second_code"));
            tempMap.put("name",itTrade.get("zy_three"));
            tempMap.put("desc","");
            resultMap.put("data",tempMap);
        }
        if(TWO.equals(type)){
            tempMap = new HashMap<>();
            tempMap.put("code",itTrade.get("zy_second_code"));
            tempMap.put("code_parent",itTrade.get("zy_first_code"));
            tempMap.put("name",itTrade.get("zy_second"));
            tempMap.put("desc","");
            resultMap.put("data",tempMap);
        }
        if(THREE.equals(type)){
            resultMap.put("data","");
        }
        if(FOUR.equals(type)){
            tempMap = new HashMap<>();
            tempMap.put("code",itTrade.get("zy_first_code"));
            tempMap.put("code_parent","");
            tempMap.put("name",itTrade.get("zy_first"));
            tempMap.put("desc","");
            resultMap.put("data",tempMap);
        }
        resultMap.put("msg","success");
        resultMap.put("code","10000");
        return resultMap;
    }
}
