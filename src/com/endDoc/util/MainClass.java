package com.endDoc.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Configuration configuration = new Configuration().configure(); 
		SchemaExport export = new SchemaExport(configuration);
		export.create(true, true);
	}
}