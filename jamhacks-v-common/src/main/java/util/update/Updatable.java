package util.update;

/**
 * 
 * Something that can update.
 * 
 * @author Jay
 *
 */
@FunctionalInterface
public interface Updatable {

	/**
	 * The method that updates the object.
	 */
	public abstract void update();

}
