insert into "Groups" (
	"Id", "Name", "YearOfStudy"
)
values
	(1, 'M3437', 4),
	(2, 'M3137', 1),
	(3, 'M3436', 4);

insert into "Students" (
	"Id", "FirstName", "LastName", "GroupId"
)
values
	(1, 'Благой', 'Димитров', 1),
	(2, 'Андрей', 'Пивоваров', 2),
	(3, 'Геннадий', 'Нестеров', 3),
	(4, 'Софья', 'Тихонова', 1);

insert into "Teachers" (
	"Id", "FirstName", "LastName"
)
values
	(1, 'Борис', 'Борисов'),
	(2, 'Андрей', 'Андреев'),
	(3, 'Валентина', 'Валентинова');

WITH tmp AS (
    INSERT INTO "Subjects" ("Id", "Name","TeacherId")
      VALUES (1, 'Пивоварение', 1)
  )
  INSERT INTO "TeachersSubjects" ("TeacherId", "SubjectId")
    VALUES (1, 1);

WITH tmp AS (
    INSERT INTO "Subjects" ("Id", "Name","TeacherId")
      VALUES (2, 'Зельеварение', 2)
  )
  INSERT INTO "TeachersSubjects" ("TeacherId", "SubjectId")
    VALUES (2, 2);

insert into "Marks" (
	"Id", "StudentId", "SubjectId", "Value"
)
values
	(1, 1, 1, 5),
	(2, 4, 1, 5),
	(3, 2, 1, 1),
	(4, 2, 2, 4);