package com.example.laborator_45;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Bormasina implements Parcelable {
    private String nume;
    private Boolean folosita;
    private Boolean nou;
    private int gradUzare;
    private Float rating;

    public Bormasina(String nume,Boolean folosita,Boolean nou,int gradUzare,Float rating){
        this.nume=nume;
        this.folosita=folosita;
        this.nou=nou;
        this.gradUzare=gradUzare;
        this.rating=rating;
    }

    protected Bormasina(Parcel in) {
        nume = in.readString();
        byte tmpFolosita = in.readByte();
        folosita = tmpFolosita == 0 ? null : tmpFolosita == 1;
        byte tmpNou = in.readByte();
        nou = tmpNou == 0 ? null : tmpNou == 1;
        gradUzare = in.readInt();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readFloat();
        }
    }

    public static final Creator<Bormasina> CREATOR = new Creator<Bormasina>() {
        @Override
        public Bormasina createFromParcel(Parcel in) {
            return new Bormasina(in);
        }

        @Override
        public Bormasina[] newArray(int size) {
            return new Bormasina[size];
        }
    };

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public int getGradUzare() {
        return gradUzare;
    }

    public void setGradUzare(int gradUzare) {
        this.gradUzare = gradUzare;
    }

    public Boolean getNou() {
        return nou;
    }

    public void setNou(Boolean nou) {
        this.nou = nou;
    }

    public Boolean getFolosita() {
        return folosita;
    }

    public void setFolosita(Boolean folosita) {
        this.folosita = folosita;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeByte((byte) (folosita == null ? 0 : folosita ? 1 : 2));
        dest.writeByte((byte) (nou == null ? 0 : nou ? 1 : 2));
        dest.writeInt(gradUzare);
        if (rating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(rating);
        }
    }
    @Override
    public String toString() {
        return "Nume: " + nume +
                ", Folosita: " + (folosita ? "Da" : "Nu") +
                ", Stare: " + (nou ? "Noua" : "Veche") +
                ", Grad uzare: " + gradUzare +
                ", Rating: " + rating + "/5";
    }
}
