package com.alick.plugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;

import javax.inject.Inject;

/**
 * @author 崔兴旺
 * @description
 * @date 2022/2/2 17:38
 */
public class JiaGuTask extends DefaultTask {
    private File     outputFile;
    private JiaGuExt jiaGuExt;

    @Inject
    public JiaGuTask(File outputFile, JiaGuExt jiaGuExt) {
        setGroup("jiagu");
        this.outputFile = outputFile;
        this.jiaGuExt = jiaGuExt;
    }

    @TaskAction
    public void abc() {
        System.out.println("执行JiaGuTask任务类中的abc方法");
        System.out.println("输出原包路径:" + outputFile);
        System.out.println("加固账号:" + jiaGuExt.getUsername() + ",密码:" + jiaGuExt.getPwd());
        System.out.println("可以通过此apk文件进行加固,并上传到公司的包管理平台");
    }


}