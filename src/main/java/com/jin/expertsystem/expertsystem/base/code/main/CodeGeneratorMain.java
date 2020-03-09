package com.jin.expertsystem.expertsystem.base.code.main;


import com.jin.expertsystem.expertsystem.base.code.service.CodeGeneratorManager;

/**
 * 代码生成器启动项
 *
 * @author GaoLiWei
 * @date 2017/09/20
 */
public class CodeGeneratorMain {



	private static final String[] TABLES = {
			"role"
	};

	/**
	 *  根据表名生成代码
	 * @param args
	 */
	public static void main(String[] args) {
		CodeGeneratorManager cgm = new CodeGeneratorManager();

		cgm.genCodeWithSimpleName(TABLES);

	}
}
