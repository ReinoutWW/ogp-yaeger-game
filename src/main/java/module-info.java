module ogp.yaeher.game {
    requires hanyaeger;
    requires org.checkerframework.checker.qual;
    requires java.desktop;

    opens audio;
    opens backgrounds;
    opens sprites;
    opens sprites.characters;
    opens sprites.weapons;
    opens sprites.powerups;

    exports brawlhalla;
}
