package app.configuration;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MyDispatcher extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {//Метод, указывающий на класс конфигурации
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {//Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
        return new Class<?>[]{
                MyConfiguration.class
        };
    }

    @Override
    protected String[] getServletMappings() { //Данный метод указывает url, на котором будет базироваться приложение
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException { //запускается при старте. Выполняем приватный метод, который добавляет фильтр
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }

    private void registerHiddenFieldFilter(ServletContext aContext) { //фильтр, который разрешает выполнять методы PATCH и DELETE
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true, "/*");
    }

}
