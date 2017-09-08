package com.shinemo.nsfw.info.service.impl;

import com.shinemo.core.service.impl.BaseServiceImpl;
import com.shinemo.nsfw.info.dao.InfoDao;
import com.shinemo.nsfw.info.entity.Info;
import com.shinemo.nsfw.info.service.InfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by pc on 2017/8/1.
 */
@Service("infoService")
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService{

    private InfoDao infoDao;
    @Resource
    public void setInfoDao(InfoDao infoDao) {
        super.setBaseDao(infoDao);
        this.infoDao = infoDao;
    }
}
