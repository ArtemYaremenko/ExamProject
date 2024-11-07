package pro.sky.ExamProject.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.ExamProject.exception.InvalidStringException;
import pro.sky.ExamProject.exception.JavaQuestionsSetIsEmptyException;
import pro.sky.ExamProject.exception.NotFoundQuestionException;
import pro.sky.ExamProject.model.Question;
import pro.sky.ExamProject.service.QuestionService;


import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.ExamProject.constans.QuestionsAndAnswers.*;

class JavaQuestionServiceTest {

    private final QuestionService out = new JavaQuestionService();

    @ParameterizedTest
    @MethodSource("validArgumentsForTest")
    void shouldAddQuestion_WhenCorrectParam_ThenReturnAddedQuestion(String question, String answer) {
        Question expected = new Question(question, answer);
        //test
        Question actual = out.add(question, answer);
        //check
        assertNotNull(actual);
        assertEquals(expected.getQuestion(), actual.getQuestion());
        assertEquals(expected.getAnswer(), actual.getAnswer());
        assertTrue(out.getAll().contains(expected));
    }

    @ParameterizedTest
    @MethodSource("badArgumentsForTest")
    void shouldAddQuestion_WhenBadParam_ThenTrowException(String question, String answer) {
        //test & check
        assertThrows(InvalidStringException.class,
                () -> out.add(question, answer));
    }


    @ParameterizedTest
    @MethodSource("validArgumentsForTest")
    void shouldRemoveQuestion_WhenCorrectParam_ThenReturnRemovedQuestion(String question, String answer) {
        Question expected = out.add(question, answer);
        //test
        Question actual = out.remove(question, answer);
        //check
        assertEquals(expected, actual);
        assertTrue(out.getAll().isEmpty());
    }

    @ParameterizedTest
    @MethodSource("badArgumentsForTest")
    void shouldRemoveQuestion_WhenBadParam_ThenTrowException(String question, String answer) {
        //test & check
        assertThrows(InvalidStringException.class,
                () -> out.add(question, answer));
    }

    @Test
    void shouldRemoveQuestion_WhenQuestionIsNotExist_ThenTrowException() {
        out.add(QUESTION_OBJECT_1);
        out.add(QUESTION_OBJECT_2);
        out.add(QUESTION_OBJECT_3);
        out.add(QUESTION_OBJECT_4);
        //test & check
        assertThrows(NotFoundQuestionException.class,
                () -> out.remove(QUESTION_OBJECT_5));
    }

    @Test
    void shouldGetCollectionOfQuestions() {
        Set<Question> expected = Set.of(QUESTION_OBJECT_1, QUESTION_OBJECT_2, QUESTION_OBJECT_3, QUESTION_OBJECT_4, QUESTION_OBJECT_5);
        out.add(QUESTION_OBJECT_1);
        out.add(QUESTION_OBJECT_2);
        out.add(QUESTION_OBJECT_3);
        out.add(QUESTION_OBJECT_4);
        out.add(QUESTION_OBJECT_5);
        //test
        Collection<Question> actual = out.getAll();
        //check
        assertTrue(actual.containsAll(expected));
    }

    @Test
    void shouldGetEmptyCollection_WhenCollectionIsEmpty() {
        //test
        Collection<Question> actual = out.getAll();
        //check
        assertTrue(actual.isEmpty());
    }

    @Test
    void shouldReturnRandomQuestion() {
        out.add(QUESTION_OBJECT_1);
        out.add(QUESTION_OBJECT_2);
        out.add(QUESTION_OBJECT_3);
        out.add(QUESTION_OBJECT_4);
        out.add(QUESTION_OBJECT_5);
        //test
        Question actual = out.getRandomQuestion();
        //check
        assertTrue(out.getAll().contains(actual));
    }

    @Test
    void shouldReturnRandomQuestion_WhenQuestionSetIsEmpty_ThenThrowException() {
        //test $ check
        assertThrows(JavaQuestionsSetIsEmptyException.class,
                out::getRandomQuestion);
    }

    private static Stream<Arguments> validArgumentsForTest() {
        return Stream.of(Arguments.of(QUESTION_1, ANSWER_1),
                Arguments.of(QUESTION_2, ANSWER_2),
                Arguments.of(QUESTION_3, ANSWER_3),
                Arguments.of(QUESTION_4, ANSWER_4),
                Arguments.of(QUESTION_5, ANSWER_5));
    }

    private static Stream<Arguments> badArgumentsForTest() {
        return Stream.of(Arguments.of(QUESTION_1, ""),
                Arguments.of("", ANSWER_2),
                Arguments.of("", ""),
                Arguments.of(QUESTION_2, " "),
                Arguments.of(" ", ANSWER_1),
                Arguments.of(" ", " "),
                Arguments.of(QUESTION_4, null),
                Arguments.of(null, ANSWER_4),
                Arguments.of(null, null));
    }

}