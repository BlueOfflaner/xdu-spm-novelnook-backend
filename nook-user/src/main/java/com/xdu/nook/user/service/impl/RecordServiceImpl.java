package com.xdu.nook.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdu.nook.user.entity.Record;
import com.xdu.nook.user.service.RecordService;
import com.xdu.nook.user.mapper.RecordMapper;
import org.springframework.stereotype.Service;

/**
* @author violet
* @description 针对表【record】的数据库操作Service实现
* @createDate 2023-04-06 21:51:11
*/
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
implements RecordService{

}
