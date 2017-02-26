package de.constantinuous.dietrich.presentation

import javafx.geometry.Rectangle2D
import com.sun.awt.SecurityWarning.getSize
import javafx.scene.shape.Rectangle
import kotlin.comparisons.compareBy


/**
 * Created by RichardG on 26.02.2017.
 */
class TreeMap() {
    private var cursor: Int = 0

    fun squarify(items: List<Node>, bounds: Rectangle) {
        squarify(sortDescending(items), 0, items.size - 1, bounds)
    }

    private fun sortDescending(items: List<Node>): List<Node> = items.sortedByDescending { it.size }

    private fun squarify(items: List<Node>, start: Int, end: Int, bounds: Rectangle) {
        if (start > end) {
            return
        }
        if (start == end) {
            items[start].bounds = bounds
        }

        this.cursor = start

        while (cursor < end) {
            if (highestNodeAspectRatio(items, start, cursor, bounds) > highestNodeAspectRatio(items, start, cursor + 1, bounds)) {
                cursor++
            } else {
                val newBounds = layoutRow(items, start, cursor, bounds)
                squarify(items, cursor + 1, end, newBounds)
            }
        }
    }

    private fun highestNodeAspectRatio(items: List<Node>, start: Int, end: Int, bounds: Rectangle): Double {
        layoutRow(items, start, end, bounds)
        var max = java.lang.Double.MIN_VALUE
        for (i in start..end) {
            if (items[i].bounds.aspectRatio() > max) {
                max = items[i].bounds.aspectRatio()
            }
        }
        return max
    }

    private fun layoutRow(items: List<Node>, start: Int, end: Int, bounds: Rectangle): Rectangle {
        val isHorizontal = bounds.width > bounds.height
        val total = bounds.width * bounds.height //totalSize(items, 0, items.length-1);
        val rowSize = totalSize(items, start, end)
        val rowRatio = rowSize / total
        var offset = 0.0

        for (i in start..end) {
            val r = Rectangle()
            val ratio = items[i].size / rowSize

            if (isHorizontal) {
                r.x = bounds.x
                r.width = bounds.width * rowRatio
                r.y = bounds.y + bounds.height * offset
                r.height = bounds.height * ratio
            } else {
                r.x = bounds.x + bounds.width * offset
                r.width = bounds.width * ratio
                r.y = bounds.y
                r.height = bounds.height * rowRatio
            }
            items[i].bounds = r
            offset += ratio
        }
        if (isHorizontal) {
            return Rectangle(bounds.x + bounds.width * rowRatio, bounds.y, bounds.width - bounds.width * rowRatio, bounds.height)
        } else {
            return Rectangle(bounds.x, bounds.y + bounds.height * rowRatio, bounds.width, bounds.height - bounds.height * rowRatio)
        }
    }


    fun totalSize(items: List<Node>): Double {
        return totalSize(items, 0, items.size - 1)
    }

    private fun totalSize(items: List<Node>, start: Int, end: Int): Double {
        var sum = 0.0
        for (i in start..end)
            sum += items[i].size
        return sum
    }
}

