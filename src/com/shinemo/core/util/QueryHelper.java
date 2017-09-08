package com.shinemo.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/8/16.
 */
public class QueryHelper {
    private String fromClause;
    private String whereClause;
    private String orderClause;
    private List<Object> parameters;
    public static String ORDER_BY_DESC = "DESC";
    public static String ORDER_BY_ASC = "ASC";

    //from子句
    public QueryHelper(Class clazz, String alias) {
        fromClause = "From" + " " + clazz.getSimpleName() + " " + alias;
    }

    //where子句
    public void addCondition(String condition, Object... params) {
        if (whereClause.length() > 1) {
            whereClause += " AND" + condition;
        } else {
            whereClause = " where" + condition;
        }
        if (parameters == null) {
            parameters = new ArrayList<>();
        }
        if (params != null) {
            for (Object param : params) {
                parameters.add(param);
            }
        }
    }

    //orderBy子句
    public void addOrderByProperty(String property, String order) {
        if (orderClause.length() > 1) {
            orderClause += "," + property + " " + order;
        } else {
            orderClause = " ORDER BY " + property + " " + order;
        }
    }

    public String getHqlList() {
        return fromClause + whereClause + orderClause;
    }

    public List<Object> getParameters() {
        return parameters;
    }
}
