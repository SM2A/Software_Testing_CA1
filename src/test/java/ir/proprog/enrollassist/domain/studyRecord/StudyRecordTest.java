package ir.proprog.enrollassist.domain.studyRecord;

import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.GraduateLevel;
import ir.proprog.enrollassist.domain.course.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class StudyRecordTest {

    StudyRecord studyRecord;


    private static Stream<Arguments> data() {

        //Args: courseGraduateLevel, studentGraduateLevel, credits, grade, expectedResult
        return Stream.of(
                Arguments.of("Undergraduate", "Undergraduate", 3, 9.9, false),
                Arguments.of("Undergraduate", "Undergraduate", 3, 10.0, true),
                Arguments.of("Undergraduate", "Undergraduate", 3, 15.0, true),
                Arguments.of("Masters", "Masters", 3, 11.9, false),
                Arguments.of("Masters", "Masters", 3, 12.0, true),
                Arguments.of("Masters", "Masters", 3, 18.0, true),
                Arguments.of("PHD", "PHD", 3, 13.9, false),
                Arguments.of("PHD", "PHD", 3, 14.0, true),
                Arguments.of("PHD", "PHD", 3, 16.0, true),
                Arguments.of("Masters", "Undergraduate", 3, 10.0, true),
                Arguments.of("Masters", "Undergraduate", 3, 8.0, false),
                Arguments.of("Undergraduate", "Masters", 3, 13.0, true),
                Arguments.of("Undergraduate", "Masters", 3, 10.0, true),
                Arguments.of("Undergraduate", "Masters", 3, 9.0, false)
                );
    }


    @ParameterizedTest
    @MethodSource("data")
    public void isPassedTest(ArgumentsAccessor args) throws ExceptionList {
        this.studyRecord = new StudyRecord(
                "00001",
                new Course("0000000", "course", args.getInteger(2), args.getString(0)),
                args.getDouble(3)
        );
        Assertions.assertEquals(
                args.getBoolean(4),
                studyRecord.isPassed(GraduateLevel.valueOf(args.getString(1)))
        );
    }

}

