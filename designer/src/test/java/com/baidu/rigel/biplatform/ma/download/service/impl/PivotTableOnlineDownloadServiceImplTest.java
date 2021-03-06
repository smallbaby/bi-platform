/*
 * Copyright 2000-2011 baidu.com All right reserved. 
 */
package com.baidu.rigel.biplatform.ma.download.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.rigel.biplatform.ac.query.MiniCubeConnection;
import com.baidu.rigel.biplatform.ac.query.MiniCubeDriverManager;
import com.baidu.rigel.biplatform.ac.query.data.DataModel;
import com.baidu.rigel.biplatform.ac.query.model.ConfigQuestionModel;
import com.baidu.rigel.biplatform.ac.query.model.QuestionModel;
import com.baidu.rigel.biplatform.ma.download.DownloadType;
import com.baidu.rigel.biplatform.ma.download.service.DownloadServiceFactory;
import com.baidu.rigel.biplatform.ma.download.service.DownloadTableDataService;
import com.baidu.rigel.biplatform.ma.report.model.LogicModel;

/**
 * pivotTable在线下载测试
 * @author yichao.jiang 2015年6月2日 下午4:59:02
 */
@RunWith(PowerMockRunner.class)  
@PrepareForTest({ MiniCubeDriverManager.class})
public class PivotTableOnlineDownloadServiceImplTest {
    
    /**
     * 日志对象 
     */
    private static final Logger LOG = LoggerFactory.getLogger(PivotTableOnlineDownloadServiceImplTest.class);
    
    /**
     * 
     */
    @Test
    public void test() {
        DownloadType downloadType = DownloadType.PIVOT_TABLE_ONLINE;
        DownloadTableDataService downService = DownloadServiceFactory.getDownloadTableDataService(downloadType);
        
        // Mock一些数据对象
        LogicModel logicModel = PowerMockito.mock(LogicModel.class);        
        ConfigQuestionModel questionModel = PowerMockito.mock(ConfigQuestionModel.class);       
        MiniCubeConnection connection = PowerMockito.mock(MiniCubeConnection.class);
        DataModel dataModel = PowerMockito.mock(DataModel.class);
        // 假定条件
        PowerMockito.mockStatic(MiniCubeDriverManager.class);
        PowerMockito.when(MiniCubeDriverManager.getConnection(Mockito.anyObject())).thenReturn(connection); 
        PowerMockito.when(connection.query(questionModel)).thenReturn(dataModel);
        String csvString = downService.downloadTableData(questionModel, logicModel);
        Assert.assertNotNull(csvString);
        LOG.debug(csvString);
    }
    
}
