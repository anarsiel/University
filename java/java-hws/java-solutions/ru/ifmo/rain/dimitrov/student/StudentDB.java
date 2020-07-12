//package ru.ifmo.rain.dimitrov.student;
//
//import info.kgeorgiy.java.advanced.student.Group;
//import info.kgeorgiy.java.advanced.student.Student;
//
//import java.util.*;
//import java.util.function.Function;
//import java.util.function.Predicate;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class StudentDB implements info.kgeorgiy.java.advanced.student.StudentGroupQuery {
//
//    private static final Comparator<Student> STUDENT_BY_NAME_COMPARATOR = Comparator.comparing(Student::getLastName)
//            .thenComparing(Student::getFirstName).thenComparingInt(Student::getId);
//
//    private static final Comparator<Student> STUDENT_BY_ID_COMPARATOR = Comparator.comparingInt(Student::getId)
//            .thenComparing(Student::getLastName).thenComparing(Student::getFirstName);
//
//    private static final Comparator<Group> GROUP_COMPARATOR = Comparator.comparing(Group::getName);
//
//    private static <R> Stream<R> mappingStream(List<Student> students,
//                                               Function<Student, R> mapper) {
//        return students.stream().map(mapper);
//    }
//
//    private static <R> List<R> mapOnStream(List<Student> students, Function<Student, R> mapper) {
//        return mappingStream(students, mapper).collect(Collectors.toList());
//    }
//
//    private static List<Student> sortOnStream(Collection<Student> students, Comparator<Student> comparator) {
//        return students.stream().sorted(comparator).collect(Collectors.toList());
//    }
//
//    private static <R> R findOnStream(Collection<Student> students, Predicate<Student> predicate,
//                                      Comparator<Student> comparator, Collector<Student, ?, R> collector) {
//        return students.stream().filter(predicate).sorted(comparator).collect(collector);
//    }
//
//    @Override
//    public List<String> getFirstNames(List<Student> students) {
//        return mapOnStream(students, Student::getFirstName);
//    }
//
//    @Override
//    public List<String> getLastNames(List<Student> students) {
//        return mapOnStream(students, Student::getLastName);
//    }
//
//    @Override
//    public List<String> getGroups(List<Student> students) {
//        return mapOnStream(students, Student::getGroup);
//    }
//
//    @Override
//    public List<String> getFullNames(List<Student> students) {
//        return mapOnStream(students, (Student s) -> String.format("%s %s", s.getFirstName(), s.getLastName()));
//    }
//
//    @Override
//    public Set<String> getDistinctFirstNames(List<Student> students) {
//        return mappingStream(students, Student::getFirstName)
//                .sorted()
//                .collect(Collectors.toCollection(TreeSet::new));
//    }
//
//    @Override
//    public String getMinStudentFirstName(List<Student> students) {
//        return students
//                .stream()
//                .min(Comparator.comparingInt(Student::getId))
//                .map(Student::getFirstName)
//                .orElse("");
//    }
//
//    @Override
//    public List<Student> sortStudentsById(Collection<Student> students) {
//        return sortOnStream(students, Comparator.comparingInt(Student::getId));
//    }
//
//    @Override
//    public List<Student> sortStudentsByName(Collection<Student> students) {
//        return sortOnStream(students, STUDENT_BY_NAME_COMPARATOR);
//    }
//
//    @Override
//    public List<Student> findStudentsByFirstName(Collection<Student> students, String name) {
//        return findOnStream(students, (Student s) -> name.equals(s.getFirstName()),
//                STUDENT_BY_NAME_COMPARATOR, Collectors.toList());
//    }
//
//    @Override
//    public List<Student> findStudentsByLastName(Collection<Student> students, String name) {
//        return findOnStream(students, (Student s) -> name.equals(s.getLastName()),
//                STUDENT_BY_NAME_COMPARATOR, Collectors.toList());
//    }
//
//    @Override
//    public List<Student> findStudentsByGroup(Collection<Student> students, String group) {
//        return findOnStream(students, (Student s) -> group.equals(s.getGroup()),
//                STUDENT_BY_NAME_COMPARATOR, Collectors.toList());
//    }
//
//    @Override
//    public Map<String, String> findStudentNamesByGroup(Collection<Student> students, String group) {
//        return findOnStream(
//                students,
//                (Student s) -> group.equals(s.getGroup()),
//                Comparator.naturalOrder(),
//                Collectors.toMap(
//                        Student::getLastName, Student::getFirstName,
//                        (String s1, String s2) -> (s1.compareTo(s2) < 0) ? s1 : s2
//                )
//        );
//    }
//
//
//    private static Stream<Group> groupStream(Collection<Student> students,
//                                             Function<Map.Entry<String, List<Student>>, Group> groupConstructor) {
//        return students
//                .stream()
//                .collect(Collectors.groupingBy(Student::getGroup, Collectors.toList()))
//                .entrySet()
//                .stream()
//                .map(groupConstructor)
//                .sorted(GROUP_COMPARATOR);
//    }
//
//    private static List<Group> getGroupQuery(Collection<Student> students, Comparator<Student> comparator) {
//        return groupStream(students, (Map.Entry<String, List<Student>> e) -> new Group(
//                        e.getKey(), e.getValue().stream().sorted(comparator).collect(Collectors.toList())
//                )
//        ).collect(Collectors.toList());
//    }
//
//    private static String maxGroupNameQuery(Collection<Student> students, Comparator<Group> comparator) {
//        return groupStream(students, (Map.Entry<String, List<Student>> e) -> new Group(e.getKey(), e.getValue()))
//                .max(comparator)
//                .map(Group::getName)
//                .orElse("");
//    }
//
//    @Override
//    public List<Group> getGroupsByName(Collection<Student> students) {
//        return getGroupQuery(students, STUDENT_BY_NAME_COMPARATOR);
//    }
//
//    @Override
//    public List<Group> getGroupsById(Collection<Student> students) {
//        return getGroupQuery(students, STUDENT_BY_ID_COMPARATOR);
//    }
//
//    @Override
//    public String getLargestGroup(Collection<Student> students) {
//        return maxGroupNameQuery(students,
//                Comparator.comparingInt((Group g) -> g.getStudents().size()).
//                        thenComparing(Comparator.comparing(Group::getName).reversed()));
//    }
//
//    @Override
//    public String getLargestGroupFirstName(Collection<Student> students) {
//        return maxGroupNameQuery(students,
//                Comparator.comparing((Group g) ->
//                        mappingStream(g.getStudents(), Student::getFirstName).distinct().count())
//                        .thenComparing(Comparator.comparing(Group::getName).reversed()));
//    }
//}