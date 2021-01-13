package tools;

public class OnScreenLocation {
    
    private int X;
    private int Y;

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
    
    public OnScreenLocation(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OnScreenLocation other = (OnScreenLocation) obj;
        if (this.X != other.X) {
            return false;
        }
        if (this.Y != other.Y) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.X;
        hash = 53 * hash + this.Y;
        return hash;
    }
}
