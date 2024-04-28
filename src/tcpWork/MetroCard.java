package tcpWork;

public class MetroCard {
    private String serNum;
    private User usr;
    private String establishment;
    private double balance;

    public MetroCard() {
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public String getEstablishment() {
        return establishment;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public MetroCard(String serNum, User usr, String establishment) {
        this.serNum = serNum;
        this.usr = usr;
        this.establishment = establishment;
        this.balance = 0.0;
    }

    @Override
    public String toString() {
        return "MetroCard{" +
                "serNum='" + serNum + '\'' +
                ", usr=" + usr +
                ", establishment='" + establishment + '\'' +
                ", balance=" + balance +
                '}';
    }

}
