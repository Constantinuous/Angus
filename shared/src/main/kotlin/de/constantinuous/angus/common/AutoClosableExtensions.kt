package de.constantinuous.angus.common

/**
 * Created by RichardG on 14.01.2017.
 */
inline fun <T : AutoCloseable, R> T.use(block: (T) -> R): R {
    var currentThrowable: java.lang.Throwable? = null
    try {
        return block(this)
    } catch (throwable: Throwable) {
        currentThrowable = throwable as java.lang.Throwable
        throw throwable
    } finally {
        if (currentThrowable != null) {
            try {
                close()
            } catch (throwable: Throwable) {
                currentThrowable.addSuppressed(throwable)
            }
        } else {
            close()
        }
    }
}