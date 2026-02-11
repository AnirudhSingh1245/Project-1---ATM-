
import java.util.Scanner;
import java.sql.*;
class Account{
    private String id;
    private String name;
    private int balance=0;
    Account(String id,String name){
        this.id=id;
        this.name=name;
    }
    Account(String id,String name,int balance){
        this.id=id;
        this.name=name;
        this.balance=balance;
    }
    String getId(){
        return id;
    }
    String getName(){
        return name;
    }
    int getBalance(){
        return balance;
    }
    int credit(int amount){
        balance=balance+amount;
        return balance;
    }
    int debit(int amount){
        if(amount<=balance){
            balance=balance-amount;
        }
        else{
            System.out.println("Amount Exdeede balance");
        }
        return balance;
    }
    int transferTo(Account another,int amount){
        if(amount<=balance){
            another.credit(amount);
        }
        else{
            System.out.println("Amount Exceeded balance");
        }
        return balance;
    }
    String tostring(){
        String a="Account[id="+id+",name="+name+",balance="+balance+"]";
        return a;
    }
}
public class PROJECT1 {
    public static void main(String args[]){
        try {
            Scanner aa = new Scanner(System.in);
            int choice0;
            while (true) {
                String url="jdbc:mysql://localhost:3306/";
                String username="root";
                String password="happy";
                String databasename="Bank";
                String tablename="Account";
                Connection c=DriverManager.getConnection(url,username,password);
                Statement s=c.createStatement();
                s.executeUpdate("use "+databasename);

                System.out.println("--ATM--" +
                        "\n\n Type -1 if account is already exist" +
                        "\n Type 1 if want to create account\n" +
                        "Type 2 for Exit\n");
                System.out.print("Enter your choice: ");
                choice0 = aa.nextInt();
                if (choice0 == 1) {
                    String id, name, id2, name2;
                    int balance, deposit = 0, withdraw = 0, choice, transfer = 0;
                    System.out.print("Enter your ID: ");
                    id = aa.next();
                    System.out.print("Enter your Name: ");
                    name = aa.next();
                    System.out.print("Enter your Balance: ");
                    balance = aa.nextInt();
                    s.executeUpdate("insert into " + tablename + " values(\"" + id + "\", \"" + name + "\", " + balance + ")");
                }
                else if(choice0==-1){
                    String id1, name1, id2, name2;
                    int balance, deposit = 0, withdraw = 0, choice, transfer = 0;
                    System.out.print("Enter your ID: ");
                    id1 = aa.next();
                    System.out.print("Enter your Name: ");
                    name1 = aa.next();
                    System.out.print("Enter your Balance: ");
                    balance = aa.nextInt();
                    Account A = new Account(id1, name1, balance);
                    while (true) {
                        System.out.print("Enter your choice:-\n " +
                                "type 0 for deposit\n" +
                                "type 1 for withdraw\n" +
                            //    "type 2 for tranfer amount on another account\n" +
                                "type 3 for exit the menu\n" +
                                "Enter: ");
                        choice = aa.nextInt();
                        if (choice == 0) {
                            System.out.print("Enter the Amount you want to deposit: ");
                            deposit = aa.nextInt();
                            s.executeUpdate("update Account set Balance=Balance+"+deposit+" where id =\""+id1+"\"");
                        }
                        else if (choice == 1) {
                            System.out.print("Enter the amount you want to withdraw: ");
                            withdraw = aa.nextInt();
                            s.executeUpdate("update Account set Balance=Balance-"+withdraw+" where id =\""+id1+"\"");
                        }
                /*        else if (choice == 2) {
                            System.out.println("Tramsfer Amount to Another Account:-");
                            System.out.println("Enter the Details of Acoount:- ");
                            System.out.print("Enter your ID: ");
                            id2 = aa.next();
                            System.out.print("Enter your Name: ");
                            name2 = aa.next();
                            Account A2 = new Account(id2, name2);
                            System.out.print("Enter the amount to transfer: ");
                            transfer = aa.nextInt();
                            System.out.print("Details:-\n" +
                                    "ID: " + A2.getId() + "\n" +
                                    "Name: " + A2.getName() + "\n" +
                                    "Amount you deposit: " + A2.transferTo(A2, transfer));
                        }*/
                        else if (choice == 3) {
                            break;
                        }
                        else {
                            System.out.println("Invalid Argument");
                        }
                    }
                    System.out.print("Your ID: " + A.getId() + "\n" +
                            "Your Name: " + A.getName() + "\n" +
                            "Your Current Balance: " + A.getBalance() + "\n" +
                            "Balance after Deposit: " + A.credit(deposit) + "\n" +
                            "Balance after Withdraw: " + A.debit(withdraw) + "\n" +
                            "Details in the form of String: " + A.tostring());
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}