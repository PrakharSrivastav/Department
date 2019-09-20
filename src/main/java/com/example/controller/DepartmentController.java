package com.example.controller;

import brave.ScopedSpan;
import brave.Span;
import brave.Tracer;
import com.example.model.Address;
import com.example.model.Department;
import com.example.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "department", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

    private static Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Tracer tracer;

    @GetMapping("{id}")
    public Department get(@PathVariable("id") final String id) {
        final Span s = this.tracer.currentSpan();
        try {
            return this.departmentService.get(id);
        } catch (Exception e) {
            s.error(e);
            e.printStackTrace();
            return null;
        } finally {
            s.finish();
        }
    }


    @GetMapping("chained")
    public Department getChained() {
        final Span s = this.tracer.currentSpan();
        try {
            this.restTemplate.getForObject("http://localhost:8081/address/asdasd", Address.class);
            return this.departmentService.get("dummyId");
        } catch (Exception e) {
            s.error(e);
            e.printStackTrace();
            return null;
        } finally {
            s.finish();
        }
    }

}
