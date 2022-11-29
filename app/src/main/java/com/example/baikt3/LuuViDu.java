package com.example.baikt3;

public class LuuViDu {
    int loai,s1;
    double kq;
    String loaichu;

    public LuuViDu(int loai,String loaichu, int s1, double kq) {
        this.loai = loai;
        this.s1 = s1;
        this.kq=kq;
        this.loaichu=loaichu;
    }

    @Override
    public String toString() {
        if (loai!=4 && loai!=6)
            return " " + s1+" "+loaichu+"= "+kq;
        else if (loai ==4)
            return "(" + s1+") = "+kq;
        else
            return " " + s1+" = "+kq;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public int getS1() {
        return s1;
    }

    public void setS1(int s1) {
        this.s1 = s1;
    }

}
