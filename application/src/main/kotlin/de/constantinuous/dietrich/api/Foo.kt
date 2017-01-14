package de.constantinuous.dietrich.api

import io.reactivex.Observable
import java.io.File

/**
 * Created by RichardG on 14.01.2017.
 */
class Foo {

    fun loopOverFiles(){
        Observable.fromIterable(File(".").walkTopDown().asIterable()).flatMap { fileToFileInfo(it) }
        .subscribe(
            { fileInfo -> println(fileInfo) },
            { throwable -> throwable.printStackTrace() })
    }
}

data class FileInfo(val file: File,
                    var bytes: Long = 0,
                    var filesCount: Long = 1)

// A function to compute a FileInfo out of a file
fun fileToFileInfo(file: File): Observable<FileInfo> {
    return Observable.just(FileInfo(file, file.totalSpace, 0))
}