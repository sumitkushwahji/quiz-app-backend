package com.sumit.ltim.web.quiz.repositories;

import com.sumit.ltim.web.quiz.entities.Question;
import com.sumit.ltim.web.quiz.entities.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findBySubjectAndTopic(String subject, String topic);
    List<Question> findByExam(String exam);

    @Query("SELECT q FROM Question q WHERE " +
            "(:subject IS NULL OR q.subject = :subject) AND " +
            "(:topic IS NULL OR q.topic = :topic) AND " +
            "(:exam IS NULL OR q.exam = :exam) AND " +
            "(:questionType IS NULL OR q.questionType = :questionType)")
    List<Question> findByFilters(
            @Param("subject") String subject,
            @Param("topic") String topic,
            @Param("exam") String exam,
            @Param("questionType") QuestionType questionType
    );

    @Query("SELECT DISTINCT q.subject FROM Question q")
    List<String> findDistinctSubjects();

    @Query("SELECT DISTINCT q.topic FROM Question q WHERE q.subject = ?1")
    List<String> findDistinctTopicsBySubject(String subject);

    @Query("SELECT DISTINCT q.exam FROM Question q")
    List<String> findDistinctExams();
}
