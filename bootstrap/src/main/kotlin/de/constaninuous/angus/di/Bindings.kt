package de.constaninuous.angus.di

import de.constantinuous.angus.fs.FileSystem

/**
 * Created by RichardG on 25.09.2016.
 */
class Bindings {

    fun createAllBindings(){
        DiContainer.instance = PicoBinder()
        val di = DiContainer.instance

        di.bindInterface(FileSystem::class, toImplementation(LocalFileSystem))
    }
}