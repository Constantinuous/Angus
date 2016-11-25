package de.constantinuous.angus.sql.impl

import tsqlParser
import tsqlBaseListener

/**
 * Created by RichardG on 25.11.2016.
 */
class AntlrTsqlListener : tsqlBaseListener() {

    override fun exitSelect_statement(ctx: tsqlParser.Select_statementContext){
        val tableName = ctx.getTableName()
        val selectedColumnNames = ctx.getSelectedColumnNames()
    }

    override fun exitDelete_statement(ctx: tsqlParser.Delete_statementContext) {
        val tableName = ctx.getTableName()
    }

    override fun exitDelete_statement_from(ctx: tsqlParser.Delete_statement_fromContext) {

    }

    override fun exitInsert_statement(ctx: tsqlParser.Insert_statementContext) {
        val tableName = ctx.getTableName()
    }

    override fun exitInsert_statement_value(ctx: tsqlParser.Insert_statement_valueContext) {
    }

    override fun exitUpdate_statement(ctx: tsqlParser.Update_statementContext) {
    }

    override fun exitExecute_statement(ctx: tsqlParser.Execute_statementContext) {
    }

    override fun exitFunction_call_expression(ctx: tsqlParser.Function_call_expressionContext) {
    }

    override fun exitFunction_call(ctx: tsqlParser.Function_callContext) {
    }

}