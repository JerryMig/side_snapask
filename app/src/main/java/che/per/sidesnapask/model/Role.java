package che.per.sidesnapask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jerry on 2018/1/3.
 */

public class Role implements Parcelable {

    public static final String TUTOR = "Tutor";
    public static final String STUDENT = "Student";
    public static final String SCHOOLTEACHER = "SchoolTeacher";
    private static final String ADMIN = "Admin";

    public static final int ROLE_STUDENT = 0;
    public static final int ROLE_TUTOR = 1;
    public static final int ROLE_SCHOOL_TEACHER = 2;
    public static final int ROLE_UNDEFINED = -1;
    public static final int ROLE_ADMIN = 3;

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    protected Role(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
    }

    public static final Creator<Role> CREATOR = new Creator<Role>() {
        @Override
        public Role createFromParcel(Parcel in) {
            return new Role(in);
        }

        @Override
        public Role[] newArray(int size) {
            return new Role[size];
        }
    };

    public String getName() {
        return mName;
    }

    public int getTransferedId() {
        if (mName.equalsIgnoreCase(TUTOR)) {
            return ROLE_TUTOR;
        } else if (mName.equalsIgnoreCase(STUDENT)) {
            return ROLE_STUDENT;
        } else if (mName.equalsIgnoreCase(SCHOOLTEACHER)) {
            return ROLE_SCHOOL_TEACHER;
        } else if (mName.equalsIgnoreCase(ADMIN)) {
            return ROLE_ADMIN;
        } else {
            return ROLE_UNDEFINED;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
    }

}
