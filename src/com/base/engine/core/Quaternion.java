package com.base.engine.core;

public class Quaternion {

    private float x;
    private float y;
    private float z;
    private float w;

    public Quaternion(){
        this(0,0,0,1);
    }

    public Quaternion(float x, float y, float z, float w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Quaternion initRotation(Vector3f axis, float angle){
        float sinHalfAngle = (float)Math.sin(angle / 2);
        float cosHalfAngle = (float)Math.cos(angle / 2);

        this.x = axis.getX() * sinHalfAngle;
        this.y = axis.getY() * sinHalfAngle;
        this.z = axis.getZ() * sinHalfAngle;
        this.w = cosHalfAngle;

        return this;
    }

    public float length(){
        return (float)Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Quaternion normalize(){
        float length = length();

        return new Quaternion(x/length, y/length, z/length, w/length);
    }

    public Quaternion conjugate(){
        return new Quaternion(-x, -y, -z, w);
    }

    public Quaternion mul(Quaternion right){
        float w_ = w * right.getW() - x * right.getX() - y * right.getY() - z * right.getZ();
        float x_ = x * right.getW() + w * right.getX() + y * right.getZ() - z * right.getY();
        float y_ = y * right.getW() + w * right.getY() + z * right.getX() - x * right.getZ();
        float z_ = z * right.getW() + w * right.getZ() + x + right.getY() - y * right.getX();

        return new Quaternion(x_, y_, z_, w_);
    }

    public Quaternion mul(Vector3f right){
        float w_ = -x * right.getX() - y * right.getY() - z * right.getZ();
        float x_ =  w * right.getX() + y * right.getZ() - z * right.getY();
        float y_ =  w * right.getY() + z * right.getX() - x * right.getZ();
        float z_ =  w * right.getZ() + x * right.getY() - y * right.getX();

        return new Quaternion(x_, y_, z_, w_);
    }

    public Matrix4f toRotationMatrix(){
        Vector3f forward =  new Vector3f(2.0f * (x * z - w * y), 2.0f * (y * z + w * x), 1.0f - 2.0f * (x * x + y * y));
        Vector3f up = new Vector3f(2.0f * (x * y + w * z), 1.0f - 2.0f * (x * x + z * z), 2.0f * (y * z - w * x));
        Vector3f right = new Vector3f(1.0f - 2.0f * (y * y + z * z), 2.0f * (x * y - w * z), 2.0f * (x * z + w * y));

        return new Matrix4f().initRotation(forward, up, right);
    }



    public Quaternion Mul(float r)
    {
        return new Quaternion(x * r, y * r, z * r, w * r);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public Vector3f getForward()
    {
        return new Vector3f(0,0,1).rotate(this);
    }

    public Vector3f getBack()
    {
        return new Vector3f(0,0,-1).rotate(this);
    }

    public Vector3f getUp()
    {
        return new Vector3f(0,1,0).rotate(this);
    }

    public Vector3f getDown()
    {
        return new Vector3f(0,-1,0).rotate(this);
    }

    public Vector3f getRight()
    {
        return new Vector3f(1,0,0).rotate(this);
    }

    public Vector3f getLeft()
    {
        return new Vector3f(-1,0,0).rotate(this);
    }

    public Quaternion set(float x, float y, float z, float w) { this.x = x; this.y = y; this.z = z; this.w = w; return this; }
    public Quaternion set(Quaternion r) { set(r.getX(), r.getY(), r.getZ(), r.getW()); return this; }


    public String toString(){
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }

}
