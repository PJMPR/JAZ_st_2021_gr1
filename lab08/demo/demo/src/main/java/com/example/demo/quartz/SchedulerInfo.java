package com.example.demo.quartz;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
@Getter
@Setter
@Configuration
public class SchedulerInfo implements Serializable {

    private int totalFireCount;
    private boolean runForever;
    private int repeatIntervals;
    private int startHour;
}
