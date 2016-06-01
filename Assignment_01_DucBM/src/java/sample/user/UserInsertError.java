/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.user;

/**
 *
 * @author Chuot Bach
 */
public class UserInsertError {
    private String UsernameLengthErr;
    private String PasswordLengthErr;
    private String FullNameLengthErr;
    private String ConfirmErr;
    private String DuplicatedErr;

    /**
     * @return the UsernameLengthErr
     */
    public String getUsernameLengthErr() {
        return UsernameLengthErr;
    }

    /**
     * @param UsernameLengthErr the UsernameLengthErr to set
     */
    public void setUsernameLengthErr(String UsernameLengthErr) {
        this.UsernameLengthErr = UsernameLengthErr;
    }

    /**
     * @return the PasswordLengthErr
     */
    public String getPasswordLengthErr() {
        return PasswordLengthErr;
    }

    /**
     * @param PasswordLengthErr the PasswordLengthErr to set
     */
    public void setPasswordLengthErr(String PasswordLengthErr) {
        this.PasswordLengthErr = PasswordLengthErr;
    }

    /**
     * @return the FullNameLengthErr
     */
    public String getFullNameLengthErr() {
        return FullNameLengthErr;
    }

    /**
     * @param FullNameLengthErr the FullNameLengthErr to set
     */
    public void setFullNameLengthErr(String FullNameLengthErr) {
        this.FullNameLengthErr = FullNameLengthErr;
    }

    /**
     * @return the ConfirmErr
     */
    public String getConfirmErr() {
        return ConfirmErr;
    }

    /**
     * @param ConfirmErr the ConfirmErr to set
     */
    public void setConfirmErr(String ConfirmErr) {
        this.ConfirmErr = ConfirmErr;
    }

    /**
     * @return the DuplicatedErr
     */
    public String getDuplicatedErr() {
        return DuplicatedErr;
    }

    /**
     * @param DuplicatedErr the DuplicatedErr to set
     */
    public void setDuplicatedErr(String DuplicatedErr) {
        this.DuplicatedErr = DuplicatedErr;
    }
    
}
