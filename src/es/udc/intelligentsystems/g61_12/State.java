package es.udc.intelligentsystems.g61_12;

public abstract class State {
    /* Any State class must override these methods to be correctly shown and allow comparisons. */

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

}
