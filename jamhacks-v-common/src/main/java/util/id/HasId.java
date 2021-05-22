package util.id;

/**
 * Something that has an ID. To use this interface, create a field <br>
 * <code>private long id</code> <br>
 * in your implementation and return it in the getId() function.
 * 
 * @author Jay
 *
 */
public interface HasId {

	/**
	 * Gets the ID.
	 * 
	 * @return the ID
	 */
	public abstract long getId();

	/**
	 * Sets the ID.
	 * 
	 * @param id the ID to set
	 */
	public abstract void setId(long id);

}
