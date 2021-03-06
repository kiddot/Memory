package com.android.memory.bean;

import cn.bmob.v3.BmobUser;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "_User".
 */
public class _User extends BmobUser{

    private Long id;
    /** Not-null value. */
//    private String username;
//
//    private String password;

            private String pwHelp;
     
    private String sex;
     
    private String birthday;
     
    private String avatar;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public _User() {
    }

    public _User(Long id) {
        this.id = id;
    }

    public _User(Long id, String username, String password, String sex, String birthday, String avatar) {
        this.id = id;
        setUsername(username);
        setPassword(password);
        pwHelp = password;
        this.sex = sex;
        this.birthday = birthday;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
//    public String getUsername() {
//        return username;
//    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
//    public void setUsername(String username) {
//        this.username = username;
//    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword(){
        return pwHelp;
    }

    public void setPassword(String password){
        super.setPassword(password);
        pwHelp = password ;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
