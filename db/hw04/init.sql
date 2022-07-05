create table Lecturers (
    lecturerId int primary key,
    lecturerName varchar(50) not null
);

create table Groups (
    groupId int primary key,
    groupName varchar(50) unique not null
);

create table Students (
    studentId int primary key,
    studentName varchar(50) not null,
    groupId int not null references Groups (groupId)
);
    
create table Courses (
    courseId int primary key,
    courseName varchar(50) not null
);

create table CourseGroups (
    groupId int references Groups (groupId),
    courseId int references Courses (courseId),
    lecturerId int not null,
    primary key (groupId, courseId)
);

create table Marks (
    studentId int references Students (studentId),
    courseId int references Courses (courseId),
    value int not null,
    primary key (studentId, courseId),
    CHECK (value BETWEEN 1 AND 5)
);