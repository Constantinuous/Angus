package de.constantinuous.angus.di

import java.lang.reflect.Type
import kotlin.reflect.KClass

/**
 * Created by RichardG on 25.09.2016.
 */
interface Binder {

    fun <T> bindInterface(port: Class<T>, adapter: ClassMatcher<*>)

    fun <T> resolveImplementation(port: Class<T>): T
}