package com.hk.live.controller;

import com.hk.commons.JsonResult;
import com.hk.commons.util.ArrayUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("course")
public class CourseController {

    @PostMapping()
    public JsonResult<?> list() {
        return JsonResult.success(ArrayUtils.asArrayList(new Course(1L, "直播课程1"),
                new Course(2L, "直播课程2")));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Course {

        private Long id;

        private String name;
    }
}
