package de.constaninuous.angus.di

/**
 * Created by RichardG on 25.09.2016.
 */
class NullBinder : Binder {

    override fun <T> resolveImplementation(port: Class<T>): T {
        throw UnsupportedOperationException()
    }

    override fun <T> bindInterface(port: Class<T>, adapter: ClassMatcher<T>) {

    }
}