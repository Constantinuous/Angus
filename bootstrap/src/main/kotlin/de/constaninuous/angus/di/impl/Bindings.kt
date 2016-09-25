package de.constaninuous.angus.di.impl

import de.constantinuous.angus.di.DiContainer
import de.constantinuous.angus.di.toImplementation
import de.constantinuous.angus.fs.FileSystem
import de.constantinuous.angus.fs.LocalFileSystem
import org.picocontainer.DefaultPicoContainer

/**
 * Created by RichardG on 25.09.2016.
 */
class Bindings {

    fun createAllBindings(){
        DiContainer.instance = PicoBinder()
        val di = DiContainer.instance

        DefaultPicoContainer().addComponent(FileSystem::class, LocalFileSystem::class);

        di.bindInterface(FileSystem::class.java, toImplementation(LocalFileSystem::class.java))
    }
}