package de.constantinuous.tessara.browser

/**
 * Created by RichardG on 08.06.2016.
 */
import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

class Styles : Stylesheet() {
    companion object {
        val loginScreen by cssclass()
    }

    init {
        select(loginScreen) {
            padding = box(15.px)
            vgap = 7.px
            hgap = 10.px
        }
    }
}