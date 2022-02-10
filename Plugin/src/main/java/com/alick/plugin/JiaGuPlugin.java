package com.alick.plugin;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.api.ApplicationVariant;
import com.android.build.gradle.api.BaseVariantOutput;
import com.android.builder.model.BuildType;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.plugins.ExtraPropertiesExtension;

import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * @author 崔兴旺
 * @description
 * @date 2022/1/23 19:32
 */
public class JiaGuPlugin implements Plugin<Project> {

    @Override
    public void apply(Project target) {
        final JiaGuExt jiaGuExt = target.getExtensions().create("jiagu", JiaGuExt.class);
        System.out.println("加载插件");

        target.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(final Project project) {
                System.out.println("JiaGuExt数据:" + jiaGuExt.toString());
                ExtensionContainer             extensions      = project.getExtensions();
                ExtraPropertiesExtension       extraProperties = extensions.getExtraProperties();
                Map<String, Object>            properties      = (Map<String, Object>) extraProperties.getProperties();
                Set<Map.Entry<String, Object>> entries         = properties.entrySet();
                for (Map.Entry<String, Object> entry : entries) {
                    System.out.println("扩展属性," + entry.getKey() + ":" + entry.getValue());
                }

                AppExtension appExtension      = (AppExtension) extensions.getByName("android");
                String       compileSdkVersion = appExtension.getCompileSdkVersion();
                System.out.println("compileSdkVersion:" + compileSdkVersion);

                appExtension.getApplicationVariants().all(new Action<ApplicationVariant>() {
                    @Override
                    public void execute(ApplicationVariant applicationVariant) {
                        BuildType buildType = applicationVariant.getBuildType();
                        System.out.println("buildType.name:" + buildType.getName());

                        applicationVariant.getOutputs().all(new Action<BaseVariantOutput>() {
                            @Override
                            public void execute(BaseVariantOutput baseVariantOutput) {
                                File outputFile = baseVariantOutput.getOutputFile();
                                System.out.println("outputFile:"+outputFile);
                                String name = baseVariantOutput.getName();
                                project.getTasks().create("jiaGu"+name,JiaGuTask.class,outputFile,jiaGuExt);
                            }
                        });
                    }
                });
            }
        });
    }

}