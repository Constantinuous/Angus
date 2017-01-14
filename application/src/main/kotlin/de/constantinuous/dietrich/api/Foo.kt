package de.constantinuous.dietrich.api

import io.reactivex.Observable

/**
 * Created by RichardG on 14.01.2017.
 */
class Foo {

    fun doFoo(){
        Observable.just("Hello", "World").subscribe { input -> println(input) }
    }
}