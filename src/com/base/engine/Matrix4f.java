package com.base.engine;

public class Matrix4f {
    private float [][] m;

    public Matrix4f(){
        m = new float[4][4];
    }

    public Matrix4f Identity(){
        m[0][0] = 1; m[0][1] = 0; m[0][2] = 0; m[0][3] = 0;
        m[1][0] = 0; m[1][1] = 1; m[1][2] = 0; m[1][3] = 0;
        m[2][0] = 0; m[2][1] = 0; m[2][2] = 1; m[2][3] = 0;
        m[3][0] = 0; m[3][1] = 0; m[3][2] = 0; m[3][3] = 1;

        return this;
    }

    public Matrix4f Translation(float x, float y, float z){
        m[0][0] = 1; m[0][1] = 0; m[0][2] = 0; m[0][3] = x;
        m[1][0] = 0; m[1][1] = 1; m[1][2] = 0; m[1][3] = y;
        m[2][0] = 0; m[2][1] = 0; m[2][2] = 1; m[2][3] = z;
        m[3][0] = 0; m[3][1] = 0; m[3][2] = 0; m[3][3] = 1;

        return this;
    }

    public Matrix4f Rotation(float x, float y, float z){
        Matrix4f rx = new Matrix4f();
        Matrix4f ry = new Matrix4f();
        Matrix4f rz = new Matrix4f();

        x = (float)Math.toRadians(x);
        y = (float)Math.toRadians(y);
        z = (float)Math.toRadians(z);

        rz.m[0][0] = (float)Math.cos(z); rz.m[0][1] = -(float)Math.sin(z); rz.m[0][2] = 0; rz.m[0][3] = 0;
        rz.m[1][0] = (float)Math.sin(z); rz.m[1][1] = (float)Math.cos(z); rz.m[1][2] = 0; rz.m[1][3] = 0;
        rz.m[2][0] = 0; rz.m[2][1] = 0; rz.m[2][2] = 1; rz.m[2][3] = 0;
        rz.m[3][0] = 0; rz.m[3][1] = 0; rz.m[3][2] = 0; rz.m[3][3] = 1;

        rx.m[0][0] = 1; rx.m[0][1] = 0; rx.m[0][2] = 0; rx.m[0][3] = 0;
        rx.m[1][0] = 0; rx.m[1][1] = (float)Math.cos(x); rx.m[1][2] = -(float)Math.sin(x); rx.m[1][3] = 0;
        rx.m[2][0] = 0; rx.m[2][1] = (float)Math.sin(x); rx.m[2][2] = (float)Math.cos(x); rx.m[2][3] = 0;
        rx.m[3][0] = 0; rx.m[3][1] = 0; rx.m[3][2] = 0; rx.m[3][3] = 1;

        ry.m[0][0] = (float)Math.cos(y); ry.m[0][1] = 0; ry.m[0][2] = -(float)Math.sin(y); ry.m[0][3] = 0;
        ry.m[1][0] = 0; ry.m[1][1] = 1; ry.m[1][2] = 0; ry.m[1][3] = 0;
        ry.m[2][0] = (float)Math.sin(y); ry.m[2][1] = 0; ry.m[2][2] = (float)Math.cos(y); ry.m[2][3] = 0;
        ry.m[3][0] = 0; ry.m[3][1] = 0; ry.m[3][2] = 0; ry.m[3][3] = 1;

        m = rz.mul(ry.mul(rx)).getM();

        return this;
    }

    public Matrix4f mul(Matrix4f right){
        Matrix4f result = new Matrix4f();

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                result.set(i, j, m[i][0] * right.get(0, j) +
                                        m[i][1] * right.get(1, j) +
                                        m[i][2] * right.get(2, j) +
                                        m[i][3] * right.get(3, j));
            }
        }

        return result;
    }

    public float get(int x, int y){
        return m[x][y];
    }

    public void set(int x, int y, float value){
        m[x][y] = value;
    }

    public float[][] getM() {
        return m;
    }

    public void setM(float[][] m) {
        this.m = m;
    }

}
