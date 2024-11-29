package com.sumit.ltim.web.quiz.repositories;

import com.sumit.ltim.web.quiz.entities.Question;
import com.sumit.ltim.web.quiz.entities.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findBySubjectAndTopic(String subject, String topic);
    List<Question> findByTests_Id(Long testId);  // Find questions by test ID
    List<Question> findByExam(String exam);

//    @Query("SELECT q FROM Question q WHERE " +
//            "(:subject IS NULL OR q.subject = :subject) AND " +
//            "(:topic IS NULL OR q.topic = :topic) AND " +
//            "(:exam IS NULL OR q.exam = :exam) AND " +
//            "(:questionType IS NULL OR q.questionType = :questionType)")
//    List<Question> findByFilters(
//            @Param("subject") String subject,
//            @Param("topic") String topic,
//            @Param("exam") String exam,
//            @Param("questionType") QuestionType questionType
//    );

    // Fetch random questions based on subject, topic, and exam
    @Query("SELECT q FROM Question q WHERE (:subject IS NULL OR q.subject = :subject) AND (:topic IS NULL OR q.topic = :topic) AND (:exam IS NULL OR q.exam = :exam) ORDER BY RANDOM() LIMIT :numberOfQuestions")
    List<Question> findRandomQuestions(@Param("subject") String subject, @Param("topic") String topic, @Param("exam") String exam, @Param("numberOfQuestions") int numberOfQuestions);

    // Dynamic filter for subject, topic, and exam
    @Query("SELECT q FROM Question q WHERE " +
            "(:subject IS NULL OR q.subject = :subject) AND " +
            "(:topic IS NULL OR q.topic = :topic) AND " +
            "(:exam IS NULL OR q.exam = :exam) AND " +
            "(:difficulty IS NULL OR q.difficulty = :difficulty)")
    List<Question> findByFilters(@Param("subject") String subject,
                                 @Param("topic") String topic,
                                 @Param("exam") String exam,
                                 @Param("difficulty") String difficulty);

    @Query("SELECT DISTINCT q.subject FROM Question q")
    List<String> findDistinctSubjects();

    @Query("SELECT DISTINCT q.topic FROM Question q ")
    List<String> findDistinctTopics();

    @Query("SELECT DISTINCT q.exam FROM Question q")
    List<String> findDistinctExams();

    // Query to fetch questions by subject
    List<Question> findBySubject(String subject);
}
