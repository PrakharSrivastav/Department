package com.example.service;

import brave.ScopedSpan;
import brave.Span;
import brave.Tracer;
import com.example.model.Department;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public final class DepartmentServiceImpl implements DepartmentService {

    private static Faker f = new Faker();
    @Autowired
    private Tracer tracer;

    @Override
    public Department get(String PersonID) {
        Span s = this.tracer.currentSpan();
        try {
            return new Department(f.job().field(), UUID.randomUUID().toString());
        } catch (Exception e) {
            s.error(e);
            e.printStackTrace();
            return null;
        } finally {
            s.finish();
        }

    }
}
