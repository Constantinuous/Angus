package de.constantinuous.tessara.browser

import javafx.application.Application
import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

/**
 * Created by RichardG on 08.06.2016.
 */
class LoginApp : App() {
    override val primaryView = LoginScreen::class
    val loginController: LoginController by inject()

    override fun start(stage: Stage) {
        importStylesheet(Styles::class)
        super.start(stage)
        loginController.init()
    }
}