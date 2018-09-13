package com.flower.mine.service;

import com.flower.mine.bean.Parameter;
import com.flower.mine.param.ParameterPostParam;
import com.flower.mine.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParameterService {

    @Autowired
    private ParameterRepository parameterRepository;

    public Page<Parameter> all() {
        return parameterRepository.findAll(PageRequest.of(0, Integer.MAX_VALUE));
    }

    public void addOrUpdate(ParameterPostParam param) {
        Parameter parameter = new Parameter();
        parameter.setName(param.getName());
        parameter.setValue(param.getValue());
        parameterRepository.save(parameter);
    }

    public void delete(String name) {
        parameterRepository.deleteById(name);
    }

    public Optional<Parameter> queryByName(String name) {
        return parameterRepository.findById(name);
    }
}
