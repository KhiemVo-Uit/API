package com.example.demoDTO2.map;





import com.example.demoDTO2.dto.StudentDTO;
import com.example.demoDTO2.entity.Student;
import com.example.demoDTO2.entity.Subject;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;



@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {

    @Mapping(source = "classroom.name", target = "classroomName")
    @Mapping(source = "classroom.id", target = "classroomId")
    @Mapping(source = "subjects", target = "subjectIds", qualifiedByName = "subjectsToIds")
    StudentDTO toDto(Student student);

    @InheritInverseConfiguration
    @Mapping(source = "subjectIds", target = "subjects", qualifiedByName = "idsToSubjects")
    @Mapping(target = "id", ignore = true)  // Có thể bỏ nếu bạn muốn cập nhật
    Student toEntity(StudentDTO dto);

    // --- Custom mapping methods ---

    @Named("subjectsToIds")
    default Set<Long> mapSubjectsToSubjectIds(Set<Subject> subjects) {
        if (subjects == null) return null;
        return subjects.stream()
                .map(Subject::getId)
                .collect(Collectors.toSet());
    }

    @Named("idsToSubjects")
    default Set<Subject> mapSubjectIdsToSubjects(Set<Long> subjectIds) {
        if (subjectIds == null) return null;
        return subjectIds.stream()
                .map(id -> {
                    Subject subject = new Subject();
                    subject.setId(id);
                    return subject;
                })
                .collect(Collectors.toSet());
    }
}
