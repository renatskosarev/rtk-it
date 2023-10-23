package com.skosarev.task01;


import com.skosarev.task01.datastructures.ClassroomDataGroups;
import com.skosarev.task01.datastructures.PersonAgeDataGroups;
import com.skosarev.task01.datastructures.PersonNameDataGroups;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String FILE_PATH = "";

    public static void main(String[] args) throws IOException {
        ClassroomDataGroups classroomDataGroups = new ClassroomDataGroups();
        PersonNameDataGroups personNameDataGroups = new PersonNameDataGroups();
        PersonAgeDataGroups personAgeDataGroups = new PersonAgeDataGroups();


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH)))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");

                String surname = values[0];
                String name = values[1];
                int age = Integer.parseInt(values[2]);
                int group = Integer.parseInt(values[3]);
                int[] grades = Arrays.stream(values).skip(4).mapToInt(Integer::parseInt).toArray();

                Person person = new Person(name, surname, age, group, grades);

                classroomDataGroups.addPerson(person);
                personNameDataGroups.addPerson(person);
                personAgeDataGroups.addPerson(person);
            }
        }


        /*
            1 — Вычисление средней оценки в старших классах
            Используется структура данных с группировкой по классам,
            так как она обеспечивает наилучшую эффективность в этой задаче — возвращает группы по классам за O(1)
         */
        double sum = 0;
        int count = 0;
        for (Person person : classroomDataGroups.getPersons(10)) {
            sum += person.averageGrade();
            count += 1;
        }
        for (Person person : classroomDataGroups.getPersons(11)) {
            sum += person.averageGrade();
            count += 1;
        }
        System.out.println("1.\nAverage grade: " + sum / count);


        /*
            2 — Поиск всех отличников, старше 14 лет
            Используется структура данных с группировкой по возрасту,
            так как она возвращает группы за O(1)
        */
        System.out.println("\n2.");
        count = 0;
        for (Person person : personAgeDataGroups.getPersonsAgeGreaterThan(13)) {
            if (person.isExcellentStudent()) {
                count++;
                System.out.println(person);
            }
        }


         /*
            3 — Поиск ученика по фамилии
            Используется группировка по первой букве алфавита
            после получения группы выбираем из неё тех учеников, фамилия которых совпадает с введённой
            оценка времени — получаем всю группу за O(1), после перебираем каждый элемент из этой группы
         */
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("\n3.\nEnter the lastname: ");
            for (Person person : personNameDataGroups.getBySurname(scanner.nextLine())) {
                System.out.println(person);
            }
        }

    }
}