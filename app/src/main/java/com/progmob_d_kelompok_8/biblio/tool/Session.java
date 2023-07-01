package com.progmob_d_kelompok_8.biblio.tool;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx){
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedin(boolean logggedin){
        editor.putBoolean("loggedInmode", logggedin);
        editor.commit();
    }

    public boolean loggedin(){
        return prefs.getBoolean("loggedInmode", false);
    }

    public void setUserId(int usrId){
        editor.putInt("userIdSession", usrId);
        editor.commit();
    }

    public int getUserId(){
        return prefs.getInt("userIdSession", 0);
    }

    public void setUserAdmin(boolean isAdmin){
        editor.putBoolean("userRoleSession", isAdmin);
        editor.commit();
    }

    public boolean isUserAdmin(){
        return prefs.getBoolean("userRoleSession", false);
    }

    public boolean isFromAdminProfileFragment(){
        return prefs.getBoolean("fromAdminProfileFragmentSession", false);
    }

    public void setFromAdminProfileFragment(boolean isAdminProfileFragment){
        editor.putBoolean("fromAdminProfileFragmentSession", isAdminProfileFragment);
        editor.commit();
    }

    public boolean isFromUserProfileFragment(){
        return prefs.getBoolean("fromUserProfileFragmentSession", false);
    }

    public void setFromUserProfileFragment(boolean isUserProfileFragment){
        editor.putBoolean("fromUserProfileFragmentSession", isUserProfileFragment);
        editor.commit();
    }

    public boolean isListed(){
        return prefs.getBoolean("listedBook", false);
    }

    public void setListed(boolean isListedBook){
        editor.putBoolean("listedBook", isListedBook);
        editor.commit();
    }

    public void setBookIdDetail(int bookId){
        editor.putInt("bookIdDetailSession", bookId);
        editor.commit();
    }

    public int getBookIdDetail(){
        return prefs.getInt("bookIdDetailSession", 0);
    }

    public void setUserIdDetail(int userId){
        editor.putInt("userIdDetailSession", userId);
        editor.commit();
    }

    public int getUserIdDetail(){
        return prefs.getInt("userIdDetailSession", 0);
    }

    public void setBookTitle(String bookTitle){
        editor.putString("bookTitle", bookTitle);
        editor.commit();
    }

    public String getBookTitle(){
        return prefs.getString("bookTitle", "DEFAULT");
    }
}
