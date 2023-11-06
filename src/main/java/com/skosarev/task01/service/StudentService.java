package com.skosarev.task01.service;


import com.skosarev.task01.dao.StudentDAO;
import com.skosarev.task01.dataload.DataLoader;

public class StudentService {
    private final DataLoader dataLoader;
    private final StudentDAO studentDAO;

    public StudentService(DataLoader dataLoader, StudentDAO studentDAO) {
        this.dataLoader = dataLoader;
        this.studentDAO = studentDAO;
    }

    public StudentDAO getDAO() {
        return this.studentDAO;
    }

    public void loadData() {
        dataLoader.loadData();
    }
}
