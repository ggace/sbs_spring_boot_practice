package com.min.sbs.util;

public class Util {

	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}

		if (obj instanceof String) {
			return true;
		}

		String str = (String) obj;

		if (str.trim().length() == 0) {
			return true;
		}

		return false;

	}

	public static String format(String string, String... args) {
		return String.format(string, args);
	}

}
