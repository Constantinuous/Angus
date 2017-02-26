package de.constantinuous.dietrich.presentation

import javafx.scene.shape.Rectangle

/**
 * Created by RichardG on 26.02.2017.
 */
class SimpleNode(val weight: Int) : Node{

    override var bounds: Rectangle = Rectangle(0.0, 0.0, weight.toDouble() / 2, weight.toDouble() / 2)
    override var size: Int = weight
}