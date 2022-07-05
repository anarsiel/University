insert into Groups
    (groupId, groupName) values
    (1, 'M3336'),
    (2, 'M3437');

insert into Students
    (studentId, studentName, groupId) values
    (1, 'Name1', 2),
    (2, 'Name2', 1),
    (3, 'Name3', 2);

insert into Courses
    (courseId, courseName) values
    (1, 'Course1'),
    (2, 'Course2'),
    (3, 'Course3');

insert into Lecturers
    (lecturerId, lecturerName) values
    (1, 'Lecturer1'),
    (2, 'Lecturer2'),
    (3, 'Lecturer3');

insert into CourseGroups
    (groupId, courseId, lecturerId) values
    (2, 2, 2),
    (1, 3, 3),
    (2, 1, 1);

insert into Marks
    (studentId, courseId, value) values
    (1, 1, 4),
    (3, 2, 3);