package com.aniu.quartz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private int id;
    private String corn;
    private String job;
    private String jobName;
    private String jobGroup;
    private String triggerName;
    private String triggerGroup;
}
