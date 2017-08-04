package com.kee.ad.config.Swagger;

import com.google.common.base.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.FactoryBean;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author KeeYang on 2017/7/21.
 * @Description :
 */
public class SwaggerConfigFactoryBean implements FactoryBean<Docket> {

    private String moduleName;

    private boolean userSwagger;

    private String controllerPackageName;


    public SwaggerConfigFactoryBean(String moduleName, boolean userSwagger, String controllerPackageName) {
        super();
        this.moduleName = moduleName;
        this.userSwagger = userSwagger;
        this.controllerPackageName = controllerPackageName;
    }

    public SwaggerConfigFactoryBean(String moduleName, boolean userSwagger) {
        super();
        this.moduleName = moduleName;
        this.userSwagger = userSwagger;
    }

    public SwaggerConfigFactoryBean() {
        super();
    }

    public Docket getObject() throws Exception {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(setApiInfo())
                .enable(userSwagger)
                .groupName(moduleName)
                .select()
                .apis(new Predicate<RequestHandler>() {
                    public boolean apply(RequestHandler requestHandler) {
                        String packages = controllerPackageName;
                        String[] packagesArr = packages.split(";");
                        boolean shouldApply = false;
                        for (String p : packagesArr) {
                            if (StringUtils.isBlank(p)) {
                                continue;
                            }
                            shouldApply = declaringClass(requestHandler).getPackage().getName().startsWith(p);
                            if (shouldApply) {
                                break;
                            }
                        }
                        return shouldApply;
                    }
                })
                .paths(PathSelectors.any())
                .build();

    }

    protected ApiInfo setApiInfo() {
        return new ApiInfoBuilder().title("Ad API 说明").description("hehe").version("1.0").build();
    }

    public Class<?> getObjectType() {
        return null;
    }

    public boolean isSingleton() {
        return true;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public boolean isUserSwagger() {
        return userSwagger;
    }

    public void setUserSwagger(boolean userSwagger) {
        this.userSwagger = userSwagger;
    }

    public String getControllerPackageName() {
        return controllerPackageName;
    }

    public void setControllerPackageName(String controllerPackageName) {
        this.controllerPackageName = controllerPackageName;
    }

    protected static Class<?> declaringClass(RequestHandler input) {
        return input.getHandlerMethod().getMethod().getDeclaringClass();
    }
}
