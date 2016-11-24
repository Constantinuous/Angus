package de.constantinuous.angus.parsing

/**
 * Created by RichardG on 25.09.2016.
 */
class ServerBlock(content: String, val isCommented: Boolean, row: Int, column: Int) : CodePiece(content, row, column) {
}