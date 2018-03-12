package pl.com.bottega.inventory.additionalFeature.functionalPrograming;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

class NonEmptyList<T> implements FunList<T> {

    private final T head;
    private final FunList<T> tail;

    NonEmptyList(T element) {
        head = element;
        tail = EmptyList.getInstance();
    }

    private NonEmptyList(T head, FunList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public FunList<T> add(T element) {
        return new NonEmptyList<>(head, tail.add(element));
    }

    @Override
    public void each(Consumer<T> consumer) {
        consumer.accept(head);
        tail.each(consumer);
    }

    @Override
    public int size() {
        return 1 + tail.size();
    }

    @Override
    public FunList<T> remove(T element) {
        if (element == head || (element != null && element.equals(head)))
            return tail;
        else
            return new NonEmptyList<>(head, tail.remove(element));
    }

    @Override
    public <R> FunList<R> map(Function<T, R> mapper) {
        return new NonEmptyList<>(mapper.apply(head), tail.map(mapper));
    }

    @Override
    public <AccT> AccT foldLeft(AccT identity, BiFunction<AccT, T, AccT> folder) {
        AccT foldedHead = folder.apply(identity, head);
        return tail.foldLeft(foldedHead, folder);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NonEmptyList<?> that = (NonEmptyList<?>) o;

        return (head != null ? head.equals(that.head) : that.head == null) && tail.equals(that.tail);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
