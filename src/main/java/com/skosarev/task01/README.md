### Примеры HTTP-запросов
1. Получение всех учеников и их средних оценок по имени группы:
   ```http request
   GET http://localhost:8080/students?group=10
   ```
   ```http request
   GET http://localhost:8080/students?group=мат-12
   ```
2. Изменение оценки по _имени+фамилии ученика_ и _имени группы_:
   ```http request
   PUT http://localhost:8080/students?name=Ренат Скосарев&group=12&subject=math&newMark=4
   ```

### Примеры команд

1. Поиск всех учеников с фамилией Семенов:
   ```
   average-mark-by-lastname Семенов
   ```
2. Определение средней оценки в 10 классах:
   ```
   average-mark 10
   ```
3. Определение средней оценки в начальной школе:
   ```
   average-mark 1 2 3 4
   ```
4. Поиск отличников 14+ лет:
   ```
   excellent-students-older-than 14
   ```
5. Выход:
   ```quit```
