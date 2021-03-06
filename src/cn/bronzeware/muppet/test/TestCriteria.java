package cn.bronzeware.muppet.test;

import java.util.Iterator;
import java.util.List;

import cn.bronzeware.core.ioc.annotation.Component;
import cn.bronzeware.muppet.core.Criteria;
import cn.bronzeware.muppet.core.Session;
import cn.bronzeware.muppet.core.SessionFactory;
import cn.bronzeware.muppet.entities.Note;
import cn.bronzeware.muppet.util.ArrayUtil;
import cn.bronzeware.muppet.util.log.Logger;
import cn.bronzeware.util.testframework.Test;

@Test
@Component
public class TestCriteria {

	SessionFactory factory;
	public  Session muppet ;
	public TestCriteria(){
		factory = new SessionFactory("muppet.xml");
	}
/*	static
	{
		muppet = factroy.getSession(true);
	}
*/
	@Test
	public  void test1(){

		muppet = factory.getSession(true);
		Criteria<Note> criteria = muppet.createCriteria(Note.class);
		//criteria.andPropEqual("id", 37);
		//criteria.andPropGreater("id", 36);
		//criteria.andPropGreaterEq("id", 39);
		//criteria.andPropGreater("id", 36).order("user_id", false).order("id", false);
		//criteria.andPropLess("id", 37);
		criteria.andPropGreater("user_id", 0);
		//criteria.andPropLike("username", "%yuhai1%");
		/*Criteria criteria1 = muppet.createCriteria(Note.class);
		criteria1.andPropEqual("user_id", 35);
		criteria.or(criteria1);*/
		
		System.out.println(criteria.select("id,value,username,password,user_id")
				.andPropGreater("user_id", 100).list().size());
		//list.get(200);
		//list.add(new Note());
		//list.get(-1);
		//System.out.println(criteria.one());
		Logger.println(criteria.one());
		Logger.println(criteria.count());
		muppet.close();
		//ArrayUtil.println(list);
	}
	
	
	public void test2(){
		muppet = factory.getSession(true);
		for(int i = 0;i < 1000; i++){
			Note note = new Note();
			note.setId(i+2);
			note.setPassword(String.valueOf(i+2));
			note.setUser_id(String.valueOf(i+2));
			note.setValue("a");
			muppet.insert(note);
		}
	}
	
	public static void main(String[] args){
		TestCriteria criteria = new TestCriteria();
		criteria.test1();
	}
	
}
