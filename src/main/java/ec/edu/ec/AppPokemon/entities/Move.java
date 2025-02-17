package ec.edu.ec.AppPokemon.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Move {
    private MoveDetail move;

    @JsonProperty("version_group_details")
    private Object versionGroupDetails;

    // Getters y setters
    public MoveDetail getMove() {
        return move;
    }

    public void setMove(MoveDetail move) {
        this.move = move;
    }

    public Object getVersionGroupDetails() {
        return versionGroupDetails;
    }

    public void setVersionGroupDetails(Object versionGroupDetails) {
        this.versionGroupDetails = versionGroupDetails;
    }
}
