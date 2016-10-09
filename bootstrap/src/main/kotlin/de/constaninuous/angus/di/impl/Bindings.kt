package de.constaninuous.angus.di.impl

import de.constantinuous.angus.di.DiContainer
import de.constantinuous.angus.di.toImplementation
import de.constantinuous.angus.fs.FileSystem
import de.constantinuous.angus.fs.LocalFileSystem
import de.constantinuous.angus.parsing.DatabaseParser
import de.constantinuous.angus.parsing.HtmlParser
import de.constantinuous.angus.parsing.impl.JdbcDatabaseParser
import de.constantinuous.angus.parsing.impl.JerichoHtmlParser
import org.picocontainer.DefaultPicoContainer

/**
 * Created by RichardG on 25.09.2016.
 */
fun createAllBindings(){
    DiContainer.instance = PicoBinder()
    val di = DiContainer.instance

    DefaultPicoContainer().addComponent(FileSystem::class, LocalFileSystem::class);

    di.bindInterface(FileSystem::class.java, toImplementation(LocalFileSystem::class.java))
    di.bindInterface(HtmlParser::class.java, toImplementation(JerichoHtmlParser::class.java))
    di.bindInterface(DatabaseParser::class.java, toImplementation(JdbcDatabaseParser::class.java))
}