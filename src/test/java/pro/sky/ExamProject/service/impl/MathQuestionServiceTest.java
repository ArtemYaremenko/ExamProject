package pro.sky.ExamProject.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.ExamProject.exception.InvalidStringException;
import pro.sky.ExamProject.exception.JavaQuestionsSetIsEmptyException;
import pro.sky.ExamProject.model.Question;
import pro.sky.ExamProject.repository.impl.JavaQuestionRepository;
import pro.sky.ExamProject.repository.impl.MathQuestionRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static pro.sky.ExamProject.constans.QuestionsAndAnswers.*;
import static pro.sky.ExamProject.constans.QuestionsAndAnswers.ANSWER_4;
@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {

    @InjectMocks
    private MathQuestionService out;
    @Mock
    private MathQuestionRepository mathQuestionRepositoryMock;

    @ParameterizedTest
    @MethodSource("validArgumentsForTest")
    void shouldAddQuestion_WhenCorrectParam_ThenReturnAddedQuestion(String question, String answer) {
        Question expected = new Question(question, answer);
        Collection<Question> testCollection = Set.of(expected);

        when(mathQuestionRepositoryMock.add(expected)).thenReturn(expected);
        when(mathQuestionRepositoryMock.getAll()).thenReturn(testCollection);

        //test
        Question actual = out.add(question, answer);
        //check
        assertNotNull(actual);
        assertEquals(expected.getQuestion(), actual.getQuestion());
        assertEquals(expected.getAnswer(), actual.getAnswer());
        assertTrue(out.getAll().contains(expected));

        verify(mathQuestionRepositoryMock).add(expected);
        verify(mathQuestionRepositoryMock).getAll();
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
        Question expected = new Question(question, answer);
        Collection<Question> testCollection = new HashSet<>();

        when(mathQuestionRepositoryMock.remove(expected)).thenReturn(expected);
        when(mathQuestionRepositoryMock.getAll()).thenReturn(testCollection);

        //test
        Question actual = out.remove(question, answer);
        //check
        assertEquals(expected, actual);
        assertTrue(out.getAll().isEmpty());

        verify(mathQuestionRepositoryMock).remove(expected);
        verify(mathQuestionRepositoryMock).getAll();
    }

    @ParameterizedTest
    @MethodSource("badArgumentsForTest")
    void shouldRemoveQuestion_WhenBadParam_ThenTrowException(String question, String answer) {
        //test & check
        assertThrows(InvalidStringException.class,
                () -> out.add(question, answer));
    }

    @Test
    void shouldGetCollectionOfQuestions() {
        Set<Question> expected = Set.of(QUESTION_OBJECT_1, QUESTION_OBJECT_2, QUESTION_OBJECT_3, QUESTION_OBJECT_4, QUESTION_OBJECT_5);

        when(mathQuestionRepositoryMock.getAll()).thenReturn(expected);
        //test
        Collection<Question> actual = out.getAll();
        //check
        assertTrue(actual.containsAll(expected));

        verify(mathQuestionRepositoryMock).getAll();
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
        Collection<Question> testCollection = Set.of(QUESTION_OBJECT_1,
                QUESTION_OBJECT_2,
                QUESTION_OBJECT_3,
                QUESTION_OBJECT_4,
                QUESTION_OBJECT_5);

        when(mathQuestionRepositoryMock.getAll()).thenReturn(testCollection);

        //test
        Question actual = out.getRandomQuestion();
        //check
        assertTrue(out.getAll().contains(actual));

        verify(mathQuestionRepositoryMock, atLeastOnce()).getAll();
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