package com.aniu.quartz.dao;

import com.aniu.quartz.entity.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskDao {
    List<Task> selectTask();
}
