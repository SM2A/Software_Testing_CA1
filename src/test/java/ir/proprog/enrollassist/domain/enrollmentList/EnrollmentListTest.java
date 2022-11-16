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
