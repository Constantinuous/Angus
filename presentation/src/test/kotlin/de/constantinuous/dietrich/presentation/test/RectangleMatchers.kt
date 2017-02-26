package de.constantinuous.dietrich.presentation.test

import io.kotlintest.matchers.Matcher
import io.kotlintest.matchers.ToleranceMatcher
import javafx.scene.shape.Rectangle

/**
 * Created by RichardG on 26.02.2017.
 */

fun matchBoundsOf(expected: Rectangle): Matcher<Rectangle> = object : Matcher<Rectangle> {
    override fun test(actual: Rectangle) {
        if (expected.x != actual.x) {
            throw AssertionError("X-Position ${actual.x} is not equal to expected value ${expected.x}")
        }
        if (expected.y != actual.y) {
            throw AssertionError("Y-Position ${actual.y} is not equal to expected value ${expected.y}")
        }
        if (expected.width != actual.width) {
            throw AssertionError("Width ${actual.width} is not equal to expected value ${expected.width}")
        }
        if (expected.height != actual.height) {
            throw AssertionError("Height ${actual.height} is not equal to expected value ${expected.height}")
        }
    }
}