package pl.com.bottega.inventory.AdditionalTasks.fun;

//import org.junit.Test;

import org.junit.Test;
import pl.com.bottega.inventory.additionalFeature.functionalPrograming.FunList;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FunListTest {

    @Test
    public void addsElementToEmptyList() {
        FunList<String> list = FunList.empty();

        FunList newList = list.add("element");

        assertThat(newList.size()).isEqualTo(1);
    }

    @Test
    public void addsThreeElementsToList() {
        FunList<Integer> list = FunList.empty();

        FunList newList = list.add(1).add(2).add(3);

        assertThat(newList.size()).isEqualTo(3);
    }

    @Test
    public void removesElementsFromList() {
        FunList<Integer> list = FunList.empty();

        FunList newList = list.add(1).remove(1);

        assertThat(newList.size()).isEqualTo(0);
    }

    @Test
    public void removesElementsFromTheMiddle() {
        FunList<Integer> list = FunList.empty();


        FunList<Integer> newList = list.add(1).add(2).add(3).add(4).remove(3);

        assertThat(newList.size()).isEqualTo(3);
    }

    @Test
    public void canHoldNullValues() {
        FunList<Integer> list = FunList.empty(Integer.class).add(1).add(null).add(2).remove(null);

        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void isGeneric() {
        FunList<Integer> intList = FunList.empty(Integer.class).add(1);
    }

    @Test
    public void iteratesOverEachElement() {
        StringBuilder stringBuilder = new StringBuilder();
        FunList<String> strings = FunList.empty(String.class).add("1").add("2").add("3");
        strings.each(stringBuilder::append);
        assertThat(stringBuilder.toString()).isEqualTo("123");
    }

    @Test
    public void mapsList() {
        FunList<String> strings = FunList.empty(String.class).add("a").add("b").add("c");

        FunList<String> upperStrings = strings.map(String::toUpperCase);

        assertThat(upperStrings).isEqualTo(FunList.empty(String.class).add("A").add("B").add("C"));
    }

    @Test
    public void foldsLeft() {
        FunList<String> strings = FunList.empty(String.class).add("a").add("b").add("c");

        String concatenated = strings.foldLeft("ala", (acc, string) -> acc + string);

        Integer lengthSum = strings.foldLeft(0, (sum, string) -> sum + string.length());

        assertThat(concatenated).isEqualTo("alaabc");
        assertThat(lengthSum).isEqualTo(3);
    }


    @Test
    public void filtersElements() {
        FunList<Integer> ints = FunList.empty(Integer.class).add(1).add(2).add(3).add(4).add(5);

        FunList<Integer> odds = ints.filter((n) -> (n % 2 == 1));

        assertThat(odds).isEqualTo(FunList.empty(Integer.class).add(1).add(3).add(5));
    }

}
