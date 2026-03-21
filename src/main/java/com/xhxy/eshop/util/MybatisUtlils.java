package com.xhxy.eshop.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtlils {
    private static SqlSessionFactory sqlSessionFactory;
    
    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream is = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();
    
    public static SqlSession getSqlSession() {
        SqlSession session = threadLocal.get();
        if (session == null) {
            session = sqlSessionFactory.openSession(false);  // 手动提交事务
            threadLocal.set(session);
        }
        return session;
    }
    
    public static void commit() {
        SqlSession session = threadLocal.get();
        if (session != null) {
            session.commit();
        }
    }
    
    public static void rollback() {
        SqlSession session = threadLocal.get();
        if (session != null) {
            session.rollback();
        }
    }
    
    public static void close() {
        SqlSession session = threadLocal.get();
        if (session != null) {
            session.close();
            threadLocal.remove();
        }
    }
}
