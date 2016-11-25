package de.constantinuous.angus.sql.impl

/**
 * Created by RichardG on 25.11.2016.
 */
fun tsqlParser.Select_statementContext.getTableName(): String {
    val tableSource = this.query_expression().query_specification().table_sources().table_source(0)
    val table = tableSource.table_source_item_joined().table_source_item().table_name_with_hint().table_name().table
    val tableName = table.simple_id().getChild(0).text ?: ""

    return tableName
}

fun tsqlParser.Select_statementContext.getSelectedColumnNames(): List<String> {
    val selectedColumnNames = mutableListOf<String>()

    val selectList = this.query_expression().query_specification().select_list().select_list_elem()
    for(selectListElement in selectList){
        val columnName = selectListElement.expression().getChild(0).text
        selectedColumnNames.add(columnName)
    }

    return selectedColumnNames
}

fun tsqlParser.Delete_statementContext.getTableName(): String{
    val tableName = this.delete_statement_from().table_alias().id().simple_id().getChild(0).text ?: ""

    return tableName
}

fun tsqlParser.Insert_statementContext.getTableName(): String{
    val tableName = ""

    return tableName
}