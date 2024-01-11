/**
 *
 * Simple yet powerful generic dao.
 * 
 *
 * <img src='doc-files/summary.dot.svg'  alt='package dependencies' >
 * 
 */
module io.github.homberghp.gensquared.dao {
    requires transitive io.github.homberghp.recordmappers;
    requires transitive io.github.homberghp.gensquared_annotations;
    requires java.logging;
    requires java.sql;
    requires java.naming;
    requires org.postgresql.jdbc;
    
//    
//    exports genericdao.dao;
//    exports genericdao.memory;
//    exports genericdao.pgdao;
}
