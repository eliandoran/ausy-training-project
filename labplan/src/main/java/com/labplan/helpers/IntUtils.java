package com.labplan.helpers;

/**
 * Provides a set of utility functions for parsing {@link Integer} values.
 * @author Elian Doran
 *
 */
public class IntUtils {
	/**
	 * Attempts to parse an {@link Integer} from a {@link String}. Should the string be empty or the conversion failed,
	 * the function returns {@code null} instead of throwing a {@link NumberFormatException}.
	 * @param str	A {@link String} containing the number to be converted.
	 * @return	An {@link Integer} containing the parsed number or {@code null} if failed.
	 */
	public static Integer tryParse(String str) {
		if (str == null || str.length() == 0)
			return null;
		
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
