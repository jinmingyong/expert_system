package com.jin.expertsystem.expertsystem.base.code.controller;

import com.jin.expertsystem.expertsystem.base.code.service.CodeGeneratorManager;
import com.jin.expertsystem.expertsystem.base.code.util.ConnectionUtil;
import com.jin.expertsystem.expertsystem.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/4/18
 */
@ApiIgnore
@RestController
@RequestMapping("/codeGenerator/")
public class CodeGeneratorController {

    @Autowired
    private CodeGeneratorManager codeGeneratorManager;
    @Autowired
    private ConnectionUtil connectionUtil;




    @GetMapping("getTables")
    public Result getTables(){
        List<String> tableNameByCon = connectionUtil.getTableNameByCon();
        return Result.result(tableNameByCon);
    }

    @PostMapping("codeGenerators")
    public String codeGenerators(@RequestBody String[] tables){
        codeGeneratorManager.genCodeWithSimpleName(tables);
        return "生成成功";
    }

}
