package com.crazy.chapter14.duplicate;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;

public class HibernateAnnotationProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> tye, RoundEnvironment env) {
		PrintStream ps = null;
		try {
			for (Element t : env.getElementsAnnotatedWith(Persistent.class)) {
				Name clazzName = t.getSimpleName();
				Persistent per = t.getAnnotation(Persistent.class);
				ps = new PrintStream(new FileOutputStream(clazzName + ".hbm.xml"));
				ps.println("<?xml version=\"1.0\"?>");
				ps.println("<!DOCTYPE hibernate-mapping PUBLIC");
				ps.println(" \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"");
				ps.println("<hibernate-mapping>");
				ps.print("<class name=\"" + t);
				ps.println("\" table=\"" + per.table() + "\">");
				for (Element f : t.getEnclosedElements()) {
					if (f.getKind() == ElementKind.FIELD) {
						Id id = f.getAnnotation(Id.class);
						if (id != null) {
							ps.println("			<id name=\""
									+ f.getSimpleName()
									+ "\" + column=\"" + id.column()
									+ "\" + type=\"" + id.type()
									+ "\">");
							ps.println("			<generator class=\""
									+ id.generator() + "\"/>");
							ps.println("			</id>");
						}
						Property p = f.getAnnotation(Property.class);
						if (p != null) {
							ps.println("			<property name=\""
									+ f.getSimpleName()
									+ "\" column=\"" + p.column()
									+ "\" type=\"" + p.type()
									+ "\"/>");
						}
					}
				}
				ps.println("	</class>");
				ps.println("</hibernate-mapping>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

}
