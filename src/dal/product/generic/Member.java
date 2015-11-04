package dal.product.generic;

import java.util.Date;

public abstract class Member 
{
    private int number;

    private Date joiningDate;

    Date getJoiningDate() {
        return this.joiningDate;
    }

    void setJoiningDate(Date value) {
        this.joiningDate = value;
    }

    int getNumber() {
        return this.number;
    }

    void setNumber(int value) {
        this.number = value;
    }

	public abstract int getMemberId(String login);
	public abstract String getLogin(int seller);
	public abstract int getSeller(int userId);
	public abstract int save(int userId, String date);

}
