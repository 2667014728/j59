package org.java.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 马果
 * @Date: 2019/2/14 11:52
 * @Description:
 */
public class ProductOrder implements Serializable {

    private Integer id;//编号
    private String productName;//产品名称
    private String tradeNode;//订单号
    private Integer price;//单价
    private Date createDate;//订单的创建时间
    private Integer userId;//用户id
    private String userName;//用户名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTradeNode() {
        return tradeNode;
    }

    public void setTradeNode(String tradeNode) {
        this.tradeNode = tradeNode;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
