import java.time.LocalDate;

/**
 * An interface for a sorted list that stores shopping data.
 * The data is sorted by dates in ascending order.
 * @param <K> The type of the key (in this case, LocalDate).
 * @param <V> The type of the value (in this case, String).
 */
public interface SortedList_Interface<K, V> {

    /**
     * Returns the number of elements in the list.
     * @return The size of the list.
     */
    int size();

    /**
     * Adds a new entry to the sorted list.
     * The entry is added in ascending order based on the date.
     * @param date The date to add to the list.
     * @param datatoadd The shopping data to add for the specified date.
     */
    void add(LocalDate date, String datatoadd);

    /**
     * Displays the shopping data in the order they were added to the list.
     * If the list is empty, a message is printed.
     */
    void display();

    /**
     * Displays the shopping data in reverse order (descending order of dates).
     * If the list is empty, a message is printed.
     */
    void displayReverse();

    /**
     * Sets the date of the product if the date was wrongly given.
     * If the specified date is not found or the list is empty, a message is printed.
     * @param index The date to update.
     * @param object The corrected date.
     */
    void setKey(LocalDate index, LocalDate object);

    /**
     * Gets the product associated with the specified date.
     * If the specified date is not found or the list is empty, a message is printed.
     * @param index The date to retrieve the product for.
     * @return The product associated with the specified date, or a message if not found.
     */
    String getValue(LocalDate index);

    /**
     * Rewrites the product and deletes the last product of the specified date.
     * If the specified date is not found or the list is empty, a message is printed.
     * @param index The date to update.
     * @param object The new product information.
     */
    void setValueNR(LocalDate index, String object);

    /**
     * Adds the product information for the specified date.
     * If the specified date is not found or the list is empty, a message is printed.
     * @param index The date to update.
     * @param object The product information to add.
     */
    void setValueR(LocalDate index, String object);

    /**
     * Gets the size of the list.
     * @return The size of the list.
     */
    int getsize();

}
