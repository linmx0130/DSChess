package com.sweetdum.dschess.utils;

import java.util.Objects;

/**
 * A two dimension tuple implementation.
 *
 * Created by Mengxiao Lin on 2016/11/22.
 */
public class Tuple<T extends Number> implements Cloneable{
    private T x1,x2;
    public Tuple(T x1, T x2){
        this.x1=x1;
        this.x2=x2;
    }

    public T x1() {
        return x1;
    }

    public T x2() {
        return x2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple<?> tuple = (Tuple<?>) o;

        if (x1 != null ? !x1.equals(tuple.x1) : tuple.x1 != null) return false;
        return x2 != null ? x2.equals(tuple.x2) : tuple.x2 == null;

    }

    @Override
    public int hashCode() {
        int result = x1 != null ? x1.hashCode() : 0;
        result = 31 * result + (x2 != null ? x2.hashCode() : 0);
        return result;
    }

    @Override
    public Object clone() {
        return new Tuple<>(x1,x2);
    }

    @Override
    public String toString() {
        return "( "+x1()+" , " + x2 +" ) ";
    }
}
