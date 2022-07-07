package site.bzyl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.bzyl.domain.ResponseResult;
import site.bzyl.domain.entity.Category;
import site.bzyl.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/getCategoryList")
    public ResponseResult<Category> getCategoryList() {
        return categoryService.getCategoryList();
    }

}
