package proyecto_so2;

public class Plato {
    private int comida;

    public Plato() {
        this.comida = (int) (Math.random() * 10) + 1;
    }

    public boolean comerBocado() {
        if (comida > 0) {
            comida--;
            return true;
        }
        return false;
    }

    public int getComida() {
        return comida;
    }
}
