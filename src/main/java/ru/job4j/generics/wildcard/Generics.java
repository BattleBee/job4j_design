package ru.job4j.generics.wildcard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

        //gen.printBoundedWildCard(first);
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
        //gen.printLowerBoundedWildCard(third);
    }

    /**
     * Использование обобщенного типа WildCard (обозначается <?>) решает проблему совметстимости
     * типов указанных во входном параметре. При использовании этого типа нет ограничений
     * @param list
     */
    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     * Обобщенный тип Bounded wildcard (обозначается <? extends А>) - так называемое "Ограничение сверху".
     * Указывается верхняя граница типа параметра, который может использоваться в качестве типа.
     * в качестве 'A' указывается базовый класс или интерфейс. Можно ограничить обобщенный тип несколькими
     * интерфейсами используя между ними символ '&' - например <T extends А & B>.
     * В данном методе может использоваться тип Predator и классов наследников Predator.
     * @param list
     */
    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     * Обобщенный тип Lower bounded wildcard (обозначается <? super A>) - так называемое "Ограниченный снизу".
     * Указывается нижняя граница типа параметра, который может использоваться в качестве типа.
     * В данном методе может обобщенный тип ограничен классом Predator и родительскими классами Predator.
     * @param list
     */
    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}