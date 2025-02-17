package ec.edu.ec.AppPokemon.entities;

public class Stat {
    private int baseStat;
    private int effort;
    private StatDetail stat;

    // Getters y setters
    public int getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(int baseStat) {
        this.baseStat = baseStat;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public StatDetail getStat() {
        return stat;
    }

    public void setStat(StatDetail stat) {
        this.stat = stat;
    }
}
