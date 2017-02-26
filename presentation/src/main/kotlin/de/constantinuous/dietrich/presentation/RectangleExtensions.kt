package de.constantinuous.dietrich.presentation

import javafx.scene.shape.Rectangle

/**
 * Created by RichardG on 26.02.2017.
 */
fun Rectangle.aspectRatio(): Double{
    return Math.max(width/height, height/width);
}