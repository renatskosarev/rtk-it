package com.skosarev.task01.service;

import com.skosarev.task01.dataload.DataLoader;
import com.skosarev.task01.datastructures.DataGroup;

public class StudentService {
    private final DataLoader dataLoader;

    public StudentService(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public void loadData(DataGroup dataGroup) {
        dataLoader.loadData(dataGroup);
    }
}
