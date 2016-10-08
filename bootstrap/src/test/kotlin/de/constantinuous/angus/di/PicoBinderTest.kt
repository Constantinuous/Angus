package de.constantinuous.angus.di

import com.natpryce.hamkrest.assertion.*
import com.natpryce.hamkrest.*
import de.constaninuous.angus.di.impl.PicoBinder
import de.constantinuous.angus.fs.FileSystem
import de.constantinuous.angus.fs.LocalFileSystem
import io.kotlintest.specs.FeatureSpec
import io.kotlintest.specs.StringSpec
import org.junit.Test

/**
 * Created by RichardG on 25.09.2016.
 */
class PicoBinderTest : FeatureSpec(){

    init {
        feature("PicoBinder") {
            scenario("should allow a subclass to be bound to the parent interface") {
                val di = PicoBinder()

                di.bindInterface(FileSystem::class.java, toImplementation(LocalFileSystem::class.java))
            }

            scenario("should not allow an unrelated class to be bound to the interface") {
                val di = PicoBinder()

                shouldThrow<ClassCastException> {
                    di.bindInterface(FileSystem::class.java, toImplementation(PicoBinderTest::class.java))
                }
            }
        }
    }
}