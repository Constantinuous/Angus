package de.constantinuous.tessara.browser

/**
 * Created by RichardG on 08.06.2016.
 */
import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.scene.layout.Background
import javafx.scene.layout.Border
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import tornadofx.Controller
import tornadofx.FX

class LoginController : Controller() {
    val loginScreen: LoginScreen by inject()
    val workbench: WorkBench by inject()

    val loginName = SimpleStringProperty()

    fun init() {
        val hbox = HBox()
        hbox.border = Border.EMPTY

    }



}