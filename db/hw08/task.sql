1.S

-- Используем unique (так как Id это PK) + 
-- hash (так как Id подходит для запросов in и exists)
-- ДЗ-5.1.1. Информацию о студентах С заданным идентификатором
-- ДЗ-5.2.1. Полную информацию о студентах С заданным идентификатором
-- ДЗ-5.8.1. Суммарный балл Одного студента :StudentId;
create unique index using hash on Students (StudentId);

-- hash (так как Id подходит для запросов in и exists)
-- ДЗ-5.1.2. Информацию о студентах С заданным ФИО 
-- ДЗ-5.2.2. Полную Информацию о студентах С заданным ФИО 
-- ДЗ-6.1.1. Информацию о студентах С заданным ФИО 
create index using hash on Students (StudentName);

-- покрывающий тип + B дерево
-- Дает возможность брать записи только по первым аттрибутам
-- Ускоряет join по GroupId (например, таблицы студентов и таблицы
-- групп).
-- Чтобы не ходить за ними в память добавили малообъемные StudentId
-- и GroupId
-- ДЗ-5.8.3. Суммарный балл Каждой группы (указать GroupName).
-- ДЗ-5.9.3. Средний балл Каждой группы (указать GroupName);
-- ДЗ-5.9.4. Средний балл средних баллов студентов каждой группы.
create index using btree on Students (GroupId, StudentId);

1.G

-- Используем unique (так как Id это PK) + 
-- hash (так как Id подходит для запросов in и exists)
-- ДЗ-5.8.3. Суммарный балл Каждой группы (указать GroupName).
-- ДЗ-5.9.3. Средний балл Каждой группы (указать GroupName);
-- ДЗ-5.9.4. Средний балл средних баллов студентов каждой группы.
create unique index using hash on Groups (GroupId);

-- покрывающий тип + B дерево
-- Дает возможность брать записи только по первым аттрибутам
-- Ускоряет поиск по названию
-- (так как записи отсортированы)
-- Чтобы не ходить за ним в память добавили малообъемный GroupId
-- ДЗ-6.1.2. Информацию о студентах Учащихся в заданной группе
-- ДЗ-7.1.2. Напишите запросы, удаляющие студентов
-- Учащихся в группе :GroupName;
-- ДЗ-7.2.4. Напишите запросы, обновляющие данные студентов: Перевод
-- всех студентов из группы :FromGroupName в группу :GroupName;
create index using btree on Groups (GroupName, GroupId);

1.C

-- Используем unique (так как Id это PK) + 
-- hash (так как Id подходит для запросов in и exists)
-- ДЗ-5.3.1. Информацию о студентах с заданной оценкой по предмету
-- С заданным идентификатором (:CourseId);
-- ДЗ-5.3.2. Информацию о студентах с заданной оценкой по предмету
-- С заданным названием (:CourseName);
-- ДЗ-6.2.2. Полную информацию о студентах, не имеющих оценки по 
-- предмету :CourseId; 
create unique index using hash on Courses (CourseId);

-- покрывающий тип + B дерево
-- Дает возможность брать записи только по первым аттрибутам
-- Ускоряет поиск по названию
-- (так как записи отсортированы)
-- Чтобы не ходить за ним в память добавили малообъемный CourseId
-- ДЗ-5.3.2. Информацию о студентах с заданной оценкой по предмету
-- С заданным названием (:CourseName);
-- ДЗ-6.2.3. Полную информацию о студентах, не имеющих оценки по 
-- предмету :CourseName; 
-- ДЗ-6.2.3. Полную информацию о студентах, не имеющих оценки по 
-- предмету :CourseName, у которых есть этот предмет.
create index using btree on Courses (CourseName, CourseId);

1.L

-- Используем unique (так как Id это PK) + 
-- hash (так как Id подходит для запросов in и exists)
-- ДЗ-5.3.5. Информацию о студентах с заданной оценкой по предмету
-- Который вёл (:LecturerId);
-- ДЗ-5.3.6. Информацию о студентах с заданной оценкой по предмету
-- Который вёл (:LecturerName);
-- ДЗ-5.6.1. Идентификаторы студентов по преподавателю
-- (:LecturerName) Имеющих хотя бы одну оценку у преподавателя;
create unique index using hash on Lecturers (LecturerId);

-- hash (так как Id подходит для запросов in и exists)
-- ДЗ-5.3.4. Информацию о студентах с заданной оценкой по предмету
-- Который у него вёл (:LecturerName);
-- ДЗ-5.3.6. Информацию о студентах с заданной оценкой по предмету
-- Который вёл (:LecturerName);
-- ДЗ-5.6.1. Идентификаторы студентов по преподавателю
-- (:LecturerName) Имеющих хотя бы одну оценку у преподавателя;
create index using hash on Lecturers (LecturerName);

1.P

-- Используем unique (так как это PK) + 
-- hash (так как подходит для запросов in и exists)
-- ДЗ-5.5.1. Для каждого студента ФИО и названия предметов 
-- Которые у него есть по плану;
-- ДЗ-5.5.2. Для каждого студента ФИО и названия предметов 
-- Есть, но у него нет оценки;
-- ДЗ-5.5.3. Для каждого студента ФИО и названия предметов 
-- Есть, но у него не 4 или 5;
create unique index using hash on Plan (GroupId, CourseId);

-- покрывающий тип + B дерево
-- Дает возможность брать записи только по первым аттрибутам
-- Ускоряет поиск по названию
-- (так как записи отсортированы)
-- Чтобы не ходить за ними в память добавили малообъемные
-- CourseId и GroupId
-- ДЗ-5.3.4. Информацию о студентах с заданной оценкой по предмету
-- Который у него вёл (:LecturerId);
-- ДЗ-5.3.3. Информацию о студентах с заданной оценкой по предмету
-- Который вёл (:LecturerName);
-- ДЗ-5.6.3. Идентификаторы студентов по преподавателю
-- (:LecturerName) Имеющих оценки по всем предметам преподавателя;
create index using btree on Plan (LecturerId, CourseId, GroupId);

1.M

-- Используем unique (так как это PK) + 
-- hash (так как подходит для запросов in и exists)
-- ДЗ-6.4.3. Студенты и предметы (StudentName, CourseName), такие
-- что предмет есть в его плане и у студента отсутствует оценка;
-- ДЗ-5.5.2. Для каждого студента ФИО и названия предметов 
-- Есть, но у него нет оценки;
-- ДЗ-5.5.3. Для каждого студента ФИО и названия предметов 
-- Есть, но у него не 4 или 5;
create unique index using hash on Marks (StudentId, CourseId);

-- покрывающий тип + B дерево
-- Дает возможность брать записи только по первым аттрибутам
-- Ускоряет поиск по названию
-- (так как записи отсортированы)
-- Чтобы не ходить за ними в память добавили малообъемные
-- CourseId и GroupId
-- ДЗ-5.3.1. Информацию о студентах с заданной оценкой (:Mark)
-- по предмету Который у него вёл (:LecturerId);
-- ДЗ-5.3.3. Информацию о студентах с заданной оценкой (:Mark)
-- по предмету Который у него вёл (:LecturerName);
-- ДЗ-5.6.3. Идентификаторы студентов по преподавателю
-- (:LecturerName) Имеющих оценки по всем предметам преподавателя;
create index using btree on Marks (Mark, CourseId, StudentId);


2.Q

select avg(Mark) from 
	Groups
	natural join Students
	natural join Marks
	natural join Courses
where 
  Courses.CourseName = :CourseName and
  Groups.GroupName = :GroupName;

2.I

-- чтобы получить CourseId по CourseName
-- и для join с Couses по StudentId
create index using btree on Courses (CourseName, CourseId);

-- чтобы получить GroupId по GroupName
create index using btree on Groups (GroupName, GroupId);

-- для join со Students по GroupId	
create index using btree on Students (GroupId, StudentId);

-- для join с Marks по StudentId	
create index using btree on Marks (StudentId)	

3.1.Q

-- Выбрать всех студентов у которых есть хотя бы одна пятерка
-- (или другая оценка)
select distinct (StudentId) from Marks
 where Mark = 5

3.1.I

-- Многозначный битовый индекс
-- Удобен так как у нас конечное и малое число типов оценок.
create index using bitmap on Marks (Mark);

3.2.Q

-- Выбрать всех студентов имя которых удовлетворяет регулярке
select (StudentId) from Students
 where StudentName like :StudentName

3.2.I
-- Удобный быстрой поиск по фамилии
-- (так как записи отсортированы)
-- Чтобы не ходить за ним в память добавили малообъемный
-- StudentId
create index using btree on Students (StudentName, StudentId);

3.3.Q

-- Выбрать всех студентов имя которых удовлетворяет регулярке
select (LecturerId) from Lecturers
 where LecturerName like :LecturerName

3.3.I
-- Удобный быстрой поиск по фамилии
-- (так как записи отсортированы)
-- Чтобы не ходить за ним в память добавили малообъемный
-- LecturerId
create index using btree on Lecturers (LecturerName, LecturerId);