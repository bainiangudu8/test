package com.minivision.sjzx.trade.entity;

/**
 * @descriptions:
 * @author: 研发常晨
 * @date: 2019/8/21 16:41
 * @version: 1.0
 */
public class Trade {

    //行业编码
    private String code;

    //父类行业节点
    private String parentId;

    //行业名称
    private String name;

    //行业描述
    private String desc;

    public Trade() {
    }

    public Trade(String code, String parentId, String name, String desc) {
        this.code = code;
        this.parentId = parentId;
        this.name = name;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "code='" + code + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
