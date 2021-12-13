package com.example.demo.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@Getter
@Setter
@NoArgsConstructor
public class ProcessStatus {
    int ramUsed;
    int executionTime;


}
