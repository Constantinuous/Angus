package de.constaninuous.angus.di.impl

import de.constantinuous.angus.di.Binder
import de.constantinuous.angus.di.ClassMatcher
import org.picocontainer.DefaultPicoContainer
import org.picocontainer.MutablePicoContainer

/**
 * Created by RichardG on 25.09.2016.
 */
class PicoBinder : Binder {

    private val picoContainer: MutablePicoContainer = DefaultPicoContainer()

    override fun <T> bindInterface(port: Class<T>, adapter: ClassMatcher<*>) {
        picoContainer.addComponent(port, adapter.aClass);
    }

    override fun <T> resolveImplementation(port: Class<T>): T {
        return picoContainer.getComponent(port)
    }

}