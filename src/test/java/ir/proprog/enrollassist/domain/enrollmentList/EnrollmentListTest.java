package ir.proprog.enrollassist.domain.enrollmentList;

import ir.proprog.enrollassist.domain.EnrollmentRules.EnrollmentRuleViolation;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EnrollmentListTest {

//    @BeforeAll
//    public void initialize() throws Exception {
//        Program program = new Program(
//                new Major("1", "CE", "Electrical and Computer Engineering"),
//                "Undergraduate",
//                1,
//                142,
//                "Major");
//
//        Course ICSP = new Course("0000000", "ICSP", 3, "Undergraduate");
//        Course AP = new Course("0000001", "Advanced Programming", 3, "Undergraduate");
//        Course DM = new Course("0000002", "Discrete Mathematics", 3, "Undergraduate");
//        Course DS = new Course("0000003", "Data Structure", 3, "Undergraduate");
//        Course DA = new Course("0000004", "Algorithm Design", 3, "Undergraduate");
//        Course OS = new Course("0000005", "Operating System", 3, "Undergraduate");
//        Course math1 = new Course("0000006", "Mathematics 1", 3, "Undergraduate");
//        Course physics1 = new Course("0000007", "Physics 1", 3, "Undergraduate");
//        Course CA = new Course("0000008", "Computer Architecture", 3, "Undergraduate");
//        Course AI = new Course("0000009", "Artificial Intelligence", 3, "Undergraduate");
//
//        Section sec_ICSP = new Section(ICSP, "0", new ExamTime("2021-06-20T08:00", "2021-06-20T10:00"), Collections.singleton(new PresentationSchedule("Sunday", "12:00", "14:00")));
//        Section sec_AP = new Section(AP, "1", new ExamTime("2021-06-21T08:00", "2021-06-21T10:00"), Collections.singleton(new PresentationSchedule("Sunday", "08:00", "10:00")));
//        Section sec_DM = new Section(DM, "2", new ExamTime("2021-06-22T08:00", "2021-06-22T10:00"), Collections.singleton(new PresentationSchedule("Sunday", "07:00", "08:00")));
//        Section sec_DS = new Section(DS, "3", new ExamTime("2021-06-21T14:00", "2021-06-21T18:00"), Collections.singleton(new PresentationSchedule("Monday", "08:00", "10:00")));
//        Section sec_DA = new Section(DA, "4", new ExamTime("2021-06-23T08:00", "2021-06-23T11:00"), Collections.singleton(new PresentationSchedule("Saturday", "08:00", "10:00")));
//        Section sec_OS = new Section(OS, "5", new ExamTime("2021-06-24T08:00", "2021-06-24T11:00"), Collections.singleton(new PresentationSchedule("Saturday", "08:00", "10:00")));
//        Section sec_math1 = new Section(math1, "6", new ExamTime("2021-06-25T14:00", "2021-06-25T18:00"), Collections.singleton(new PresentationSchedule("Tuesday", "08:00", "10:00")));
//        Section sec_physics = new Section(physics1, "7", new ExamTime("2021-06-30T08:00", "2021-06-30T11:00"), Collections.singleton(new PresentationSchedule("Saturday", "11:00", "13:00")));
//        Section sec_CA = new Section(CA, "8", new ExamTime("2021-06-30T08:00", "2021-06-30T11:00"), Collections.singleton(new PresentationSchedule("Saturday", "15:00", "17:00")));
//        Set<PresentationSchedule> scheduleSet = new HashSet<>();
//        scheduleSet.add(new PresentationSchedule("Saturday", "09:00", "10:30"));
//        scheduleSet.add(new PresentationSchedule("Monday", "09:00", "10:30"));
//        Section sec_AI = new Section(AI, "9", new ExamTime("2021-07-01T10:00", "2021-07-01T13:00"), scheduleSet);
//
//
//        AP.withPre(ICSP);
//        DM.withPre(ICSP);
//        DS.withPre(AP, DS);
//        DA.withPre(DS);
//        OS.withPre(CA, DS);
//        AI.withPre(AP);
//
//        program.addCourse(ICSP, AP, DM, DS, DA, OS, math1, physics1, CA, AI);
//
//        Student student = new Student("000000000", "Undergraduate");
//        student.addProgram(program);
//
//        List<StudyRecord> studyRecords = new ArrayList<>();
//        studyRecords.add(new StudyRecord("00001", ICSP, 17.5));
//        studyRecords.add(new StudyRecord("00001", math1, 12.5));
//        studyRecords.add(new StudyRecord("00002", AP, 19.5));
//        studyRecords.add(new StudyRecord("00002", DM, 16.5));
//
//        for (StudyRecord c : studyRecords)
//            student.setGrade(c.getTerm(), c.getCourse(), c.getGrade().getGrade());
//
//
//        EnrollmentList enrollmentList_noProblem = new EnrollmentList("EnrollmentListTest", student);
//
//
//
//    }

    @Spy
    private EnrollmentList enrollmentListSpy;

    @Test
    public void checkEnrollmentRulesTest() {

        // given
        doReturn(Lists.emptyList()).when(enrollmentListSpy).checkHasPassedAllPrerequisites();
        doReturn(Lists.emptyList()).when(enrollmentListSpy).checkHasNotAlreadyPassedCourses();
        doReturn(Lists.emptyList()).when(enrollmentListSpy).checkNoCourseHasRequestedTwice();
        doReturn(Lists.emptyList()).when(enrollmentListSpy).checkValidGPALimit();
        doReturn(Lists.emptyList()).when(enrollmentListSpy).checkExamTimeConflicts();
        doReturn(Lists.emptyList()).when(enrollmentListSpy).checkSectionScheduleConflicts();
        // when
//        List<EnrollmentRuleViolation> violations = verify(enrollmentListSpy).checkEnrollmentRules();
        List<EnrollmentRuleViolation> violations = enrollmentListSpy.checkEnrollmentRules();
        //then
        Assertions.assertThat(violations.isEmpty()).isTrue();
    }
}
