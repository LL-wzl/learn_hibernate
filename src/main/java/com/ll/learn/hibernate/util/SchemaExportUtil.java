package com.ll.learn.hibernate.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.ll.learn.hibernate.c_set.Member;

/**
 * 创建表的工具
 * @author Administrator
 *
 */
public class SchemaExportUtil {

	public static void main(String[] args) {

		Configuration cfg = new Configuration().configure().addClass(Member.class);
		
		 SchemaExport schemaExport = new SchemaExport(cfg);
		 schemaExport.create(true, false);
		 
		
	}

}
