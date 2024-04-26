module ogp.yaeher.game {
    requires hanyaeger;
    requires org.checkerframework.checker.qual;

    opens audio;
    opens backgrounds;
    opens sprites;
    opens sprites.characters;
    opens sprites.weapons;

    exports brawlhalla;
}
