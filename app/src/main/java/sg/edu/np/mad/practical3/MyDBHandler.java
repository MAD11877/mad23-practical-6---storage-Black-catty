package sg.edu.np.mad.practical3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    public static final String TABLE_USERS = "User";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_DESCRIPTION= "Description";
    public static final String COLUMN_FOLLOWED="Followed";
    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory,
                       int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_USER_TABLE= "CREATE TABLE " +
                TABLE_USERS +
                "(" + COLUMN_USERNAME
                + " TEXT," + COLUMN_DESCRIPTION
                + " TEXT," + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
             + COLUMN_FOLLOWED + " INTEGER" + ")";
        db.execSQL(CREATE_USER_TABLE);



    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);


    }
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_USERNAME);
            int descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
            int followedIndex = cursor.getColumnIndex(COLUMN_FOLLOWED);

            int id = cursor.getInt(idIndex);
            String name = cursor.getString(nameIndex);
            String description = cursor.getString(descriptionIndex);
            int followedValue = cursor.getInt(followedIndex);
            boolean isFollowed = followedValue == 1;

            User user = new User(name, description, id, isFollowed);
            userList.add(user);
        }

        cursor.close();
        db.close();

        return userList;
    }
    public void addUser(User user)

    {   ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_ID, user.getId());
        int followedvalue=user.getFollowed()? 1:0;
        values.put(COLUMN_FOLLOWED,followedvalue);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.isOpen();

    }
    public void updateUser(User user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_ID,user.getId());
        values.put(COLUMN_FOLLOWED, user.getFollowed() ? 1 : 0);

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(user.getId())};

        db.update(TABLE_USERS, values, whereClause, whereArgs);
        int rowsAffected = db.update(TABLE_USERS, values, whereClause, whereArgs);
        Log.d("MyDBHandler", "Rows affected: " + rowsAffected);

        db.close();
    }

    private int generatedOTP() {
        Random ran = new Random();
        int myNumber = ran.nextInt(999999999);
        return myNumber;
    }
    private boolean generateBool() {
        Random rand = new Random();
        boolean Truth = rand.nextBoolean();
        return Truth;
    }
}
