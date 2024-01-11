package io.github.homberghp.gensquared.pgdao;

import io.github.homberghp.gensquared.dao.DAO;
import io.github.homberghp.recordmappers.RecordMapper;
import io.github.homberghp.recordmappers.RecordMapper.EditHelper;
import static java.lang.String.format;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * Class to compute and cache query strings.
 *
 * The queries are to be computed from the meta data on the java entity type in
 * the mapper. After the first invocation of on of the non private method, the
 * same should be returned on each call.
 *
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class QueryFactory {

    final RecordMapper<?, ?> mapper;

    ConcurrentMap<String, String> queryTextCache = new ConcurrentHashMap<>();

    public QueryFactory(RecordMapper<?, ?> mapper) {
        this.mapper = mapper;
    }

    /**
     * Returned a value from the cache.
     *
     * @return
     */
    String allColumns() {
        return this.queryTextCache
                .computeIfAbsent( "allColumns", x -> computeAllColumns() );
    }

    /**
     * Compute the value of all columns joins with a comma.
     *
     * @return text
     */
    private String computeAllColumns() {
        //Start Solution::replacewith:://TODO
        return mapper.editHelpers().stream().map( EditHelper::fieldName )
                .collect( joining( "," ) );
        //End Solution::replacewith::return "";
    }

    /**
     * The name of the key column.
     *
     * @return the name of the key column
     */
    String idName() {
        return this.queryTextCache
                .computeIfAbsent( "idName", x -> mapper.keyName() );
    }

    String getQueryText() {
        return queryTextCache
                .computeIfAbsent( "selectsingle", ( x )
                        -> computeGetQueryText() );
    }

    /**
     * Compute the select query.
     *
     * @return the text
     */
    private String computeGetQueryText() {
        //Start Solution::replacewith:://TODO
        return String
                .format( "select %s from %s where %s=?",
                        allColumns(),
                        tableName(),
                        idName() );
        //End Solution::replacewith::return "";
    }

    /**
     * Get the table name fom the @TableName annotation.
     *
     * @return the table name or a synthesized name using simple plural.
     */
    String tableName() {
        return DAO.tableName( mapper.entityType() );
    }

    /**
     * Get the delete query form the cache.
     *
     * @return the text
     */
    String deleteQueryText() {
        return queryTextCache
                .computeIfAbsent( "delete", x -> computeDeleteQueryText() );
    }

    private String computeDeleteQueryText() {
        //Start Solution::replacewith:://TODO
        return format( "delete from %s where %s=?", tableName(),
                mapper.keyName() );
        //End Solution::replacewith::return "";
    }

    String updateQueryText() {
        return queryTextCache
                .computeIfAbsent( "update", x -> computeUpdateQueryText()
                );
    }

    private String computeUpdateQueryText() {
        //Start Solution::replacewith:://TODO
        var columnNames = mapper.editHelpers()
                .stream()
                .map( EditHelper::fieldName )
                .collect( toList() );
        String columns = String
                .join( ",", columnNames );
        String placeholders = makePlaceHolders( columnNames.size() );
        String sqlt = format(
                "update %1$s set (%2$s)=(%3$s) where (%4$s)=(?)"
                + " returning  %2$s",
                tableName(),
                columns,
                placeholders,
                mapper.keyName() );
        return sqlt;
        //End Solution::replacewith::return "";
    }

    final String saveQueryText() {
        return queryTextCache
                .computeIfAbsent( "save", x -> computeSaveQueryText() );
    }

    final String makePlaceHolders(final int count) {
        String[] qm = new String[ count ];
        Arrays.fill( qm, "?" );
        return String.join( ",", qm );
    }

    private String computeSaveQueryText() {
        //Start Solution::replacewith:://TODO
        List<String> fields = mapper.editHelpers().stream()
                .map( EditHelper::fieldName )
                .filter( s -> !mapper.generatedFieldNames().contains( s ) )
                .collect( toList() );
        String placeHolders = makePlaceHolders( fields.size() );
        String saveColumnNames = fields.stream().collect( joining( "," ) );
        return String.format(
                "insert into %1$s (%2$s) %n"
                + "values(%3$s) %n"
                + "returning %4$s",
                tableName(),
                saveColumnNames,
                placeHolders,
                allColumns()
        );
        //End Solution::replacewith::return "";
    }

    String allQuery() {
        return queryTextCache
                .computeIfAbsent( "all", x -> computeAllQueryText() );
    }

    private String computeAllQueryText() {
        //Start Solution::replacewith:://TODO
        return format( "select %s from %s ", allColumns(), tableName() );
        //End Solution::replacewith::return "";
    }

    String lastIdQuery() {
        return queryTextCache
                .computeIfAbsent( "lastIdQuery", x -> computeLastIdQuery() );
    }

    private String computeLastIdQuery() {
        //Start Solution::replacewith:://TODO
        return format( "select max(%s)  as lastid from %s", idName(),
                tableName() );
        //End Solution::replacewith::return "";
    }

    String dropallQuery() {
        return queryTextCache
                .computeIfAbsent( "dropAllQuery", x -> computeDropallQuery() );

    }

    String computeDropallQuery() {
        return "truncate " + tableName() + " restart identity cascade";
    }
}
