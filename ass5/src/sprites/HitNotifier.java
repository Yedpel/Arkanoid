package sprites;

/**
 * The interface Hit notifier.
 */
public interface HitNotifier {
    /**
     * Add hit listener.
     *
     * @param hl the hit listener to add
     */
// Add hl as a listener to hit events.
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     *
     * @param hl the hit listener to remove
     */
// Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}