package ir.proprog.enrollassist.domain.studyRecord;

import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.GraduateLevel;
import ir.proprog.enrollassist.domain.course.Course;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class StudyRecordTest {

    private  StudyRecord studyRecord;

    @Parameters
    public static Collection<Object[]> data() throws ExceptionList {
        return Arrays.asList(new Object[][]{
                {"Undergraduate", "Undergraduate", 3, 9.9, false},
                {"Undergraduate", "Undergraduate", 3, 10, true},
                {"Undergraduate", "Undergraduate", 3, 15, true},
                {"Masters", "Masters", 3, 11.9, false},
                {"Masters", "Masters", 3, 12, true},
                {"Masters", "Masters", 3, 18, true},
                {"PHD", "PHD", 3, 13.9, false},
                {"PHD", "PHD", 3, 14, true},
                {"PHD", "PHD", 3, 16, true},
                {"Masters", "Undergraduate", 3, 10, true},
                {"Masters", "Undergraduate", 3, 8, false},
                {"Undergraduate", "Masters", 3, 13, true},
                {"Undergraduate", "Masters", 3, 10, true},
                {"Undergraduate", "Masters", 3, 9, false},
        });
    }

    @Parameter(0)
    public String courseGraduateLevel;
    @Parameter(1)
    public String studentGraduateLevel;
    @Parameter(2)
    public int credits;
    @Parameter(3)
    public double grade;
   @Parameter(4)
   public boolean expected;

    @Test
    public void isPassedTest() throws ExceptionList {
        this.studyRecord = new StudyRecord(
                "00001",
                new Course("0000000", "course", credits, courseGraduateLevel),
                grade);
        Assertions.assertEquals(expected, studyRecord.isPassed(GraduateLevel.valueOf(studentGraduateLevel)));
    }

}
