package com.sumit.ltim.web.quiz.services.impl;

import com.sumit.ltim.web.quiz.entities.Option;
import com.sumit.ltim.web.quiz.repositories.OptionRepository;
import com.sumit.ltim.web.quiz.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public Option addOption(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    @Override
    public Option updateOption(Long id, Option option) {
        Option existingOption = optionRepository.findById(id).orElseThrow(() -> new RuntimeException("Option not found"));
        existingOption.setText(option.getText());
        existingOption.setIsCorrect(option.getIsCorrect());
        return optionRepository.save(existingOption);
    }

    @Override
    public void deleteOption(Long id) {
        optionRepository.deleteById(id);

    }
}
