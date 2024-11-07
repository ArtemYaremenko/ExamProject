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

    @Test
    void shouldReturnCollectionOfQuestions_WhenCorrectParam() {
        List<Question> questionsTest = List.of(QUESTION_OBJECT_1,
                QUESTION_OBJECT_2,
                QUESTION_OBJECT_3,
                QUESTION_OBJECT_4,
                QUESTION_OBJECT_5);
        Question expected = questionsTest.get(RANDOM.nextInt(questionsTest.size()));

        when(javaQuestionServiceMock.getAll()).thenReturn(questionsTest);
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(expected);
        //test
        Collection<Question> actual = out.getQuestions(1);
        //check
        System.out.println(actual);
        assertTrue(actual.contains(expected));
        verify(javaQuestionServiceMock).getAll();
        verify(javaQuestionServiceMock).getRandomQuestion();
    }

    @Test
    void shouldReturnCollectionOfQuestions_WhenParamLessThanZero_ThenTrowException() {
        //test & check
        assertThrows(BadQuestionAmountException.class,
                () -> out.getQuestions(-1));
    }

    @Test
    void shouldReturnCollectionOfQuestions_WhenParamMoreThanCollectionSize_ThenTrowException() {
        List<Question> questionsTest = List.of(QUESTION_OBJECT_1,
                QUESTION_OBJECT_2,
                QUESTION_OBJECT_3,
                QUESTION_OBJECT_4,
                QUESTION_OBJECT_5);
        when(javaQuestionServiceMock.getAll()).thenReturn(questionsTest);
        //test & check
        assertThrows(IllegalQuestionAmountException.class,
                () -> out.getQuestions(questionsTest.size() + 1));
        verify(javaQuestionServiceMock, only()).getAll();
    }
}
