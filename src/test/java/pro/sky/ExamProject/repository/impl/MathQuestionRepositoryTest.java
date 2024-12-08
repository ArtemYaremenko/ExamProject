package pro.sky.ExamProject.repository.impl;

import org.junit.jupiter.api.Test;
import pro.sky.ExamProject.exception.NotFoundQuestionException;
import pro.sky.ExamProject.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.ExamProject.constans.QuestionsAndAnswers.*;

class MathQuestionRepositoryTest {

    private final Set<Question> testMathQuestions = new HashSet<>();
    private final MathQuestionRepository out = new MathQuestionRepository(testMathQuestions);

    @Test
    void shouldAddMathQuestion_ThanReturnJavaQuestion() {
        Question expected = QUESTION_OBJECT_3;

        //test
        Question actual = out.add(QUESTION_OBJECT_3);
        //check
        assertEquals(expected, actual);
        assertTrue(out.getAll().contains(expected));
    }

    @Test
    void shouldReMathJavaQuestion_ThanReturnJavaQuestion() {
        Question expected = out.add(QUESTION_OBJECT_1);

        //test
        Question actual = out.remove(QUESTION_OBJECT_1);
        //check
        assertEquals(expected, actual);
        assertTrue(out.getAll().isEmpty());
    }

    @Test
    void shouldRemoveMathQuestion_WhenQuestionDoesNotExist_ThanThrowException() {
        out.add(QUESTION_OBJECT_2);
        out.add((QUESTION_OBJECT_4));

        //check & test
        assertThrows(NotFoundQuestionException.class,
                () -> out.remove(QUESTION_OBJECT_3));
    }

    @Test
    void shouldGetAllMathQuestions() {
        out.add(QUESTION_OBJECT_1);
        out.add(QUESTION_OBJECT_2);
        out.add(QUESTION_OBJECT_3);
        out.add(QUESTION_OBJECT_4);
        out.add(QUESTION_OBJECT_5);
        Collection<Question> expected = Set.of(QUESTION_OBJECT_1,
                QUESTION_OBJECT_2,
                QUESTION_OBJECT_3,
                QUESTION_OBJECT_4,
                QUESTION_OBJECT_5);

        //test
        Collection<Question> actual = out.getAll();
        //check
        assertTrue(actual.containsAll(expected));
    }

    @Test
    void shouldGetAllMathQuestions_WhenCollectionIsEmpty_ThanReturnEmptyCollection() {
        Collection<Question> expected = new HashSet<>();

        //test
        Collection<Question> actual = out.getAll();
        //check
        assertIterableEquals(expected, actual);
        assertTrue(actual.isEmpty());
    }
}