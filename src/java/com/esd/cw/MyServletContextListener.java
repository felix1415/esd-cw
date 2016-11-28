package com.esd.cw;

import com.esd.cw.dao.DbBean;
import javax.servlet.Servlet;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

    private static final String URL_KEY = "database-url";
    private static final String PORT_KEY = "database-url";
    private static final String USERNAME_KEY = "database-username";
    private static final String PASSWORD_KEY = "database-password";
    private static final String DATABASE_NAME_KEY = "database-name";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String url = sce.getServletContext().getInitParameter(URL_KEY);
        String username = sce.getServletContext().getInitParameter(USERNAME_KEY);
        String password = sce.getServletContext().getInitParameter(PASSWORD_KEY);
        String databaseName = sce.getServletContext().getInitParameter(DATABASE_NAME_KEY);

        DbBean.setParameters(url, username, password, databaseName);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DbBean.getInstance().shutDown();
    }

}
