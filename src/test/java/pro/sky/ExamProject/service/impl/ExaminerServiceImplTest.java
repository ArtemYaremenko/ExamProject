package pro.sky.ExamProject.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.ExamProject.exception.BadQuestionAmountException;
import pro.sky.ExamProject.exception.IllegalQuestionAmountException;
import pro.sky.ExamProject.model.Question;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static pro.sky.ExamProject.constans.QuestionsAndAnswers.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    private final Random RANDOM = new Random();

    @InjectMocks
    private ExaminerServiceImpl out;

    @Mock
    private JavaQuestionService javaQuestionServiceMock;

    @Mock
    private MathQuestionService mathQuestionServiceMock;

    @Test
    void shouldReturnCollectionOfJavaQuestions_WhenCorrectParam() {
        List<Question> questionsTest = List.of(QUESTION_OBJECT_1,
                QUESTION_OBJECT_2,
                QUESTION_OBJECT_3,
                QUESTION_OBJECT_4,
                QUESTION_OBJECT_5);
        Question expected = questionsTest.get(RANDOM.nextInt(questionsTest.size()));

        when(javaQuestionServiceMock.getAll()).thenReturn(questionsTest);
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(expected);
        //test
        Collection<Question> actual = out.getJavaQuestions(1);
        //check
        System.out.println(actual);
        assertTrue(actual.contains(expected));
        verify(javaQuestionServiceMock).getAll();
        verify(javaQuestionServiceMock).getRandomQuestion();
    }

    @Test
    void shouldReturnCollectionOfMathQuestions_WhenCorrectParam() {
        List<Question> questionsTest = List.of(QUESTION_OBJECT_1,
                QUESTION_OBJECT_2,
                QUESTION_OBJECT_3,
                QUESTION_OBJECT_4,
                QUESTION_OBJECT_5);
        Question expected = questionsTest.get(RANDOM.nextInt(questionsTest.size()));

        when(mathQuestionServiceMock.getAll()).thenReturn(questionsTest);
        when(mathQuestionServiceMock.getRandomQuestion()).thenReturn(expected);
        //test
        Collection<Question> actual = out.getMathQuestions(1);
        //check
        System.out.println(actual);
        assertTrue(actual.contains(expected));
        verify(mathQuestionServiceMock).getAll();
        verify(mathQuestionServiceMock).getRandomQuestion();
    }

    @Test
    void shouldReturnCollectionOfJavaQuestions_WhenParamLessThanZero_ThenTrowException() {
        //test & check
        assertThrows(BadQuestionAmountException.class,
                () -> out.getJavaQuestions(-1));
    }

    @Test
    void shouldReturnCollectionOfMathQuestions_WhenParamLessThanZero_ThenTrowException() {
        //test & check
        assertThrows(BadQuestionAmountException.class,
                () -> out.getMathQuestions(-1));
    }

    @Test
    void shouldReturnCollectionOfJavaQuestions_WhenParamMoreThanCollectionSize_ThenTrowException() {
        List<Question> questionsTest = List.of(QUESTION_OBJECT_1,
                QUESTION_OBJECT_2,
                QUESTION_OBJECT_3,
                QUESTION_OBJECT_4,
                QUESTION_OBJECT_5);
        when(javaQuestionServiceMock.getAll()).thenReturn(questionsTest);
        //test & check
        assertThrows(IllegalQuestionAmountException.class,
                () -> out.getJavaQuestions(questionsTest.size() + 1));
        verify(javaQuestionServiceMock, only()).getAll();
    }

    @Test
    void shouldReturnCollectionOfMathQuestions_WhenParamMoreThanCollectionSize_ThenTrowException() {
        List<Question> questionsTest = List.of(QUESTION_OBJECT_1,
                QUESTION_OBJECT_2,
                QUESTION_OBJECT_3,
                QUESTION_OBJECT_4,
                QUESTION_OBJECT_5);
        when(mathQuestionServiceMock.getAll()).thenReturn(questionsTest);
        //test & check
        assertThrows(IllegalQuestionAmountException.class,
                () -> out.getMathQuestions(questionsTest.size() + 1));
        verify(mathQuestionServiceMock, only()).getAll();
    }
}
