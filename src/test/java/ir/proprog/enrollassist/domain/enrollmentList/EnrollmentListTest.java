package ir.proprog.enrollassist.domain.enrollmentList;

import ir.proprog.enrollassist.domain.EnrollmentRules.*;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.domain.section.Section;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class EnrollmentListTest {

    @Spy
    private EnrollmentList enrollmentListSpy;
    @Mock
    public static Course requestedCourse;
    @Mock
    public static Course prerequisite;
    @Mock
    public static MaxCreditsLimitExceeded maxCreditsLimitExceeded;
    @Mock
    public static MinCreditsRequiredNotMet minCreditsRequiredNotMet;
    @Mock
    public static Section section1;
    @Mock
    public static Section section2;


    public static Stream<Arguments> data () {
        List<EnrollmentRuleViolation> noViolation = Lists.emptyList();
        List<EnrollmentRuleViolation> prerequisiteNotTakenViolations = Lists.list(new PrerequisiteNotTaken(requestedCourse, prerequisite));
        List<EnrollmentRuleViolation> requestedCourseAlreadyPassedViolations = Lists.list(new RequestedCourseAlreadyPassed(requestedCourse));
        List<EnrollmentRuleViolation> courseRequestedTwiceViolations = Lists.list(new CourseRequestedTwice(requestedCourse));
        List<EnrollmentRuleViolation> maxCreditsLimitExceededViolations = Lists.list(maxCreditsLimitExceeded);
        List<EnrollmentRuleViolation> minCreditsRequiredNotMetViolations = Lists.list(minCreditsRequiredNotMet);
        List<EnrollmentRuleViolation>  examTimeCollisionViolations = Lists.list(new ExamTimeCollision(section1, section2));
        List<EnrollmentRuleViolation> sectionScheduleViolations = Lists.list(new ConflictOfClassSchedule(section1, section2));

        // args: PrerequisiteNotTaken, RequestedCourseAlreadyPassed, CourseRequestedTwice, GPALimit, ExamTimeCollision, ScheduleConflicts, expectedViolations
        return Stream.of(
                Arguments.of(noViolation, noViolation, noViolation, noViolation, noViolation, noViolation, noViolation),
                Arguments.of(prerequisiteNotTakenViolations, noViolation, noViolation, noViolation, noViolation, noViolation, prerequisiteNotTakenViolations),
                Arguments.of(noViolation, requestedCourseAlreadyPassedViolations, noViolation, noViolation, noViolation, noViolation, requestedCourseAlreadyPassedViolations),
                Arguments.of(noViolation, noViolation,courseRequestedTwiceViolations, noViolation, noViolation, noViolation, courseRequestedTwiceViolations),
                Arguments.of(noViolation, noViolation, noViolation, maxCreditsLimitExceededViolations, noViolation, noViolation, maxCreditsLimitExceededViolations),
                Arguments.of(noViolation, noViolation, noViolation, minCreditsRequiredNotMetViolations, noViolation, noViolation, minCreditsRequiredNotMetViolations),
                Arguments.of(noViolation, noViolation, noViolation, noViolation, examTimeCollisionViolations, noViolation, examTimeCollisionViolations),
                Arguments.of(noViolation, noViolation, noViolation, noViolation, noViolation, sectionScheduleViolations, sectionScheduleViolations),
                Arguments.of(prerequisiteNotTakenViolations, requestedCourseAlreadyPassedViolations, noViolation, noViolation, noViolation, noViolation,
                        Stream.concat(prerequisiteNotTakenViolations.stream(), requestedCourseAlreadyPassedViolations.stream()).toList()),
                Arguments.of(noViolation, requestedCourseAlreadyPassedViolations, courseRequestedTwiceViolations, noViolation, noViolation, noViolation,
                        Stream.concat(requestedCourseAlreadyPassedViolations.stream(), courseRequestedTwiceViolations.stream()).toList()),
                Arguments.of(noViolation, noViolation, noViolation, minCreditsRequiredNotMetViolations, noViolation, examTimeCollisionViolations,
                        Stream.concat(minCreditsRequiredNotMetViolations.stream(), examTimeCollisionViolations.stream()).toList())
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void checkEnrollmentRulesTest(ArgumentsAccessor args) {

        // given
        doReturn(args.get(0)).when(enrollmentListSpy).checkHasPassedAllPrerequisites();
        doReturn(args.get(1)).when(enrollmentListSpy).checkHasNotAlreadyPassedCourses();
        doReturn(args.get(2)).when(enrollmentListSpy).checkNoCourseHasRequestedTwice();
        doReturn(args.get(3)).when(enrollmentListSpy).checkValidGPALimit();
        doReturn(args.get(4)).when(enrollmentListSpy).checkExamTimeConflicts();
        doReturn(args.get(5)).when(enrollmentListSpy).checkSectionScheduleConflicts();

        // when
        List<EnrollmentRuleViolation> violations = enrollmentListSpy.checkEnrollmentRules();

        //then
        Assertions.assertEquals(violations, args.get(6));
    }

}
