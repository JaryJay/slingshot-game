package colour;

import processing.core.PApplet;

/**
 * Combines R, G, B, A values into an integer, or splits an integer into R, G,
 * B, A values.
 * 
 * @author Jay
 *
 */
public final class Colour {

	private Colour() {
	}

	/**
	 * Combines an RGB colour into an integer.
	 * 
	 * @param r the R value of the colour
	 * @param g the R value of the colour
	 * @param b the R value of the colour
	 * @return the integer that contains all of the RGB values.
	 */
	public static int of(final int r, final int g, final int b) {
		return of(r, g, b, 255);
	}

	public static int of(final int r, final int g, final int b, final int a) {
		int rc = r << 24;
		int gc = g << 16;
		int bc = b << 8;
		return (rc | gc | bc | a);
	}

	/**
	 * Splits an integer into its RGBA values. Can also be used to convert
	 * hexadecimal colours into RGB values, but you need to enter the hexadecimal
	 * starting with 0x. Example: <code>split(0x409c86)</code>
	 * 
	 * @param colour the colour you want to split
	 * @return
	 */
	public static int[] split(final int colour) {
		return new int[] {

				(colour & 0xFF000000) >>> 24,

				(colour & 0xFF0000) >>> 16,

				(colour & 0xFF00) >>> 8,

				colour & 0xFF };
	}

	public static void fill(final int colour, final PApplet p) {
		int[] colours = split(colour);
		p.fill(colours[0], colours[1], colours[2], colours[3]);
	}

}
