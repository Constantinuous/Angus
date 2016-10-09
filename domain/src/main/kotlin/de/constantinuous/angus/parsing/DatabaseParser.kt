package de.constantinuous.angus.parsing

/**
 * Created by RichardG on 09.10.2016.
 */
interface DatabaseParser{

    fun extractFoo(connectionString: String, user: String, password: String)
}