package com.types.controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Normalizer;

import javax.swing.table.DefaultTableModel;

import com.types.tads.ArrayIndexList;
import com.types.tads.ArrayStack;

public class Types {

	public static Object[] data;
	public static Object type;
	private static int option;

	public Types(int option) {
		Types.option = option;
		switch (option) {
		case 0:
			type = new ArrayIndexList<Object>();
			break;
		case 1:
			type = new ArrayStack<Object>();
			break;
		}
	}

	@SuppressWarnings("rawtypes")
	public static void execute(Object obj, String name, Class[] parameterTypes, Object[] data,
			DefaultTableModel model) {
		Method m;
		try {
			String result = name + "(";
			for (Object d : data) {
				result += d.toString() + ", ";
			}
			if (data.length != 0)
				result = result.substring(0, result.length() - 2) + ")";
			else
				result += ")";
			try {
				m = obj.getClass().getMethod(name, parameterTypes);
				model.addRow(new Object[] { result, m.invoke(obj, data) });

			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { }
		} catch (NoSuchMethodException | SecurityException e) { }
	}

	public static String[] getTexts(int action) {
		switch (option) {
		case 0:
			if (action == 1)
				return new String[] { "Índice:", "Valor:" };
			else
				return new String[] { "Índice:" };
		case 1:
			if (action == 1)
				return new String[] { "Valor para colocar na pilha: " };
		}
		return null;
	}

	public static String stripAccents(String s) {
		s = Normalizer.normalize(s, Normalizer.Form.NFD);
		s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		return s;
	}

}
