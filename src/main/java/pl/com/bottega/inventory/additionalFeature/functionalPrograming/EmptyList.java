package pl.com.bottega.inventory.additionalFeature.functionalPrograming;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

class EmptyList<T> implements FunList<T> {

    private static final EmptyList INSTANCE = new EmptyList();

    private EmptyList() {
    }

    @Override
    public FunList<T> add(T element) {
        return new NonEmptyList<>(element);
    }

    @Override
    public void each(Consumer<T> consumer) {
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public FunList<T> remove(T element) {
        return this;
    }

    @Override
    public <R> FunList<R> map(Function<T, R> mapper) {
        return FunList.empty();
    }

    @Override
    public <AccT> AccT foldLeft(AccT identity, BiFunction<AccT, T, AccT> folder) {
        return identity;
    }

    public static FunList getInstance() {
        return INSTANCE;
    }
}
