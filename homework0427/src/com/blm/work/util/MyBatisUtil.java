package com.blm.work.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * 获得MyBatis会话的工具类
 * @author Administrator
 *
 */
public class MyBatisUtil {

	/*会话工厂*/
	private static SqlSessionFactory sessionFactory;
	static{
		try {
			//加载mybatis.xml
			Reader reader = Resources.getResourceAsReader("mybatis.xml");
			//创建会话工厂
			SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
			sessionFactory = sessionFactoryBuilder.build(reader);
		} catch (IOException e) {
			System.out.println("创建出错了");
		}
	}
	public static SqlSession getSqlSession(){
		return sessionFactory.openSession();
	}
}
