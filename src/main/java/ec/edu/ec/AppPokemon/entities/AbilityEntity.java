package ec.edu.ec.AppPokemon.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ability")
public class AbilityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_hidden")
    private boolean isHidden;

    private int slot;

    @Embedded
    private AbilityDetail ability;

    public AbilityEntity() {
    }

    public AbilityEntity(boolean isHidden, int slot, AbilityDetail ability) {
        this.isHidden = isHidden;
        this.slot = slot;
        this.ability = ability;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public AbilityDetail getAbility() {
        return ability;
    }

    public void setAbility(AbilityDetail ability) {
        this.ability = ability;
    }

    @Override
    public String toString() {
        return
                "Habilidades = " + ability.toString() + "\n" +
                "Está oculto =" + isHidden + "\n" ;
    }
}
