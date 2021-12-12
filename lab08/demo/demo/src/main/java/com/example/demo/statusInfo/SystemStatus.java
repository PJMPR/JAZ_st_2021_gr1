package com.example.demo.statusInfo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
@Getter
@Setter
@Component
public class SystemStatus {
    boolean isWorking;
    Date reloadStarted;
    Date reloadEnded;
    int moviesToUpdate;
    int progress;
    ArrayList<ProcessStatus> stepFinished;

}
