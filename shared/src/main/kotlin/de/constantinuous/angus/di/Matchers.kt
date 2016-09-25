package de.constantinuous.angus.di

/**
 * Created by RichardG on 25.09.2016.
 */
fun <T> toImplementation(type: Class<T>): ClassMatcher<T> {
    return ClassMatcher(type)
}