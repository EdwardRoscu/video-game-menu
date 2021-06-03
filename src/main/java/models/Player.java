package models;

import org.dizitart.no2.objects.Id;

import java.util.UUID;

@SuppressWarnings("unused")
public class Player {

    @Id
    private UUID id = UUID.randomUUID();
    private String name;
    private boolean current;
    private Save[] saves = new Save[3];

    public Player() {}

    public Player(String name) { this.name = name; }

    public Player(String name, UUID id) {
        this.name = name;
        this.id = id;
    }

    public Player(String name, boolean current, Save[] saves, UUID id) {
        this.name = name;
        this.current = current;
        this.saves = saves;
        this.id = id;
    }

    public UUID rand_UUID() {
        this.id = UUID.randomUUID();
        return this.id;
    }

    public void addSave(Save save, int index) {
        saves[index] = save;
    }

    public String toString() {
        return name + " " + current;
    }




    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public Save[] getSaves() {
        return saves;
    }

    public void setSaves(Save[] saves) {
        this.saves = saves;
    }

}
