package com.nixsolution.config.mvc;

import com.nixsolution.config.hibernate.HibernateConfig;
import com.nixsolution.config.spring.SpringConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { SpringConfig.class, HibernateConfig.class,  };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
