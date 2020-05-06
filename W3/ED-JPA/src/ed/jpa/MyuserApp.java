/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.jpa;

/**
 *
 * @author Lenovo
 */
public class MyuserApp {

    /**
     * @param args the command line arguments
     */
    private MyuserDB mydb;

    public MyuserApp() {
        mydb = new MyuserDB();
        mydb.getRecord("000001");
        mydb.deleteRecord("000003");
    }

    public static void main(String[] args) {
        MyuserApp client = new MyuserApp();
// assuming inputs from keyboard or any GUI
        MyuserDTO myuserDTO = new MyuserDTO("000001", "Peter Smith", "123456",
                "psmith@swin.edu.au", "9876543210", "Swinburne EN510f",
                "What is my name?", "Peter");
        boolean result = client.createRecord(myuserDTO);
        client.showCreateResult(result, myuserDTO);
// assuming inputs from keyboard or any GUI
        MyuserDTO myuserDTO2 = new MyuserDTO("000006", "David Lee", "654321",
                "dlee@swin.edu.au", "0123456789", "Swinburne EN510g",
                "What is my name?", "David");
        result = client.createRecord(myuserDTO2);
        client.showCreateResult(result, myuserDTO2);

        result = client.updateRecord(myuserDTO);
        
        
    }

    public void showCreateResult(boolean result, MyuserDTO myuserDTO) {
        if (result) {
            System.out.println("Record with primary key " + myuserDTO.getUserid()
                    + " has been created in the database table.");
        } else {
            System.out.println("Record with primary key " + myuserDTO.getUserid()
                    + " could not be created in the database table!");
        }
    }

    public boolean createRecord(MyuserDTO myuserDTO) {
        return mydb.createRecord(myuserDTO);
    }

    private boolean updateRecord(MyuserDTO myuserDTO) {
        return mydb.updateRecord(myuserDTO);
    }

}
