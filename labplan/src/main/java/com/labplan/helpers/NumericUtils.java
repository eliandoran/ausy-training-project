package com.labplan.helpers;

/**
 * Provides a set of utility functions for parsing {@link Integer} and
 * {@link Float} values.
 * 
 * @author Elian Doran
 *
 */
public class NumericUtils {
	/**
	 * Attempts to parse an {@link Integer} from a {@link String}. Should the string
	 * be empty or the conversion failed, the function returns {@code null} instead
	 * of throwing a {@link NumberFormatException}.
	 * 
	 * @param str
	 *            A {@link String} containing the number to be converted.
	 * @return An {@link Integer} containing the parsed number or {@code null} if
	 *         failed.
	 */
	public static Integer tryParseInteger(String str) {
		if (str == null || str.length() == 0)
			return null;

		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * Attempts to parse a {@link Float} from a {@link String}. Should the string be
	 * empty or the conversion failed, the function returns {@code null} instead of
	 * throwing a {@link NumberFormatException}.
	 * 
	 * @param str
	 *            A {@link String} containing the number to be converted.
	 * @return An {@link Float} containing the parsed number or {@code null} if
	 *         failed.
	 */
	public static Float tryParseFloat(String str) {
		if (str == null || str.length() == 0)
			return null;

		try {
			return Float.parseFloat(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
