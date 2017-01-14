package de.constantinuous.dietrich.presentation

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import tornadofx.View
import tornadofx.plusAssign

/**
 * Created by RichardG on 14.01.2017.
 */
class MyView: View() {
    override val root = VBox()
    init {
        root += Button("Press Me")
        root += Label("")
    }
}