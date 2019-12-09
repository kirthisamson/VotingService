package com.kirthisamson.votingsystem.Util;

import java.util.function.Predicate;

public class StreamUtil {
    public static <R> Predicate<R> not(Predicate<R> predicate) {
        return predicate.negate();
    }
}
