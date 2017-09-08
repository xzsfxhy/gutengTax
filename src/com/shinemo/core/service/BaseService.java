package com.shinemo.core.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 2017/8/15.
 */
public interface BaseService<T> {
    //c,增加
    public void save(T entity);
    //d,删除
    public void delete(Serializable id);
    //u,更改
    public void update(T entity);
    //查,根据id查找
    public T findObjectById(Serializable id);
    //查,查找所有
    public List<T> findObjects();
    //查,前台搜索框查询
    public List<T> findObjects(String hql,List<Object> parameters);
}
