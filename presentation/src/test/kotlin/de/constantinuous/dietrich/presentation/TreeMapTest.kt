package de.constantinuous.dietrich.presentation

import de.constantinuous.dietrich.presentation.test.matchBoundsOf
import javafx.scene.shape.Rectangle
import io.kotlintest.specs.FeatureSpec

/**
 * Created by RichardG on 26.02.2017.
 */
class TreeMapTest : FeatureSpec() {

    init {
        feature("TreeMap should squarify single item correctly") {
            scenario("single item bounds should match square map bounds") {
                val mapBounds = Rectangle(100.0, 100.0)
                val treeMap = TreeMap()
                val items = arrayListOf(SimpleNode(100))

                treeMap.squarify(items, mapBounds)

                items[0].bounds should matchBoundsOf(mapBounds)
            }

            scenario("single item bounds should match rectangle map bounds") {
                val mapBounds = Rectangle(50.0, 70.0)
                val treeMap = TreeMap()
                val items = arrayListOf(SimpleNode(100))

                treeMap.squarify(items, mapBounds)

                items[0].bounds should matchBoundsOf(mapBounds)
            }
        }

        feature("TreeMap should squarify two items correctly") {
            scenario("item bounds should divide map bounds into two halves") {
                val mapBounds = Rectangle(100.0, 100.0)
                val treeMap = TreeMap()
                val items = arrayListOf(SimpleNode(100), SimpleNode(100))

                treeMap.squarify(items, mapBounds)

                items[0].bounds should matchBoundsOf(Rectangle(0.0, 0.0, 50.0, 2.0))
                items[1].bounds should matchBoundsOf(Rectangle(50.0, 00.0, 50.0, 2.0))
            }

            scenario("item bounds should divide map bounds into 2/3 rectangles") {
                val mapBounds = Rectangle(300.0, 300.0)
                val treeMap = TreeMap()
                val items = arrayListOf(SimpleNode(50), SimpleNode(100))

                treeMap.squarify(items, mapBounds)

                items[0].bounds should matchBoundsOf(Rectangle(200.0, 0.0, 100.0, 0.5))
                items[1].bounds should matchBoundsOf(Rectangle(0.0, 00.0, 200.0, 0.5))
            }
        }

        feature("TreeMap should squarify two items correctly") {
            scenario("item bounds should divide map bounds into three equal rectangles") {
                val mapBounds = Rectangle(300.0, 300.0)
                val treeMap = TreeMap()
                val items = arrayListOf(SimpleNode(100), SimpleNode(100), SimpleNode(100))

                treeMap.squarify(items, mapBounds)

                items[0].bounds should matchBoundsOf(Rectangle(0.0, 0.0, 100.0, 1.0))
                items[1].bounds should matchBoundsOf(Rectangle(100.0, 00.0, 100.0, 1.0))
                items[2].bounds should matchBoundsOf(Rectangle(200.0, 00.0, 100.0, 1.0))
            }

            scenario("item bounds should divide map bounds into three parts") {
                val mapBounds = Rectangle(350.0, 350.0)
                val treeMap = TreeMap()
                val items = arrayListOf(SimpleNode(50), SimpleNode(200), SimpleNode(100))

                treeMap.squarify(items, mapBounds)

                items[0].bounds should matchBoundsOf(Rectangle(300.0, 0.0, 50.0, 1.0))
                items[1].bounds should matchBoundsOf(Rectangle(0.0, 0.0, 200.0, 1.0))
                items[2].bounds should matchBoundsOf(Rectangle(200.0, 0.0, 100.0, 1.0))
            }
        }
    }


}