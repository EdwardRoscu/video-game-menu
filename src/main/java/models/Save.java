package models;

@SuppressWarnings("unused")
public class Save {

    private final String chapter;
    private final Hero hero;

    public Save(String chapter, Hero hero) {
        this.chapter = chapter;
        this.hero = hero;
    }

    public String getChapter() {
        return chapter;
    }

    public Hero getHero() {
        return hero;
    }
    
}
