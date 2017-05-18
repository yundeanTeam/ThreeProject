package com.blm.work.domain;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.blm.work.util.MyBatisUtil;

public class MainTest {

	private static final String NAMESPACE = "com.blm.work.domain.WorkMapper.";
	/**
	 * 修改学生班级信息（换班级）
	 */
	@Test
	public void testUpdate(){
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			Grade grade = session.selectOne(NAMESPACE+"selectGrade",1);
			if(null!=grade){
				Student student = new Student();
				student.setSid(1);
				student.setGrade(grade);
				int resaut = session.update(NAMESPACE+"updateGrade",student);
				session.commit();
				if(resaut>0){
					System.out.println("修改成功");
				}
			}else {
				System.out.println("修改失败！");
			}
			
		} catch (Exception e) {
			if(null!=session){
				session.rollback();
			}
		}finally{
			if(null!=session){
				session.close();	
			}
		}
	}

	/**
	 * 删除某个学生的班级
	 */
	@Test
	public void testDeleteOne(){
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			int a = session.update(NAMESPACE+"updateOne",2);
			//session.delete(NAMESPACE+"deleteOne",2); 直接删除该学生
			session.commit();
			if(a>0){
				System.out.println("移除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(null!=session){
				session.rollback();
				System.out.println("失败！");
			}
		}finally{
			if(null!=session){
				session.close();
			}
		}
	}
	
	/**
	 * 删除某个班级
	 */
	@Test
	public void testDeleteAll(){
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSqlSession();
			Grade grade = session.selectOne(NAMESPACE+"selectGrade",1);
			if(null!=grade){
				Student student = new Student();
				student.setGrade(grade);
				int a = session.delete(NAMESPACE+"deleteStudent",student);
				int b = session.delete(NAMESPACE+"deleteAll",1);
				session.commit();
				if(a>0&&b>0){
					System.out.println("删除成功");
				}
			}else{
				System.out.println("失败！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(null!=session){
				session.rollback();
				System.out.println("失败！");
			}
		}finally{
			if(null!=session){
				session.close();
			}
		}
	}
}
