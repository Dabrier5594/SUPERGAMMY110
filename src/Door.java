public class Door {
    private boolean isOpen;
    private Hub hubA;
    private Hub hubB;

    public Door(Hub hubA, Hub hubB, boolean isOpen) {
        this.hubA = hubA;
        this.hubB = hubB;
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    public Hub getOtherSide(Hub current) {
        if (current == hubA) return hubB;
        if (current == hubB) return hubA;
        return null;
    }
}