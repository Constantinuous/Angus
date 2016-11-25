package de.constantinuous.angus.test

import org.mockito.Mockito

/**
 * Created by RichardG on 25.11.2016.
 */
inline fun <reified T: Any> mock() = Mockito.mock(T::class.java)

inline fun <reified T : Any> any() = Mockito.any(T::class.java) ?: uninitialized<T>()

fun <T> uninitialized(): T = null as T
