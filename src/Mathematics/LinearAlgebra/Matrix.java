package Mathematics.LinearAlgebra;

public class Matrix {

    private final double[][] grid;

    public Matrix(final double[][] grid) {
        this.grid = grid;
    }

    public Matrix(final int i, final int j) {
        grid = new double[i][j];
    }

    public Matrix(final int[] size) {
        grid = new double[size[0]][size[1]];
    }

    public Matrix(final Vector vector) {
        double[] vectorCoords = vector.getCoordinates();
        int dims = vector.getDimensions();

        grid = new double[dims][1];
        for (int i = 0; i < dims; i++) {
            grid[i][0] = vectorCoords[i];
        }
    }

    public double get(final int i, final int j) {
        return grid[i][j];
    }

    public void set(final int i, final int j, final double value) {
        grid[i][j] = value;
    }

    public Matrix multiply(Matrix otherMatrix) {
        int[] size = getSize(); int[] otherSize = otherMatrix.getSize();
        assert size[1] == otherSize[0];

        Matrix resultMatrix = new Matrix(size[0], otherSize[1]);

        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < otherSize[1]; j++) {
                double tmpValue = 0;
                for (int k = 0; k < size[1]; k++) {
                    tmpValue += get(i, k) * otherMatrix.get(k, j);
                }
                resultMatrix.set(i, j, tmpValue);
            }
        }

        return resultMatrix;
    }

    public void add(Matrix otherMatrix) {
        assert (otherMatrix.grid.length == grid.length) && (otherMatrix.grid[0].length == grid[0].length);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] += otherMatrix.grid[i][j];
            }
        }
    }

    public Matrix getAdded(Matrix otherMatrix) {
        Matrix copyOfMatrix = (Matrix) clone();
        copyOfMatrix.add(otherMatrix);
        return copyOfMatrix;
    }

    public void add(double v) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] += v;
            }
        }
    }

    public Matrix getAdded(double v) {
        Matrix copyOfMatrix = (Matrix) clone();
        copyOfMatrix.add(v);
        return copyOfMatrix;
    }

    public void elementMultiply(Matrix otherMatrix) {
        assert (otherMatrix.grid.length == grid.length) && (otherMatrix.grid[0].length == grid[0].length);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] *= otherMatrix.grid[i][j];
            }
        }
    }

    public Matrix getElementMultiplied(Matrix otherMatrix) {
        Matrix copyOfMatrix = (Matrix) clone();
        copyOfMatrix.elementMultiply(otherMatrix);
        return copyOfMatrix;
    }

    public void exponentScale(double exp) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = Math.pow(grid[i][j], exp);
            }
        }
    }

    public Matrix getExponentScaled(double exp) {
        Matrix copyOfMatrix = (Matrix) clone();
        copyOfMatrix.exponentScale(exp);
        return copyOfMatrix;
    }

    public void scale(double factor) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] *= factor;
            }
        }
    }

    public Matrix getScaled(double factor) {
        Matrix copyOfMatrix = (Matrix) clone();
        copyOfMatrix.scale(factor);
        return copyOfMatrix;
    }

    public Vector multiply(Vector otherVector) {
        Matrix otherMatrix = new Matrix(otherVector);
        Matrix result = multiply(otherMatrix);
        return matrixToVector(result);
    }

    public Matrix getTransposed() {
        double[][] newGrid = new double[grid[0].length][grid.length];
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                newGrid[j][i] = grid[i][j];
        return new Matrix(newGrid);
    }

    private static Vector matrixToVector(Matrix matrix) {
        int[] size = matrix.getSize();
        assert size[1] == 1;

        double[] coords = new double[size[0]];
        for (int i = 0; i < size[0]; i++) {
            coords[i] = matrix.get(i, 0);
        }

        return new Vector(coords);
    }

    public double[][] getGrid() {
        return grid;
    }

    public int[] getSize() {
        return new int[] {grid.length, grid[0].length};
    }

    @Override
    public Object clone() {
        int numRows = grid.length; int numCols = grid[0].length;
        double[][] newGrid = new double[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            newGrid[i] = grid[i].clone();
        }
        return new Matrix(newGrid);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (double[] doubles : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                str.append(String.format("%5f", doubles[j])).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Matrix)) return false;
        if(getSize()[0]!=((Matrix) other).getSize()[0]||getSize()[1]!=((Matrix) other).getSize()[1]) return false;
        for(int i=0; i<getSize()[0];i++){
            for(int j=0;j<getSize()[1];j++){
                if(grid[i][j]!=((Matrix) other).get(i, j)) return false;
            }
        }
        return true;
    }
}
