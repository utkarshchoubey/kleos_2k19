package com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.models;



import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question extends Message implements Parcelable {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("question")
    @Expose
    public String question;
    @SerializedName("image")
    @Expose
    public String image;

    @SerializedName("hint1")
    @Expose
    public String hint1;
    @SerializedName("hint2")
    @Expose
    public String hint2;
    @SerializedName("hint3")
    @Expose
    public String hint3;
    @SerializedName("hint4")
    @Expose
    public String hint4;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(question);
        dest.writeString(image);
        dest.writeString(hint1);
        dest.writeString(hint2);
        dest.writeString(hint3);
        dest.writeString(hint4);
    }

    public Question(Parcel in) {
        title = in.readString();
        question = in.readString();
        image = in.readString();
        hint1 = in.readString();
        hint2 = in.readString();
        hint3 = in.readString();
        hint4 = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHint1() {
        return hint1;
    }

    public void setHint1(String hint1) {
        this.hint1 = hint1;
    }

    public String getHint2() {
        return hint2;
    }

    public void setHint2(String hint2) {
        this.hint2 = hint2;
    }

    public String getHint3() {
        return hint3;
    }

    public void setHint3(String hint3) {
        this.hint3 = hint3;
    }

    public String getHint4() {
        return hint4;
    }

    public void setHint4(String hint4) {
        this.hint4 = hint4;
    }


}