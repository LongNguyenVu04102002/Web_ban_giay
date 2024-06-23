package com.example.datn.service;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IService<X> {

    List<X> getAll();

    Optional<X> getOne(Long id);

    X addOrUpdate(X x);

    void remove(Long id);

    Page<X> pagination(Integer pageNo, Integer size);
}
