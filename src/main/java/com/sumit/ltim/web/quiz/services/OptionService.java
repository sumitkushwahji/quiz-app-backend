package com.sumit.ltim.web.quiz.services;

import com.sumit.ltim.web.quiz.entities.Option;

import java.util.List;

public interface OptionService {
    Option addOption(Option option);
    List<Option> getAllOptions();
    Option updateOption(Long id, Option option);
    void deleteOption(Long id);
}
