-- Exported from QuickDBD: https://www.quickdatabasediagrams.com/
-- Link to schema: https://app.quickdatabasediagrams.com/#/d/U3g56Y
-- NOTE! If you have used non-SQL datatypes in your design, you will have to change these here.

CREATE TABLE "Groups" (
    "Id" int   NOT NULL,
    "Name" varchar(50)   NOT NULL,
    "YearOfStudy" int   NOT NULL,
    CONSTRAINT "pk_Groups" PRIMARY KEY (
        "Id"
     ),

    CHECK ("YearOfStudy" > 0)
);

CREATE TABLE "Students" (
    "Id" int   NOT NULL,
    "FirstName" varchar(50)   NOT NULL,
    "LastName" varchar(50)   NOT NULL,
    "GroupId" int   NOT NULL,
    CONSTRAINT "pk_Students" PRIMARY KEY (
        "Id"
     )
);

CREATE TABLE "Teachers" (
    "Id" int   NOT NULL,
    "FirstName" varchar(50)   NOT NULL,
    "LastName" varchar(50)   NOT NULL,
    CONSTRAINT "pk_Teachers" PRIMARY KEY (
        "Id"
     )
);

CREATE TABLE "TeachersSubjects" (
    "TeacherId" int   NOT NULL,
    "SubjectId" int   NOT NULL,
    CONSTRAINT "pk_TeachersSubjects" PRIMARY KEY (
        "TeacherId","SubjectId"
     )
);

CREATE TABLE "GroupsSubjects" (
    "GroupId" int   NOT NULL,
    "SubjectId" int   NOT NULL,
    CONSTRAINT "pk_GroupsSubjects" PRIMARY KEY (
        "GroupId","SubjectId"
     )
);

CREATE TABLE "Subjects" (
    "Id" int   NOT NULL,
    "Name" varchar(50)   NOT NULL,
    "TeacherId" int   NOT NULL,
    CONSTRAINT "pk_Subjects" PRIMARY KEY (
        "Id"
     ),
    CONSTRAINT "uc_Subjects_Name" UNIQUE (
        "Name"
    )
);

CREATE TABLE "Marks" (
    "Id" int   NOT NULL,
    "StudentId" int   NOT NULL,
    "SubjectId" int   NOT NULL,
    "Value" int   NOT NULL,
    CONSTRAINT "pk_Marks" PRIMARY KEY (
        "Id"
     ),

    CHECK ("Value" BETWEEN 1 AND 5)
);

ALTER TABLE "TeachersSubjects" ADD CONSTRAINT "fk_TeachersSubjects_TeacherId" FOREIGN KEY("TeacherId")
REFERENCES "Teachers" ("Id");

ALTER TABLE "TeachersSubjects" ADD CONSTRAINT "fk_TeachersSubjects_SubjectId" FOREIGN KEY("SubjectId")
REFERENCES "Subjects" ("Id");

ALTER TABLE "GroupsSubjects" ADD CONSTRAINT "fk_GroupsSubjects_GroupId" FOREIGN KEY("GroupId")
REFERENCES "Groups" ("Id");

ALTER TABLE "GroupsSubjects" ADD CONSTRAINT "fk_GroupsSubjects_SubjectId" FOREIGN KEY("SubjectId")
REFERENCES "Subjects" ("Id");

ALTER TABLE "Students" ADD CONSTRAINT "fk_Students_GroupId" FOREIGN KEY("GroupId")
REFERENCES "Groups" ("Id");

ALTER TABLE "Subjects" ADD CONSTRAINT "fk_Subjects_TeacherId" FOREIGN KEY("TeacherId", "Id")
REFERENCES "TeachersSubjects" ("TeacherId", "SubjectId");

ALTER TABLE "Marks" ADD CONSTRAINT "fk_Marks_StudentId" FOREIGN KEY("StudentId")
REFERENCES "Students" ("Id");

ALTER TABLE "Marks" ADD CONSTRAINT "fk_Marks_SubjectId" FOREIGN KEY("SubjectId")
REFERENCES "Subjects" ("Id");