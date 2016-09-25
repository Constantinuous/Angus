package de.constaninuous.angus.di

import java.lang.reflect.Type
import kotlin.reflect.KClass

/**
 * Created by RichardG on 25.09.2016.
 */
interface Binder {

    fun <T> bindInterface(port: Type, adapter: ClassMatcher<T>)

    fun <T> resolveImplementation(port: Type): T
}