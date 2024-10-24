package com.sumit.ltim.web.quiz.controllers;

import com.sumit.ltim.web.quiz.entities.Option;
import com.sumit.ltim.web.quiz.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class OptionController {
    @Autowired
    private OptionService optionService;

    @PostMapping
    public Option addOption(@RequestBody Option option) {
        return optionService.addOption(option);
    }

    @GetMapping
    public List<Option> getAllOptions() {
        return optionService.getAllOptions();
    }

    @PutMapping("/{id}")
    public Option updateOption(@PathVariable Long id, @RequestBody Option option) {
        return optionService.updateOption(id, option);
    }

    @DeleteMapping("/{id}")
    public void deleteOption(@PathVariable Long id) {
        optionService.deleteOption(id);
    }
}
