package com.example.tilas.service.impl;

import com.example.tilas.mapper.OperateLogMapper;
import com.example.tilas.pojo.OperateLog;
import com.example.tilas.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
public class DeptLogServiceImp implements DeptLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(OperateLog operateLog) {
        operateLogMapper.insert(operateLog);
    }
}
