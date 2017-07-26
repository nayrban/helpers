package com.gps.hc.enumeration.datasources;

import java.io.Serializable;
import java.util.List;


public class CustomerDataSource implements Serializable {

    private static  final  String DEFAULT_QUERY = "select type from coupon_type";
    private static  final  String DEFAULT_QUERY2 = "select type from customer_type";
    private MySqlConection mySqlConection;

    public CustomerDataSource() {
        mySqlConection = new MySqlConection();
    }

    public List<String> getCounpons() {
        return mySqlConection.executeQuery(DEFAULT_QUERY);
    }

    public List<String> getCustomerType() {
        return mySqlConection.executeQuery(DEFAULT_QUERY2);
    }
}
