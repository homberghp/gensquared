module sampleentities {
    exports sampleentities;
    requires transitive io.github.homberghp.gensquared_annotations;
    requires  io.github.homberghp.recordmappers;
    opens sampleentities to io.github.homberghp.recordmappers;
}
