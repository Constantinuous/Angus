package de.constantinuous.angus.sql.impl

import org.antlr.v4.runtime.RuleContext

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
    val tableName = this.ddl_object().full_table_name().getChild(0).text

    return tableName
}

fun tsqlParser.Insert_statementContext.getColumnNames(): List<String>{
    val columnNames = mutableListOf<String>()

    val columnNameList = this.column_name_list()
    if(columnNameList != null){
        for(id in columnNameList.id()){
            columnNames.add(id.simple_id().getChild(0).text)
        }
    }

    return columnNames
}

fun tsqlParser.Execute_statementContext.getProcedureNames(): List<String>{
    val procedureNames = mutableListOf<String>()
    val procedures = this.func_proc_name().id()
    for(procedure in procedures){
        procedureNames.add(procedure.simple_id().text)
    }
    return procedureNames
}
