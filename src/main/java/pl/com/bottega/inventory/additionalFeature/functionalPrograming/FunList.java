package pl.com.bottega.inventory.additionalFeature.functionalPrograming;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface FunList<T> {

    static <T> FunList<T> empty() {//parametryzowana typem metoda statyczna
        return EmptyList.getInstance();
    }

    static <T> FunList<T> empty(Class<T> tClass) {//parametryzowana typem metoda statyczna
        return EmptyList.getInstance();
    }
    FunList<T> add(T element);

    void each(Consumer<T> consumer);

    int size();

    FunList<T> remove(T element);

    <R> FunList<R> map(Function<T,R> mapper);//metoda generyczna

    <AccT> AccT foldLeft(AccT identity, BiFunction<AccT,T, AccT> folder);

    default FunList<T> filter(Predicate<T> predicate){
        return foldLeft(empty(),(acc,element)->{
            if (predicate.test(element))
                return acc.add(element);
            else
                return acc;
        });
    }
}
