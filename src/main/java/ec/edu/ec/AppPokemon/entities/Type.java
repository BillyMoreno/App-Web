package ec.edu.ec.AppPokemon.entities;

public class Type {
    private int slot;
    private TypeDetail type;

    // Getters y setters
    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public TypeDetail getType() {
        return type;
    }

    public void setType(TypeDetail type) {
        this.type = type;
    }
}
