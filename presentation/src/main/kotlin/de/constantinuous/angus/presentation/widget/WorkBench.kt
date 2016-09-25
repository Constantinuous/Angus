package de.constantinuous.tessara.browser

/**
 * Created by RichardG on 08.06.2016.
 */
import javafx.collections.FXCollections
import javafx.scene.layout.GridPane
import tornadofx.*

class WorkBench : View() {
    override val root = GridPane()

    val mapTableContent = mapOf(Pair("item 1", 5), Pair("item 2", 10), Pair("item 3", 6))

    init {
        with (root) {
            row {
                vbox {
                    label("Tableview from a map")
                    tableview(FXCollections.observableArrayList<Map.Entry<String, Int>>(mapTableContent.entries)) {
                        column("Item", Map.Entry<String, Int>::key)
                        column("Count", Map.Entry<String, Int>::value)
                        resizeColumnsToFitContent()
                    }
                }
            }
        }
    }

}