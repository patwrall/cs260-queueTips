package M1_OOP;

import java.util.Random;

public class Position3D implements java.io.Serializable, Cloneable, Comparable<Position3D> {

    private double x;
    private double y;
    private double z;
    public static final Position3D origin = new Position3D(0, 0, 0);

    public Position3D() {
        Random rand = new Random();
        this.x = rand.nextDouble(100);
        this.y = rand.nextDouble(100);
        this.z = rand.nextDouble(100);
    }

    public Position3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public Position3D clone() throws CloneNotSupportedException {
        return (Position3D) super.clone();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public final double distanceTo(Position3D other) {
        if (other == null) {
            throw new NullPointerException("Other cannot be null in 'public final double distanceTo(Position3D other)'");
        }

        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = this.z - other.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    @Override
    public String toString() {
        return String.format("(x=%.2f, y=%.2f, z=%.2f)", x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Position3D pos = (Position3D) o;

        return this.x == pos.x && this.y == pos.y && this.z == pos.z;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y, z);
    }

    @Override
    public int compareTo(Position3D o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            throw new NullPointerException("O cannot be null in 'public int compareTo(Position3D o)'");
        }
        return Double.compare(this.distanceTo(origin), o.distanceTo(origin));
    }
}
